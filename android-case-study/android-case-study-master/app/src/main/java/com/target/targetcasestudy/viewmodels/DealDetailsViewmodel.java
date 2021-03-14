package com.target.targetcasestudy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.target.targetcasestudy.repo.DealDetailsRepo;
import com.target.targetcasestudy.requets.data.DealItem;

public class DealDetailsViewmodel extends ViewModel {

    private DealDetailsRepo mDealDetailsRepo;

    public DealDetailsViewmodel() {
        mDealDetailsRepo = DealDetailsRepo.getInstance();
    }

    //Return Live data to View
    public LiveData<DealItem> getDealDetails(int pid) {
        return mDealDetailsRepo.getDealDetails(pid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDealDetailsRepo.clearDisposable();
    }
}