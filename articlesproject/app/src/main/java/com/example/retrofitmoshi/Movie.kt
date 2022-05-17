package com.example.retrofitmoshi

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class Movie(
    //filmes populares
    var adult: Boolean,
    var backdrop_path: String,
    //var genre_ids: @RawValue List<genre_ids>,
    var id: String = "",
    var original_language: String?,
    var original_title: String,
    var overview: String,
    var popularity: String,
    var poster_path: String,
    var release_date: String,
    var title: String,
    var video: Boolean,
    var vote_average: String,
    var vote_count: String
) : Parcelable