package com.example.sanderbeazar.sportinaalst.domain

import java.io.Serializable

class Sportclub (val naam: String = "", val sport: String= "", val email: String= "",
                 val website: String="", val jongen: Boolean = true, val meisje: Boolean = true,
                 val Postcode : String, val adres : String = "", val Id : String): Serializable{

}