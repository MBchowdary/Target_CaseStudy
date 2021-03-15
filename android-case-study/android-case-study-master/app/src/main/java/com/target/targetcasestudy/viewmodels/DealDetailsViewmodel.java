package com.target.targetcasestudy.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.target.targetcasestudy.data.repo.Repo;
import com.target.targetcasestudy.data.model.DealItem;

public class DealDetailsViewmodel extends ViewModel {

    private Repo mRepo;

    public DealDetailsViewmodel() {
        // create repo instance
        mRepo = Repo.getInstance();
    }

    //Return Live data to View
    public LiveData<DealItem> getDealDetails(int pid) {
        //Perform some action on the Live data
        return mRepo.getDealDetails(pid);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mRepo.clearDisposable();
    }
}