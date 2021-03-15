package com.target.targetcasestudy.data.repo;

import androidx.lifecycle.LiveData;

import com.target.targetcasestudy.data.api.DealDetailsApiClient;
import com.target.targetcasestudy.data.api.DealsListApiClient;
import com.target.targetcasestudy.data.model.DealItem;

import java.util.List;

public class Repo {

    private DealsListApiClient mDealsListApiClient;
    private DealDetailsApiClient mDealDetailsApiClient;
    private static Repo sRepo;

    public static Repo getInstance() {
        if (sRepo == null) {
            sRepo = new Repo();
        }
        return sRepo;
    }

    private Repo() {
        // create API client for service call
        mDealDetailsApiClient = new DealDetailsApiClient();
        mDealsListApiClient = new DealsListApiClient();
    }

    // Live data to ViewModel
    public LiveData<DealItem> getDealDetails(int pid) {
        return mDealDetailsApiClient.getProductDetails(pid);
    }

    // Live data to ViewModel
    public LiveData<List<DealItem>> getDeals() {
        return mDealsListApiClient.getDeals();
    }

    // Disposable will be cleared when its no longer needed
    public void clearDisposable() {
        mDealDetailsApiClient.clearDisposable();
        mDealsListApiClient.clearDisposable();
    }
}
