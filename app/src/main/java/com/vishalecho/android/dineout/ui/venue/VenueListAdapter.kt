package com.vishalecho.android.dineout.ui.venue

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishalecho.android.dineout.R
import com.vishalecho.android.dineout.data.model.VenuesResponse.Response.Venue
import com.vishalecho.android.dineout.utils.Constants
import kotlinx.android.synthetic.main.venue_item.view.*
import java.text.DecimalFormat

class VenueListAdapter : ListAdapter<Venue, VenueListAdapter.ListViewHolder>(VenuesDC()) {

    var interaction: Interaction? = null
    val decimalFormat = DecimalFormat("#.##")

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int) = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.venue_item, parent, false), interaction)

    override fun onBindViewHolder( holder: ListViewHolder, position: Int) = holder.bind(getItem(position))

    fun swapData(data: List<Venue>) {
        submitList(data.toMutableList())
    }

    inner class ListViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val clicked = getItem(adapterPosition)
            interaction?.restaurantClicked(clicked, itemView.tvRestaurantName, itemView.tvCategoriesName)
        }

        fun bind(item: Venue) = with(itemView) {
            tvRestaurantName.text =  item.name
            tvAddress.text        =  if (item.location.address.isNullOrEmpty()) "" else item.location.address
            tvCategoriesName.text =  item.categories[0].name
            tvDistance.text       =  (decimalFormat.format(item.location.distance / 1000.0)).toString().plus(Constants.KM)

            //SharedItem transition
            ViewCompat.setTransitionName(tvRestaurantName, item.name)
        }
    }

    interface Interaction {
        fun restaurantClicked(
            venue: Venue,
            tvRestaurantName  : TextView,
            tvCategoriesName  : TextView
        )
    }

    private class VenuesDC : DiffUtil.ItemCallback<Venue>() {
        override fun areItemsTheSame(oldItem: Venue, newItem: Venue) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Venue, newItem: Venue) = oldItem == newItem
    }
}