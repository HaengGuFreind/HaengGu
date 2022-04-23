package com.example.haenggu.main.find

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.remote.datasources.BoardData
import com.example.haenggu.data.remote.datasources.ResponseBoard
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.databinding.FragmentFindBinding
import com.example.haenggu.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindFragment : Fragment() {

    private var _binding : FragmentFindBinding? = null
    private val binding get() = _binding!!
    val sharedManager: SharedManager by lazy { SharedManager(context as MainActivity) }
    private val service = RetrofitInstance.api
    var data = mutableListOf<BoardData>()

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

        val findListAdapger = FindListAdapter { BoardData, View ->
            val intent = Intent(activity, FindDetailActivity::class.java)
            intent.putExtra("idx", BoardData.id)
            startActivity(intent)
        }
        binding.fragmentFindRv.adapter = findListAdapger

        service.getBoards("Bearer " + sharedManager.getHToken()).enqueue(object : Callback<ResponseBoard>{
            override fun onResponse(call: Call<ResponseBoard>, response: Response<ResponseBoard>) {
                Log.d("응답", response.toString())
                if(response.isSuccessful){
                    Log.d("어댑터", response.body()!!.resources.content.toString())
                    data.clear()
                    data.addAll(response.body()!!.resources.content)
                    findListAdapger.datas = data
                    findListAdapger.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ResponseBoard>, t: Throwable) {
                Log.d("실패", t.toString())
            }

        })
    }

}