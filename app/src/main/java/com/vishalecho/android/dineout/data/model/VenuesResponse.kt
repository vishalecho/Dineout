package com.vishalecho.android.dineout.data.model

import com.google.gson.annotations.SerializedName

data class VenuesResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("response")
    val response: Response
) {
    data class Meta(
        @SerializedName("code")
        val code: Int,
        @SerializedName("requestId")
        val requestId: String
    )

    data class Response(
        @SerializedName("venues")
        val venues: List<Venue>
    ) {
        data class Venue(
            @SerializedName("beenHere")
            val beenHere: BeenHere,
            @SerializedName("categories")
            val categories: List<Category>,
            @SerializedName("contact")
            val contact: Contact,
            @SerializedName("hasPerk")
            val hasPerk: Boolean,
            @SerializedName("hereNow")
            val hereNow: HereNow,
            @SerializedName("id")
            val id: String,
            @SerializedName("location")
            val location: Location,
            @SerializedName("name")
            val name: String,
            @SerializedName("referralId")
            val referralId: String,
            @SerializedName("stats")
            val stats: Stats,
            @SerializedName("venueChains")
            val venueChains: List<Any>,
            @SerializedName("verified")
            val verified: Boolean
        ) {
            data class BeenHere(
                @SerializedName("count")
                val count: Int,
                @SerializedName("lastCheckinExpiredAt")
                val lastCheckinExpiredAt: Int,
                @SerializedName("marked")
                val marked: Boolean,
                @SerializedName("unconfirmedCount")
                val unconfirmedCount: Int
            )

            data class Category(
                @SerializedName("icon")
                val icon: Icon,
                @SerializedName("id")
                val id: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("pluralName")
                val pluralName: String,
                @SerializedName("primary")
                val primary: Boolean,
                @SerializedName("shortName")
                val shortName: String
            ) {
                data class Icon(
                    @SerializedName("prefix")
                    val prefix: String,
                    @SerializedName("suffix")
                    val suffix: String
                )
            }

            class Contact(
            )

            data class HereNow(
                @SerializedName("count")
                val count: Int,
                @SerializedName("groups")
                val groups: List<Any>,
                @SerializedName("summary")
                val summary: String
            )

            data class Location(
                @SerializedName("address")
                val address: String,
                @SerializedName("cc")
                val cc: String,
                @SerializedName("city")
                val city: String,
                @SerializedName("country")
                val country: String,
                @SerializedName("crossStreet")
                val crossStreet: String,
                @SerializedName("distance")
                val distance: Int,
                @SerializedName("formattedAddress")
                val formattedAddress: List<String>,
                @SerializedName("labeledLatLngs")
                val labeledLatLngs: List<LabeledLatLng>,
                @SerializedName("lat")
                val lat: Double,
                @SerializedName("lng")
                val lng: Double,
                @SerializedName("postalCode")
                val postalCode: String,
                @SerializedName("state")
                val state: String
            ) {
                data class LabeledLatLng(
                    @SerializedName("label")
                    val label: String,
                    @SerializedName("lat")
                    val lat: Double,
                    @SerializedName("lng")
                    val lng: Double
                )
            }

            data class Stats(
                @SerializedName("checkinsCount")
                val checkinsCount: Int,
                @SerializedName("tipCount")
                val tipCount: Int,
                @SerializedName("usersCount")
                val usersCount: Int,
                @SerializedName("visitsCount")
                val visitsCount: Int
            )
        }
    }
}