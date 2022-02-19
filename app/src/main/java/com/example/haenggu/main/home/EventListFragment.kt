package com.example.haenggu.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.haenggu.data.remote.datasources.EventData
import com.example.haenggu.data.remote.datasources.ResponseEvent
import com.example.haenggu.data.remote.services.RetrofitInstance
import com.example.haenggu.databinding.FragmentEventListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EventListFragment : Fragment() {
    private lateinit var param1: String
    private lateinit var param2: String

    val service = RetrofitInstance.api
    lateinit var data:ArrayList<EventData>

    private var _binding: FragmentEventListBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1).toString()
            param2 = it.getString(ARG_PARAM2).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventViewPagerAdapger = EventListAdapter{ EventData, View ->

        }
        binding.fragmentEventListRv.adapter = eventViewPagerAdapger
        val query = mutableMapOf<String, String>()
        query[param1] = param2
        service.getEventList(query).enqueue(object : Callback<ResponseEvent> {//Callback등록. Retrofit의 Callback을 import 해줘야 함!
        override fun onFailure(call: Call<ResponseEvent>, t: Throwable) { //통신 실패
            Log.e("통신 에러 : ", t.message!!)
        }
            override fun onResponse(
                call: Call<ResponseEvent>,
                response: Response<ResponseEvent>
            ) { //통신 성공
                if(response.isSuccessful) {//statusCode가 200~300 사이일때. 응답 body 이용 가능
                    eventViewPagerAdapger.datas = response.body()!!.resources.content
                    Log.d("통신 성공 : ", response.body()!!.toString())
                }
            }
        })
        eventViewPagerAdapger.notifyDataSetChanged()
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            EventListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}