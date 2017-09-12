package com.fashare.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * SharedPreferences Utils
 */
class PreferencesUtil {
    private constructor()
    private constructor(context: Context){
        mSharedPreferences = context.getSharedPreferences(NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    companion object{
        private const val NAME_SHARED_PREFERENCES = "NAME_SHARED_PREFERENCES"

        fun getInstance(context: Context) = PreferencesUtil(context)
    }

    private lateinit var mSharedPreferences: SharedPreferences
    private var mGson: Gson = Gson()

    fun remove(key: String): Boolean{
        return mSharedPreferences.edit().remove(key).commit()
    }

    fun getBoolean(name: String, bDefault: Boolean): Boolean {
        return mSharedPreferences.getBoolean(name, bDefault)
    }

    fun putBoolean(name: String, value: Boolean): Boolean {
        val editor = mSharedPreferences.edit()
        editor.putBoolean(name, value)
        return editor.commit()
    }

    fun putString(key: String, value: String): Boolean {
        val editor = mSharedPreferences.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    fun getString(key: String): String {
        return mSharedPreferences.getString(key, "")
    }

    fun putInt(key: String, value: Int): Boolean {
        val editor = mSharedPreferences.edit()
        editor.putInt(key, value)
        return editor.commit()
    }

    fun putEntity(key: String, value: Any): Boolean {
        try {
            val editor = mSharedPreferences.edit()
            editor.putString(key, mGson.toJson(value))
            return editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    fun <T> getEntity(key: String, classOfT: Class<T>): T? {
        var entity: T? = null
        try {
            entity = mGson.fromJson(mSharedPreferences.getString(key, null), classOfT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return entity
    }

    fun putList(key: String, list: List<*>): Boolean {
        try {
            val editor = mSharedPreferences.edit()
            editor.putString(key, mGson.toJson(list))
            return editor.commit()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    fun <T : Any> getList(key: String, classOfT: Class<T>?): MutableList<T> {
        val list = ArrayList<T>()
        try {
            mGson.fromJson<ArrayList<JsonElement>>(mSharedPreferences.getString(key, null), object : TypeToken<ArrayList<JsonElement>>() {}.type)?.mapNotNullTo(list) { mGson.fromJson(it, classOfT) }
        } catch (jsonParseException: JsonParseException) {
            jsonParseException.printStackTrace()
        } catch (jsonSyntaxException: JsonSyntaxException) {
            jsonSyntaxException.printStackTrace()
        }
        return list
    }

    fun getInt(key: String, iDefault: Int): Int {
        return mSharedPreferences.getInt(key, iDefault)
    }

    fun putLong(key: String, value: Long): Boolean {
        val editor = mSharedPreferences.edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    fun getLong(key: String, iDefault: Long): Long {
        return mSharedPreferences.getLong(key, iDefault)
    }
}
