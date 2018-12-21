package com.example.sanderbeazar.sportinaalst.network

import com.example.sanderbeazar.sportinaalst.domain.Sportclub
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SportclubApi {

    /**
     * een sportclub uit de API halen
     */
    @GET("/api/sportclubs/{icao}")
    fun getSportclub(@Path("icao") icao : String?):Observable<Sportclub>

    /**
     * alle sportclubs uit de API halen
     */
    @GET("/api/sportclubs/")
    fun getAllSportclubs(): Observable<List<Sportclub>>
}