package com.example.haenggu.data.remote.datasources

data class ResponseEventDetail (
    val event_id: String,
    val title: String,
    val description: String,
    val started_date: String,
    val ended_date: String,
    val reservation_ended_date: String,
    val event_location: String,
    val category: String,
    val region: String,
    val tag: List<String>,
    val image_url: List<String>,
)