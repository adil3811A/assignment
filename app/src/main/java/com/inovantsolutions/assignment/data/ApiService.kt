package com.inovantsolutions.assignment.data

import com.inovantsolutions.assignment.domain.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/rest/V1/productdetails/6701/253620?lang=en&store=KWD")
    suspend fun getData():Response
}