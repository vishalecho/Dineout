package com.vishalecho.android.dineout.data

import com.vishalecho.android.dineout.data.model.VenuesResponse

object DummyData {
    fun venuesResponse() = VenuesResponse(VenuesResponse.Meta(200, "67643698"), VenuesResponse.Response(listOf(venue())))

    fun venue() = VenuesResponse.Response.Venue(beenHere(), listOf(categories()), contact(), false, hereNow(), "3344", location(), "dsd", "dfd34", stats(), listOf(), false)
    fun contact() = VenuesResponse.Response.Venue.Contact()
    fun location() = VenuesResponse.Response.Venue.Location("abc", "in", "noida", "", "", 0, listOf(""), labelLatLong(), 0.0, 0.0, "", "")
    fun labelLatLong() = listOf(VenuesResponse.Response.Venue.Location.LabeledLatLng("", 0.0, 0.0))
    fun beenHere() = VenuesResponse.Response.Venue.BeenHere(0, 0, false, 0)
    fun categories() = VenuesResponse.Response.Venue.Category(icon(), "id", "name", "pn", false, "sh")
    fun icon() = VenuesResponse.Response.Venue.Category.Icon("url", ".png")
    fun stats() = VenuesResponse.Response.Venue.Stats(0, 0, 0, 0)
    fun hereNow() = VenuesResponse.Response.Venue.HereNow(0, listOf(), "")
}