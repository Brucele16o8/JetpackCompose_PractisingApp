package com.bruce.tt.wealth.domain.model

import com.bruce.tt.wealth.data.remote.dto.CurrencyInfo
import com.google.gson.annotations.SerializedName

data class CoinTickerInformation(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String, @SerializedName ("symbol") val symbol: String,
    @SerializedName("quotes") val priceInfo: Map<String, CurrencyInfo>
)