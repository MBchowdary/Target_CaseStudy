package com.target.targetcasestudy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.target.targetcasestudy.data.model.DealItem;
import com.target.targetcasestudy.data.repo.Repo;

import java.util.List;

public class DealsListViewmodel extends ViewModel {

    private Repo mRepo;

    public DealsListViewmodel() {
        mRepo = Repo.getInstance();
    }

    //Return Live data to View
    public LiveData<List<DealItem>> getDeals() {
        return mRepo.getDeals();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mRepo.clearDisposable();
    }
}