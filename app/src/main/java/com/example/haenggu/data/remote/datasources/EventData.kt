package com.example.haenggu.data.remote.datasources

import java.util.*

data class EventData (
    val resources : Content
)

data class Content (

    val category : String,
    val description: String,
    val ended_date: Date,
    val event_id: String,
    val image_url: Array<String>,
    val region: String,
    val reservation_ended_date: Date,
    val started_date: Date,
    val tag: Array<String>,
    val title: String
)