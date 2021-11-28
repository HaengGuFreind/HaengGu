package com.example.haenggu.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_login_useript.*

class UserIptFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var grade: String ?= null
    private var mbti: String ?= null
    lateinit var navController : NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_useript, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gender : String ?= null
        var school : String ?= null
        var major : String ?= null
        var event = mutableListOf<String>()
        var region = mutableListOf<String>()

        navController = Navigation.findNavController(view)

        // 클릭시 모든 데이터를 인텐트에 담아 전송, Json 형태
        fragment_useript_btnStore.setOnClickListener {
            var loginUserIpt : LoginUserIpt
            if (fragment_useript_etNickname.text.toString() == null || gender == null || school == null || major == null ||
                fragment_useript_etSchool.text.toString() == null || fragment_useript_etMajor.text.toString() == null ||
                grade == null || mbti == null || event.size < 3 || region.size < 3) {
                Toast.makeText(requireContext(), "모든 내용을 선택해주세요", Toast.LENGTH_LONG).show()
            } else {
                loginUserIpt =  LoginUserIpt(
                    nickname = fragment_useript_etNickname.text.toString(),
                    gender = gender!!,
                    school = fragment_useript_etSchool.text.toString(),
                    major = fragment_useript_etMajor.text.toString(),
                    grade = grade!! ,
                    mbti = mbti!!,
                    event1 = event.get(0),
                    event2 = event.get(1),
                    event3 = event.get(2),
                    region1 = region.get(0),
                    region2 = region.get(1),
                    region3 = region.get(2)
                        )

                // LoginActivity에 JSON 보내서 Presenter에서 처리하게 하기
                val lActivity = activity as LoginActivity
                lActivity.getUserIpt(loginUserIpt)
            }
        }
        fragment_useript_btnMan.setOnClickListener{
            gender = "man"
        }

        fragment_useript_btnWoman.setOnClickListener{
            gender = "woman"
        }

//        fragment_useript_btnSchool.setOnClickListener {
//
//        }
//
//        fragment_useript_btnMajor.setOnClickListener {
//
//        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.grade_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            fragment_useript_spnGrade.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.mbti_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            fragment_useript_spnMbti.adapter = adapter
        }
        fragment_useript_spnGrade.onItemSelectedListener = this
        fragment_useript_spnMbti.onItemSelectedListener = this


        fragment_useript_btnEvent1.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("동아리행사")
        }
        fragment_useript_btnEvent2.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("잡페어")
        }
        fragment_useript_btnEvent3.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("컨퍼런스")
        }
        fragment_useript_btnEvent4.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("전시/졸업전시")
        }
        fragment_useript_btnEvent5.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("페스티벌")
        }
        fragment_useript_btnEvent6.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("콘서트")
        }
        fragment_useript_btnEvent7.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("연극/뮤지컬")
        }
        fragment_useript_btnEvent8.setOnClickListener {
            if (event.size == 3){
                event.removeAt(0)
            }
            event.add("기타")
        }


        fragment_useript_btnRegion1.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("기타")
        }
        fragment_useript_btnRegion2.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("경기")
        }
        fragment_useript_btnRegion3.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("충청")
        }
        fragment_useript_btnRegion4.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("강원")
        }
        fragment_useript_btnRegion5.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("전라")
        }
        fragment_useript_btnRegion6.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("경상")
        }
        fragment_useript_btnRegion7.setOnClickListener {
            if (region.size == 3){
                region.removeAt(0)
            }
            region.add("제주")
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        grade = position.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
