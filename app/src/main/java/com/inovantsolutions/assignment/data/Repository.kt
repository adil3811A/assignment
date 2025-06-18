package com.inovantsolutions.assignment.data

import com.inovantsolutions.assignment.domain.Response
import javax.inject.Inject

class Repository( val apiService: ApiService){
    suspend fun getdate():Response = apiService.getData()
}