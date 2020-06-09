package com.example.rodesta_dicodingsubmission1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserData(
    var avatar : Int? = null,
    var username: String? = null,
    var name: String? = null,
    var location: String? = null,
    var repository: String? = null,
    var company : String? = null,
    var followers: String? = null,
    var following: String? = null
): Parcelable
