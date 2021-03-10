package com.target.targetcasestudy.network


import com.target.targetcasestudy.BuildConfig

interface NetworkConstants {
    companion object {
        const val WS_GET_DEAL_LIST = BuildConfig.WEB_URL + "deals"
        const val WS_GET_DEAL_DETAILS = BuildConfig.WEB_URL + "deals/{id}"
    }

}
