package com.example.hipo_intern.FileService

import Member
import Root
import android.content.Context
import android.content.res.AssetManager
import com.google.gson.GsonBuilder
import java.io.InputStream
import java.lang.Exception

class Database(c : Context){
    val FILE_NAME = "hipo.json"
    var result = ""
    val context = c

    suspend fun getData() : List<Member>{
        readData(FILE_NAME)
        val gson = GsonBuilder().create()
        val model = gson.fromJson(result, Root::class.java)
        return model.members
    }
    suspend fun readData(fileName: String) : String{
        try {
            val assetManager : AssetManager = context.assets
            val inputStream : InputStream = assetManager.open(FILE_NAME)
            result = inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}