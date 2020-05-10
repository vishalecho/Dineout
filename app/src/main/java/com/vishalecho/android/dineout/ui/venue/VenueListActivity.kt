package com.vishalecho.android.dineout.ui.venue

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vishalecho.android.core.network.Response
import com.vishalecho.android.core.ui.base.BaseActivity
import com.vishalecho.android.dineout.R
import com.vishalecho.android.dineout.data.model.VenuesResponse
import com.vishalecho.android.dineout.di.DineoutDH
import com.vishalecho.android.dineout.viewmodel.VenueListViewModel
import com.vishalecho.android.dineout.viewmodel.VenueListViewModelFactory
import kotlinx.android.synthetic.main.activity_venues.*
import java.io.IOException
import javax.inject.Inject


class VenueListActivity : BaseActivity(), VenueListAdapter.Interaction {

    private val component by lazy { DineoutDH.venueListComponent() }

    @Inject
    lateinit var viewModelFactory: VenueListViewModelFactory

    @Inject
    lateinit var adapter: VenueListAdapter

    private val viewModel: VenueListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(VenueListViewModel::class.java)
    }

    private val context: Context by lazy { this }

    private val TAG = "VenueListActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venues)
        app_toolbar.title = getString(R.string.app_bar_title)
        component.inject(this)

        adapter.interaction = this
        rvVenues.adapter = adapter
        srlVenues.setOnRefreshListener { viewModel.refreshRestaurants() }

        viewModel.getRestaurants()
        initiateDataListener()
    }

    private fun initiateDataListener() {
        //Observe the response and update state of the screen accordingly
        viewModel.venuesResponse.observe(this, Observer<Response<List<VenuesResponse.Response.Venue>>> { response ->
            Log.d(TAG, "initiateDataListener: $response")
            when(response) {
                is Response.Progress -> srlVenues.isRefreshing = response.loading

                is Response.Success  -> {
                    Log.d(TAG, "initiateDataListener: Successfully loaded data")
                    adapter.swapData(response.data)
                }

                is Response.Failure  -> {
                    if (response.e is IOException)
                        Toast.makeText(context, R.string.need_internet_venues, Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(context, R.string.failed_venues_try_again, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showCustomDialog(title: String, msg: String, lat: String, lng: String) {
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_directions)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setPositiveButton(R.string.open_google_map) { dialog, which ->
            val gmmIntentUri: Uri = Uri.parse("geo:$lat,$lng?q=$lat,$lng($title)")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(packageManager) != null) {
                startActivity(mapIntent)
            }
        }
        builder.show()
    }

    override fun restaurantClicked(
        venue: VenuesResponse.Response.Venue,
        tvRestaurantName: TextView,
        tvCategoriesName: TextView
    ) {
        showCustomDialog(venue.name, venue.location.city, venue.location.lat.toString(), venue.location.lng.toString())
    }
}
