package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.target.targetcasestudy.R
import com.target.targetcasestudy.viewmodels.DealDetailsViewmodel
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.data.model.DealItem
import kotlinx.android.synthetic.main.fragment_deal_item.*

class DealItemFragment : Fragment() {

    lateinit var mDealDetailsViewmodel: DealDetailsViewmodel
    private var mPid: Int = -1
    val MAX_CHARS = 10000

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get product id from bundle
        mPid = getArguments()?.getInt("deal_id") ?: 0
        init()
    }

    // Init View model
    private fun init() {
        mDealDetailsViewmodel =
                ViewModelProviders.of(this).get(DealDetailsViewmodel::class.java)
        subscribeObservers()
    }

    // Now we need to observe Live Data
    private fun subscribeObservers() {
        mDealDetailsViewmodel.getDealDetails(mPid).observe(viewLifecycleOwner,
                Observer {
                    // Observer Would be called on data set change
                    setData(it)
                })
    }

    private fun setData(response: DealItem) {
        dealSalePrice.text = response.salePrice?.displayString
        if (response.salePrice?.displayString != null) {
            dealRegularPrice.setBackgroundResource(R.drawable.strike_textview)
        }
        dealRegularPrice.text = response.regularPrice?.displayString
        dealTitle.text = response.title
        if (response.description.length < MAX_CHARS) {
            dealDesc.text = response.description
        } else {
            dealDesc.text = response.description.substring(0, MAX_CHARS)
        }
        Picasso
                .get()
                .load(response.imageUrl)
                .fit()
                .centerCrop()
                .into(dealImage);
    }
}