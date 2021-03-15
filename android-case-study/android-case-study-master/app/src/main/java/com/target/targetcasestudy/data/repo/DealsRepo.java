package com.target.targetcasestudy.data.repo;

import androidx.lifecycle.LiveData;

import com.target.targetcasestudy.data.api.DealsListApiClient;
import com.target.targetcasestudy.data.model.DealItem;

import java.util.List;

public class DealsRepo {

    private static DealsRepo instance;
    private DealsListApiClient mDealsListApiClient;

    public static DealsRepo getInstance(){
        if(instance == null){
            instance = new DealsRepo();
        }
        return instance;
    }

    private DealsRepo(){
        mDealsListApiClient = DealsListApiClient.getInstance();
    }

    // Live data to ViewModel
    public LiveData<List<DealItem>> getDeals(){
        return mDealsListApiClient.getDeals();
    }

    // Disposable will be cleared when its no longer needed
    public void clearDisposable(){
        mDealsListApiClient.clearDisposable();
    }
}
