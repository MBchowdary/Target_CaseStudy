package com.target.targetcasestudy.data.repo;

import androidx.lifecycle.LiveData;

import com.target.targetcasestudy.data.api.DealsListApiClient;
import com.target.targetcasestudy.data.model.DealItem;

import java.util.List;

public class DealsRepo {

    private DealsListApiClient mDealsListApiClient;

    public DealsRepo() {
        mDealsListApiClient = new DealsListApiClient();
    }

    // Live data to ViewModel
    public LiveData<List<DealItem>> getDeals() {
        return mDealsListApiClient.getDeals();
    }

    // Disposable will be cleared when its no longer needed
    public void clearDisposable() {
        mDealsListApiClient.clearDisposable();
    }
}
