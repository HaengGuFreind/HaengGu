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
    val category : String,
    val tag: List<String>,
    val event_image_uri: String,
    val favorite: Int,
)