package com.fashare.mvvm_juejin.view.detail

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebView
import com.fashare.adapter.OnItemClickListener
import com.fashare.adapter.ViewHolder
import com.fashare.base_ui.BaseActivity
import com.fashare.mvvm_juejin.JueJinApp
import com.fashare.mvvm_juejin.R
import com.fashare.mvvm_juejin.databinding.ActivityArticleBinding
import com.fashare.mvvm_juejin.model.article.ArticleBean
import com.fashare.mvvm_juejin.model.notify.NotifyBean
import com.fashare.mvvm_juejin.repo.Composers
import com.fashare.mvvm_juejin.repo.JueJinApis
import com.fashare.mvvm_juejin.viewmodel.ArticleVM
import com.fashare.net.ApiFactory
import kotlinx.android.synthetic.main.g_two_way_list.*
import java.io.*

class ArticleActivity : BaseActivity() {
    companion object{
        private val PARAMS_ARTICLE_ID = "PARAMS_ARTICLE_ID"

        val LOCAL_TEMPLATE by lazy{
            openAssets(JueJinApp.instance, "www/template.html")?: ""
        }

        val LOCAL_CSS by lazy{
            com.daimajia.gold.utils.d.b.a(com.daimajia.gold.utils.g.b())
        }

        val LOCAL_JS by lazy{
            com.daimajia.gold.utils.d.b.b(com.daimajia.gold.utils.g.c())
        }

        fun openAssets(context: Context, filePath: String): String? {
            try {
                return openAssets(context.resources.assets.open(filePath))
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }

        @Throws(IOException::class)
        fun openAssets(inputStream: InputStream): String {
            val stringWriter = StringWriter()
            val cArr = CharArray(2048)
            try {
                val bufferedReader = BufferedReader(InputStreamReader(inputStream, "utf-8"))
                while (true) {
                    val read = bufferedReader.read(cArr)
                    if (read == -1) {
                        break
                    }
                    stringWriter.write(cArr, 0, read)
                }
                return stringWriter.toString()
            } finally {
                inputStream.close()
            }
        }

        fun start(from: Context, article: ArticleBean?){
            Intent(from, ArticleActivity::class.java)
                    .putExtra(PARAMS_ARTICLE_ID, article?: ArticleBean("", "")).apply {
                from.startActivity(this)
            }
        }

        val START = object : OnItemClickListener<ArticleBean>() {
            override fun onItemClick(holder: ViewHolder, data: ArticleBean, position: Int) {
                ArticleActivity.start(holder.itemView.context, data)
            }
        }

        val START_FROM_NOTIFY = object : OnItemClickListener<NotifyBean>() {
            override fun onItemClick(holder: ViewHolder, data: NotifyBean, position: Int) {
                ArticleActivity.start(holder.itemView.context, data.entry?.toArticle())
            }
        }
    }

    private val IS_CLEAR = true
    lateinit var binding : ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityArticleBinding>(this, R.layout.activity_article).apply{
            binding = this
            this.articleVM = ArticleVM(rv)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        val article = intent?.getSerializableExtra(PARAMS_ARTICLE_ID) as ArticleBean
        loadArticleHtml(article)
        loadRelatedArticles(article.objectId)
    }

    private fun loadArticleHtml(article: ArticleBean) {
        binding.articleVM.article.set(article)
        binding.articleVM.headerData.article.set(article)

        ApiFactory.getApi(JueJinApis.Article.Html::class.java)
                .getHtml(article.objectId?: "")
                .compose(Composers.compose())
                .subscribe({
                    // 以下代码来自反编译的掘金app
                    var template = LOCAL_TEMPLATE
                    var screenshot = article.screenshot?: ""
                    if (TextUtils.isEmpty(template)) {
                        template = "<h1>404</h1>"
                    } else {
                        template = template.replace("{gold-toolbar-height}", "0px")
                                .replace("{gold-css-js}", LOCAL_CSS)
                                .replace("{gold-js-replace}", LOCAL_JS)
                                .replace("{gold-header}", com.daimajia.gold.utils.d.b.a(screenshot, article.originalUrl, article.title, article.user?.username?: ""))
                                .replace("{gold-content}", it.content?: "")
                    }
                    binding.articleVM.headerData.html.set(template)
                }, {})
    }

    private fun loadRelatedArticles(articleId: String?) {
        ApiFactory.getApi(JueJinApis:: class.java)
                .getRelatedEntry(entryId = articleId?: "")
                .compose(Composers.compose())
                .subscribe({
                    val list = it?.entrylist as Iterable<ArticleBean>

                    binding.articleVM.headerData.data.apply{
                        this.clear()
                        this.addAll(list.filter{ !this.contains(it) })  // 去重
                    }
                }, {

                })
    }
}
