package com.example.haenggu.data.remote.datasources

data class ResponseEvent (
    val resources : EventResource
)

data class EventResource (
    val links: List<LinkData>,
    val content: List<EventData>,
    val page: PageData
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
    val favorite: Int,
)