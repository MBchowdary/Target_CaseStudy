package com.target.targetcasestudy.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.Constants
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.network.APIService
import com.target.targetcasestudy.network.OKHttpHelper
import com.target.targetcasestudy.network.response.DealListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class DealListFragment : Fragment(), DealClickListener {

    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    private var mAPIService: APIService? = null
    private var disposable: CompositeDisposable? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progressBar)
        init()
    }

    private fun init() {
        disposable = CompositeDisposable()
        mAPIService = OKHttpHelper.getAPIService(BuildConfig.WEB_URL)
        getDealList()
    }

    fun getDealList() {
        progressBar.visibility = View.VISIBLE


        disposable!!.add(
            mAPIService!!.getDealList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DealListResponse>() {
                    override fun onNext(response: DealListResponse) {
                        progressBar.visibility = View.GONE
                        if (!response.products.isNullOrEmpty()) {
                            Log.e("Response", "Response == " + response.products?.size)
                            setData(response)
                        } else {
                            Log.e("Response", "Response ERRRO== ")
                        }
                    }

                    override fun onError(e: Throwable) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            activity,
                            "Error While Getting List", Toast.LENGTH_SHORT
                        ).show()
                        Log.e("onError", "While Getting List == " + e.localizedMessage)
                    }

                    override fun onComplete() {
                        Log.e("onComplete", "onComplete")
                    }
                })
        )
    }

    fun setData(response: DealListResponse) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = DealItemAdapter(context!!, response.products!!, this)
    }

    override fun OnDealClickListner(dealItem: DealItem) {
        val intent = Intent(context, DealDetailsActivity::class.java)
        intent.putExtra(Constants.KEY_DEAL_ID, dealItem.id)
        startActivity(intent)
    }
}
