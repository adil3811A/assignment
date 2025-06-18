package com.inovantsolutions.assignment.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Response (
    @SerialName("data")
    val data:Data
)
data class Data(
    @SerialName("name")
    val name:String,
    @SerialName("brand_name")
    val brand_name:String,
    @SerialName("sku")
    val sku :String,
    @SerialName("final_price")
    val final_price:String,
    @SerialName("description")
    val description:String,
    @SerialName("images")
    val images:List<String>
)