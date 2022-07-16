package com.sample.branchdeeplink.api


import com.google.gson.JsonObject
import com.sample.branchdeeplink.model.LinkResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RepoApi {

    @POST("v2/event/standard")
    suspend fun postContentEvent(@Body jsonObject: JsonObject): Response<LinkResponse>

}