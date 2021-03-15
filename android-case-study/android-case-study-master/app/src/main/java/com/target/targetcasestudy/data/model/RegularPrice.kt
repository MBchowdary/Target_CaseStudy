package com.target.targetcasestudy.data.model


import com.google.gson.annotations.SerializedName

data class RegularPrice(
    @SerializedName("amount_in_cents")
    val amountInCents: Int, // 18406
    @SerializedName("currency_symbol")
    val currencySymbol: String, // $
    @SerializedName("display_string")
    val displayString: String // $184.06
)