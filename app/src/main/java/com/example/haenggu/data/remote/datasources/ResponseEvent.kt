package com.example.haenggu.data.remote.datasources

import java.util.*
import kotlin.collections.ArrayList

data class ResponseEvent (
    val resources : Resource
)

data class Resource (
    val content: ArrayList<EventData>
)

data class EventData (

    val category : String,
    val description: String,
    val ended_date: Date,
    val event_id: String,
    val image_url: ArrayList<String>,
    val region: String,
    val reservation_ended_date: Date,
    val started_date: Date,
    val tag: ArrayList<String>,
    val title: String
)