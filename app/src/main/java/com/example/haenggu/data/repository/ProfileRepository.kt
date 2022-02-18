package com.example.haenggu.data.repository

import com.example.haenggu.data.remote.datasources.Boards
import com.example.haenggu.data.remote.datasources.Edit
import com.example.haenggu.data.remote.datasources.Events
import com.example.haenggu.data.remote.datasources.Profile
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.data.remote.services.RetrofitInstance_Profile
import retrofit2.Response

class ProfileRespository {
    suspend fun getProfile(): Response<Profile> {
        return RetrofitInstance.api.getProfile()
    }

    suspend fun getBoards(options: Map<String,String>): Response<List<Boards>> {
        return RetrofitInstance_Profile.api.getBoards(options)
    }

    suspend fun getEvents(options: Map<String,String>): Response<List<Events>> {
        return RetrofitInstance_Profile.api.getEvents(options)
    }

    suspend fun getEdit(): Response<Edit>{
        return RetrofitInstance.api.getEdit()
    }

}
