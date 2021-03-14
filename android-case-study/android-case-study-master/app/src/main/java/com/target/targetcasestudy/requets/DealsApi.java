package com.target.targetcasestudy.requets;

import com.target.targetcasestudy.requets.data.DealsList;
import com.target.targetcasestudy.requets.data.DealItem;

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