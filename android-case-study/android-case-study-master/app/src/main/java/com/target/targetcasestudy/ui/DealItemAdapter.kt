package com.target.targetcasestudy.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.requets.data.DealItem

class DealItemAdapter(
    var context: Context,
    var dealList: List<DealItem>,
    var listener: DealClickListener
) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dealList.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = dealList[position]
        viewHolder.title.text = item.title
        viewHolder.price.text = item.regularPrice?.displayString
        viewHolder.aisle.text = item.aisle
        viewHolder.itemView.setOnClickListener {
            listener.OnDealClickListner(item)
        }

        setProfileImage(item.imageUrl, viewHolder)
    }

    private fun setProfileImage(imageUrl: String, holder: DealItemViewHolder) {
        Picasso
            .get()
            .load(imageUrl)
            .into(holder.imageView)
    }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.deal_list_item_title)
    var price: TextView = itemView.findViewById(R.id.deal_list_item_price)
    var aisle: TextView = itemView.findViewById(R.id.deal_list_item_b2)
    var imageView: ImageView = itemView.findViewById(R.id.deal_list_item_image_view)
}

interface DealClickListener {
    fun OnDealClickListner(dealItem: DealItem)
}