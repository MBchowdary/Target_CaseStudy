package com.target.targetcasestudy.data.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.target.targetcasestudy.data.model.DealsList;
import com.target.targetcasestudy.data.model.DealItem;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

// Here we will retrieve the data
public class DealsListApiClient {
    private static final String TAG = "DealsApiClient";

    private static DealsListApiClient instance;
    private DealsApi mDealsApi;
    private MutableLiveData<List<DealItem>> mDealsList;
    private final CompositeDisposable mCompositeDisposable;

    public static DealsListApiClient getInstance() {
        if (instance == null) {
            instance = new DealsListApiClient();
        }
        return instance;
    }

    private DealsListApiClient() {
        mDealsList = new MutableLiveData<>();
        mCompositeDisposable = new CompositeDisposable();
    }

    // Live data to Repo
    public LiveData<List<DealItem>> getDeals() {
        // Get the data from API call then transform it to Live data then pass it along
        // Service call
        dealsListServiceCall();
        return mDealsList;
    }

    private void dealsListServiceCall() {
        // Service instance
        mDealsApi = ServiceApi.getDealsApi();
        // This will give us observable
        Single<DealsList> dealsListObservable = mDealsApi.getDealList();
        //Subscribe with observer
        subscribeWithObserver(dealsListObservable);
    }

    private void subscribeWithObserver(Single<DealsList> dealsListObservable) {
        dealsListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<DealsList>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull DealsList dealsList) {
                        // Set data to Live data
                        mDealsList.setValue(dealsList.getProducts());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError" + e.getMessage());
                    }
                });
    }

    // Disposable will be cleared when its no longer needed
    public void clearDisposable() {
        mCompositeDisposable.clear();
    }
}
