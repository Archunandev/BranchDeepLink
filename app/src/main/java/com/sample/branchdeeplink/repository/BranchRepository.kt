package com.sample.branchdeeplink.repository


import com.google.gson.JsonObject
import com.sample.branchdeeplink.api.RetrofitInstance

class BranchRepository() {

    suspend fun postDatatoget(jsonObject: JsonObject) =
        RetrofitInstance.api.postContentEvent(jsonObject)
}