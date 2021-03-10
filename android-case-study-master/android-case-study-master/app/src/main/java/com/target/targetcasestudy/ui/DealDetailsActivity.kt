package com.target.targetcasestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.Constants
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.network.APIService
import com.target.targetcasestudy.network.OKHttpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class DealDetailsActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private var mAPIService: APIService? = null
    private var disposable: CompositeDisposable? = null
    private var dealId: Int = -1
    private lateinit var dealSalePrice: TextView
    private lateinit var dealRegularPrice: TextView
    private lateinit var dealTitle: TextView
    private lateinit var dealDesc: TextView
    private lateinit var dealImage: ImageView
    val MAX_CHARS = 10000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_details)
        init()
    }

    private fun init() {
        if (intent.hasExtra(Constants.KEY_DEAL_ID)) {
            dealId = intent.getIntExtra(Constants.KEY_DEAL_ID, -1)
        }

        dealImage = findViewById(R.id.dealImage)
        dealSalePrice = findViewById(R.id.dealSalePrice)
        dealRegularPrice = findViewById(R.id.dealRegularPrice)
        dealTitle = findViewById(R.id.dealTitle)
        dealDesc = findViewById(R.id.dealDesc)

        progressBar = findViewById(R.id.progressBarDetails)
        disposable = CompositeDisposable()
        mAPIService = OKHttpHelper.getAPIService(BuildConfig.WEB_URL)
        if (dealId != -1) {
            getDealDetails()
        }
    }

    private fun getDealDetails() {
        progressBar.visibility = View.VISIBLE

        disposable!!.add(
            mAPIService!!.getDealDetails(dealId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DealItem>() {
                    override fun onNext(response: DealItem) {
                        progressBar.visibility = View.GONE
                        if (response != null) {
                            Log.e("Response", "Response == " + response.title)
                            setData(response)
                        } else {
                            Log.e("Response", "Response ERRRO== ")
                        }
                    }

                    override fun onError(e: Throwable) {
                        progressBar.visibility = View.GONE
                        Log.e("onError", "While Getting List == " + e.localizedMessage)
                    }

                    override fun onComplete() {
                        Log.i("onComplete", "onComplete")
                    }
                })
        )
    }

    private fun setData(response: DealItem) {
        dealSalePrice.text = response.sale_price?.display_string
        dealRegularPrice.text = response.regular_price?.display_string
        dealTitle.text = response.title
        if(response.description.length < MAX_CHARS) {
            dealDesc.text = response.description
        }else {
            dealDesc.text = response.description.substring(0, MAX_CHARS)
        }


        Glide.with(this)
            .load(response.image_url)
            .into(dealImage)
    }
}