package com.example.haenggu.data.remote.datasources


data class ResponseBoard (
    val resources : BoardResource
)

data class BoardResource (
    val links: LinkData,
    val content: List<BoardData>,
    val page: PageData
)

data class BoardData (

    val content: String,
    val created_date: String,
    val id: String,
    val modified_date: String,
    val schedule: String,
    val title: String,
    val user: BoardUser
)

data class BoardUser (
    val profile_image: String,
    val username: String
)
