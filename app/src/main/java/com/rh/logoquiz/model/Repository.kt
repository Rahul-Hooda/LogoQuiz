package com.rh.logoquiz.model

import android.content.Context

class Repository {
    fun getCompanyList(context: Context): ArrayList<CompanyModel> {
        return JsonParser().getCompanyList(context,"logo.txt")
    }
}