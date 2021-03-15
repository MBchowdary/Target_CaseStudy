package com.target.targetcasestudy.data.api;

import com.target.targetcasestudy.data.model.DealsList;
import com.target.targetcasestudy.data.model.DealItem;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.target.targetcasestudy.utils.NetworkConstants.WS_GET_DEAL_DETAILS;
import static com.target.targetcasestudy.utils.NetworkConstants.WS_GET_DEAL_LIST;

public interface DealsApi {
    //Get List of Deals
    @GET(WS_GET_DEAL_LIST)
    Single<DealsList> getDealList();

    //Get Details of a Deal
    @GET(WS_GET_DEAL_DETAILS)
    Single<DealItem> getDealDetails(@Path("id") int id);
}