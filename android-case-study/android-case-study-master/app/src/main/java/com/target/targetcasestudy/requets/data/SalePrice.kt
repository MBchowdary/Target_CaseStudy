package com.target.targetcasestudy.requets.data


import com.google.gson.annotations.SerializedName

data class SalePrice(
    @SerializedName("amount_in_cents")
    val amountInCents: Int, // 734
    @SerializedName("currency_symbol")
    val currencySymbol: String, // $
    @SerializedName("display_string")
    val displayString: String // $7.34
)