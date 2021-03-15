package com.target.targetcasestudy.utils

import com.target.targetcasestudy.BuildConfig

/**
 * A constants class
 */
object NetworkConstants {
    const val BASE_URL = BuildConfig.WEB_URL
    const val WS_GET_DEAL_LIST = "deals"
    const val WS_GET_DEAL_DETAILS = "deals/{id}"
}