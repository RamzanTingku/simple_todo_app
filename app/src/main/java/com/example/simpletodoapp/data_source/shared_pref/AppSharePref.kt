package com.example.simpletodoapp.data_source.shared_pref

import android.content.Context
import android.content.SharedPreferences
import com.example.simpletodoapp.MainApplication

object AppSharedPref {
    private const val sharedPrefFile = "todo_app_file"
    private val appContext = MainApplication.instance
    private val sharedPreferences: SharedPreferences =
        appContext.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)


    fun saveStringData(key: String, dataString: String): Boolean{
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, dataString)
        return  editor.commit()
    }

    fun getStringData(key: String) : String?{
        return  sharedPreferences.getString(key, null)
    }

    fun clearSharedPrefData(){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

}