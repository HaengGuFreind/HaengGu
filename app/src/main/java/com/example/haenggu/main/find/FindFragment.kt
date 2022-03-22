package com.example.haenggu.main.find

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.ResponseBoard
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.databinding.FragmentFindBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindFragment : Fragment() {

    private var _binding : FragmentFindBinding? = null
    private val binding get() = _binding!!
    private val service = RetrofitInstance.api

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intiView()
    }

    private fun intiView() {
        service.getBoards().enqueue(object : Callback<ResponseBoard>{
            override fun onResponse(call: Call<ResponseBoard>, response: Response<ResponseBoard>) {
                if(response.isSuccessful){
                    var data = response.body()

                }
            }

            override fun onFailure(call: Call<ResponseBoard>, t: Throwable) {
            }

        })
    }

}