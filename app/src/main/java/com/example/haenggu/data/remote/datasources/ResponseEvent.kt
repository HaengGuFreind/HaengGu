package com.example.haenggu.data.remote.datasources

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

data class ResponseEvent (
    val resources : Resource
)

data class Resource (
    val links: List<LinkData>,
    val content: List<EventData>
)

data class LinkData (
    val rel: String,
    val href: String
)

data class EventData (

    val event_id: String,
    val title: String,
    val description: String,
    val started_date: String,
    val ended_date: String,
    val reservation_ended_date: String,
    val event_location: String,
    val category : String,
    val region: String,
    val tag: List<String>,
    val image_url: List<String>,
)