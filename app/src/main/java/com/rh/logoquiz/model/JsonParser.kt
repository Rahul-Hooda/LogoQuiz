package com.rh.logoquiz.model

import android.content.Context
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class JsonParser {

    private fun getJsonFromAssets(context: Context, fileName: String): String {
        val jsonString: String
        jsonString = try {
            val `is`: InputStream = context.getAssets().open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, "UTF-8")

        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
        return jsonString
    }

    internal fun getCompanyList(context: Context, fileName: String): ArrayList<CompanyModel>{
        val jsonFileString: String =
            getJsonFromAssets(context, fileName)

        val gson = Gson()
        val response = gson.fromJson(jsonFileString,CompanyListModel::class.java)

        return if(response.companyListModel != null) response.companyListModel!! else ArrayList<CompanyModel>()
    }
}