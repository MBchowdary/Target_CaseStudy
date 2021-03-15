package com.target.targetcasestudy.data.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.target.targetcasestudy.data.model.DealItem;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DealDetailsApiClient {
    private static final String TAG = "DealDetailApiClient";
    private static DealDetailsApiClient instance;
    private MutableLiveData<DealItem> mProduct;
    private final CompositeDisposable mCompositeDisposable;
    private DealsApi mDealsApi;

    private DealDetailsApiClient() {
        mCompositeDisposable = new CompositeDisposable();
        mProduct = new MutableLiveData<>();
    }

    public static DealDetailsApiClient getInstance() {
        if (instance == null) {
            instance = new DealDetailsApiClient();
        }
        return instance;
    }

    // Live data to Repo
    public LiveData<DealItem> getProductDetails(int pid) {
        // Get the data from API call then transform it to Live data then pass it along
        // Service call
        productDetailsServiceCall(pid);
        return mProduct;
    }

    // testing
    public void productDetailsServiceCall(int pid) {
        // Service instance
        mDealsApi = ServiceApi.getDealsApi();
        // This will give us observable
        Single<DealItem> productObservable = mDealsApi.getDealDetails(pid);
        //Subscribe with observer
        subscribeWithObserver(productObservable);
    }

    private void subscribeWithObserver(Single<DealItem> productObservable) {
        productObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<DealItem>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull DealItem dealItem) {
                        // Set data to LiveData
                        mProduct.setValue(dealItem);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError"+e.getMessage());
                    }
                });
    }

    // Disposable will be cleared when its no longer needed
    public void clearDisposable() {
        mCompositeDisposable.clear();
    }
}