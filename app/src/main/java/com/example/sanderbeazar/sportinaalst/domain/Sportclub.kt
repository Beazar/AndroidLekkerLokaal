package com.example.sanderbeazar.sportinaalst.domain

import android.os.Parcelable
import java.io.Serializable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
class Sportclub (@field:Json(name="name") val naam: String,
                 @field:Json(name="sport") val sport: String,
                 @field:Json(name="email") val email: String,
                 @field:Json(name="website") val website: String,
                 @field:Json(name="jongen") val jongen: Boolean,
                 @field:Json(name="meisje") val meisje: Boolean,
                 @field:Json(name="postcode") val Postcode : String,
                 @field:Json(name="adres") val adres : String,
                 @field:Json(name="_id") val Id : String): Serializable, Parcelable