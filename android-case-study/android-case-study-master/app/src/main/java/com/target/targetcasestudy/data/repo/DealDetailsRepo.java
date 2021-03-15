package com.target.targetcasestudy.data.repo;

import androidx.lifecycle.LiveData;

import com.target.targetcasestudy.data.api.DealDetailsApiClient;
import com.target.targetcasestudy.data.model.DealItem;

public class DealDetailsRepo {

    private static DealDetailsRepo instance;
    private DealDetailsApiClient mDealDetailsApiClient;

    public static DealDetailsRepo getInstance(){
        if(instance == null){
            instance = new DealDetailsRepo();
        }
        return instance;
    }

    private DealDetailsRepo(){
        mDealDetailsApiClient = DealDetailsApiClient.getInstance();
    }

    // Live data to ViewModel
    public LiveData<DealItem> getDealDetails(int pid){
        return mDealDetailsApiClient.getProductDetails(pid);
    }

    // Disposable will be cleared when its no longer needed
    public void clearDisposable(){
        mDealDetailsApiClient.clearDisposable();
    }
}
