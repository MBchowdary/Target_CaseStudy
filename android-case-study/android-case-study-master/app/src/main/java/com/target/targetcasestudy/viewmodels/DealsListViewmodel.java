package com.target.targetcasestudy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.target.targetcasestudy.data.repo.DealsRepo;
import com.target.targetcasestudy.data.model.DealItem;

import java.util.List;

public class DealsListViewmodel extends ViewModel {

    private DealsRepo mDealsRepo;

    public DealsListViewmodel() {
        mDealsRepo = new DealsRepo();
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