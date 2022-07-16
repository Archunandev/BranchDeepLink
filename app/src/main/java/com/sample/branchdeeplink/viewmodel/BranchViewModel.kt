package com.sample.branchdeeplink.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.sample.branchdeeplink.app.BranchDeepLinkApplication
import com.sample.branchdeeplink.model.LinkResponse

import com.sample.branchdeeplink.repository.BranchRepository
import kotlinx.coroutines.launch
import retrofit2.Response


class BranchViewModel(app: Application, val branchRepository: BranchRepository) :
    AndroidViewModel(app) {

    val branchLink: MutableLiveData<LinkResponse> = MutableLiveData()
    var branchLinkResponse: LinkResponse? = null


    fun postValueServer(jsonObject: JsonObject) = viewModelScope.launch { getList(jsonObject) }


    private suspend fun getList(jsonObject: JsonObject) {
        try {
            if (hasInternetConnection()) {
                val response = branchRepository.postDatatoget(jsonObject)
                branchLink.postValue(handleList(response))
            } else {
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun handleList(response: Response<LinkResponse>): LinkResponse? {
        if (response.isSuccessful) {
            response.body().let { resultResponse ->
                branchLinkResponse = resultResponse!!

                return branchLinkResponse?.let { it }
            }
        }
        return LinkResponse(false)
    }

    private fun hasInternetConnection(): Boolean {

        val connectivityManager = getApplication<BranchDeepLinkApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false

            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }

}