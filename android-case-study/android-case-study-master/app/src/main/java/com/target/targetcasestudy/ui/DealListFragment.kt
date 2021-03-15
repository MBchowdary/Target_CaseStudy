package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.model.DealItem
import com.target.targetcasestudy.viewmodels.DealsListViewmodel


class DealListFragment : Fragment(), DealClickListener {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDealsListViewmodel: DealsListViewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        view.findViewById<RecyclerView>(R.id.fragment_deal_list).layoutManager =
            LinearLayoutManager(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView = view.findViewById(R.id.fragment_deal_list)
        init()
    }

    // Init View model
    private fun init() {
        mDealsListViewmodel =
            ViewModelProviders.of(this).get(DealsListViewmodel::class.java)
        subscribeObservers()
    }

    // Now we need to observe Live Data
    private fun subscribeObservers() {
        mDealsListViewmodel.getDeals().observe(viewLifecycleOwner,
            Observer {
                // Observer Would be called on data set change
                setData(it)
            })
    }

    fun setData(response: List<DealItem>) {
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.adapter = context?.let { DealItemAdapter(it, response, this) }
        mRecyclerView.adapter?.notifyDataSetChanged()
    }

    override fun OnDealClickListner(dealItem: DealItem) {
        //When onclick event is received on List Item send data
        // Send DealItem to DealItemFragment for Query
        val bundle = Bundle()
        bundle.putInt("deal_id", dealItem.id)
        val navController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }
        navController?.navigate(R.id.action_list_to_details, bundle)
    }
}
