package com.example.haenggu.data.remote.datasources

data class ResponseBoardDetail(

    val content: String,
    val event: Event,
    val id: String,
    val is_favorite: String,
    val schedule: String,
    val title: String,
    val user: BoardUser,
)

data class Event(
    val id: String,
    val title: String
)
