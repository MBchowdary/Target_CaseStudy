package com.target.targetcasestudy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.target.targetcasestudy.repo.DealsRepo;
import com.target.targetcasestudy.requets.data.DealItem;

import java.util.List;

public class DealsListViewmodel extends ViewModel {

    private DealsRepo mDealsRepo;

    public DealsListViewmodel() {
        mDealsRepo = DealsRepo.getInstance();
    }

    //Return Live data to View
    public LiveData<List<DealItem>> getDeals() {
        return mDealsRepo.getDeals();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDealsRepo.clearDisposable();
    }
}