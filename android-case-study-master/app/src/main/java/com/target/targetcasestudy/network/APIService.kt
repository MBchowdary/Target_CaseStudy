package com.target.targetcasestudy.network


import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.network.response.DealListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {


    @GET(NetworkConstants.WS_GET_DEAL_LIST)
    fun getDealList(): Observable<DealListResponse>

    @GET(NetworkConstants.WS_GET_DEAL_DETAILS)
    fun getDealDetails(@Path("id") dealId:Int): Observable<DealItem>
}
