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
    var data = mutableListOf<EventData>()

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
//        data.apply {
//            add(EventData("EXHIBITION", "인상주의의 후원가，수집가로 알려진 예술가 － 구스타브 카유보트의 그림과 이야기 그리고 조향사가 그림에서 영감 받아 조향한향수를 함께 감상하는 특별한 전시",
//                "2022-04-03T00:00:00", "2c023bb7-9c64-4c82-a582-4d0e1b532716","ㅇㅇ", "서울", "2022-04-03T00:00:00",
//                "2022-04-03T00:00:00", "전시회","카유보트 : 향기를 만나다 展","헤이리스 갤러리(경기도 파주시 탄현면 헤이리마을길 7)" ))
//            add(EventData("EXHIBITION", "인상주의의 후원가，수집가로 알려진 예술가 － 구스타브 카유보트의 그림과 이야기 그리고 조향사가 그림에서 영감 받아 조향한향수를 함께 감상하는 특별한 전시",
//                "2022-04-03T00:00:00", "2c023bb7-9c64-4c82-a582-4d0e1b532716","ㅇㅇ", "서울", "2022-04-03T00:00:00",
//                "2022-04-03T00:00:00", "전시회","카유보트 : 향기를 만나다 展","헤이리스 갤러리(경기도 파주시 탄현면 헤이리마을길 7)" ))
//            add(EventData("EXHIBITION", "인상주의의 후원가，수집가로 알려진 예술가 － 구스타브 카유보트의 그림과 이야기 그리고 조향사가 그림에서 영감 받아 조향한향수를 함께 감상하는 특별한 전시",
//                "2022-04-03T00:00:00", "2c023bb7-9c64-4c82-a582-4d0e1b532716","ㅇㅇ", "서울", "2022-04-03T00:00:00",
//                "2022-04-03T00:00:00", "전시회","카유보트 : 향기를 만나다 展","헤이리스 갤러리(경기도 파주시 탄현면 헤이리마을길 7)" ))
//        }
        service.getEventList(query).enqueue(object : Callback<ResponseEvent> {//Callback등록. Retrofit의 Callback을 import 해줘야 함!
        override fun onFailure(call: Call<ResponseEvent>, t: Throwable) { //통신 실패
            Log.e("통신 에러 : ", t.stackTraceToString())
        }
            override fun onResponse(
                call: Call<ResponseEvent>,
                response: Response<ResponseEvent>
            ) { //통신 성공
                if(response.isSuccessful) {//statusCode가 200~300 사이일때. 응답 body 이용 가능
                    data.clear()
                    data.addAll(response.body()!!.resources.content)
                    Log.d("통신 성공 : ", response.body()!!.resources.content.toString())
                    eventViewPagerAdapger.datas = data
                    eventViewPagerAdapger.notifyDataSetChanged()
                }
            }
        })
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