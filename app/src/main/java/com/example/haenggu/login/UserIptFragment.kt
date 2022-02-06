package com.example.haenggu.login

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.LoginUserIpt
import com.example.haenggu.databinding.FragmentLoginUseriptBinding

class UserIptFragment : Fragment(){

    private var _binding: FragmentLoginUseriptBinding? = null
    private val binding get() = _binding!!
    var count:Int = 0
    var count_region:Int = 0
    var events: ArrayList<String> = arrayListOf<String>("", "", "")
    var regions: ArrayList<String> = arrayListOf<String>("", "", "")
    var school = arguments?.getString("schoolname")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginUseriptBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.button.


        var gender:String = ""
        var nickname:String = ""
        var birth:String = ""
        var major:String = ""
        var grade:String = ""
        var mbti:String = ""
        var loginUserIpt : LoginUserIpt

        var flag_event1 = 0
        var flag_event2 = 0
        var flag_event3 = 0
        var flag_event4 = 0
        var flag_event5 = 0
        var flag_event6 = 0
        var flag_event7 = 0
        var flag_event8 = 0

        var flag_region1 = 0
        var flag_region2 = 0
        var flag_region3 = 0
        var flag_region4 = 0
        var flag_region5 = 0
        var flag_region6 = 0
        var flag_region7 = 0



        binding.fragmentUseriptSpnGrade.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.grade, android.R.layout.simple_spinner_item)
        binding.fragmentUseriptSpnMBTI.adapter = ArrayAdapter.createFromResource(requireContext(), R.array.mbti, android.R.layout.simple_spinner_item)

        binding.fragmentUseriptBtnStore.setOnClickListener {

            nickname = binding.fragmentUseriptEtNickname.text.toString()
            birth = binding.fragmentUseriptEtNickname.text.toString()

            if (isValidNickname_Eng(nickname) == false && isValidNickname_Korea(nickname) == false){
                Toast.makeText(requireContext(), "닉네임을 확인해 주세요.", Toast.LENGTH_LONG).show()
                nickname = ""
            }
            else if (nickname == "" || gender == "" || school == "" || major == "" ||
                birth== "" || grade == "" || mbti == "" || events[0] == "" || events[1] == "" || events[2] == "" ||
                regions[0] == "" || regions[1] == "" || regions[2] == "" ) {
                Toast.makeText(requireContext(), "모든 내용을 선택해주세요", Toast.LENGTH_LONG).show()
            } else {
                loginUserIpt =  LoginUserIpt(
                    birth = birth,
                    gender = gender,
                    grade = grade,
                    likesEvents = events,
                    likesRegions = regions,
                    major = major,
                    mbti = mbti,
                    nickname = nickname,
                    school = school!!
                )

                // LoginActivity에 JSON 보내서 Presenter에서 처리하게 하기
                val lActivity = activity as LoginActivity
                lActivity.getUserIpt(loginUserIpt)
            }

        }

        binding.fragmentUseriptBtnMan.setOnClickListener {
            gender = binding.fragmentUseriptBtnMan.text.toString()
            binding.fragmentUseriptBtnWoman.setBackgroundResource(R.drawable.gray_stroke_button)
            binding.fragmentUseriptBtnWoman.setTextColor(949494)
        }

        binding.fragmentUseriptBtnWoman.setOnClickListener {
            gender = binding.fragmentUseriptBtnWoman.text.toString()
            binding.fragmentUseriptBtnMan.setBackgroundResource(R.drawable.gray_stroke_button)
            binding.fragmentUseriptBtnMan.setTextColor(949494)
        }

        binding.fragmentUseriptEdtBirth.addTextChangedListener(watcherListener )

        binding.fragmentUseriptBtnSchool.setOnClickListener {
//            Intent
//
//            school =
            binding.fragmentUseriptBtnSchool.setBackgroundResource(R.drawable.gray_stroke_button)

        }

        binding.fragmentUseriptBtnSMajor.setOnClickListener {

            binding.fragmentUseriptBtnSMajor.setBackgroundResource(R.drawable.gray_stroke_button)

        }

        binding.fragmentUseriptSpnGrade.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    //선택안함
                    0 -> {
                    }
                    1 -> {
                        grade = "1"
                    }
                    2 -> {
                        grade = "2"
                    }
                    3 -> {
                        grade = "3"
                    }
                    4 -> {
                        grade = "4"
                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.fragmentUseriptSpnMBTI.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    //선택안함
                    0 -> {
                    }
                    1 -> {
                        mbti = "ISTJ"
                    }
                    2 -> {
                        mbti = "ISFJ"
                    }
                    3 -> {
                        mbti = "ISTP"
                    }
                    4 -> {
                        mbti = "ISFP"
                    }
                    5 -> {
                        mbti = "INFJ"
                    }
                    6 -> {
                        mbti = "INTJ"
                    }
                    7 -> {
                        mbti = "INFP"
                    }
                    8 -> {
                        mbti = "INTP"
                    }
                    9 -> {
                        mbti = "ESTP"
                    }
                    10 -> {
                        mbti = "ESFP"
                    }
                    11 -> {
                        mbti = "ESTJ"
                    }
                    12 -> {
                        mbti = "ESFJ"
                    }
                    13 -> {
                        mbti = "ENFP"
                    }
                    14 -> {
                        mbti = "ENTP"
                    }
                    15 -> {
                        mbti = "ENFJ"
                    }
                    16 -> {
                        mbti = "ENTJ"
                    }


                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.fragmentUseriptBtnEvent1.setOnClickListener {
            if (flag_event1 == 0){
                flag_event1 = 1
                binding.fragmentUseriptBtnEvent1.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent1.setTextColor(Color.WHITE)
            }else{
                flag_event1 =0
                binding.fragmentUseriptBtnEvent1.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent1.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "동아리 행사"
            count = (count + 1) % 3
        }
        binding.fragmentUseriptBtnEvent2.setOnClickListener{
            if (flag_event2 == 0){
                flag_event2 = 1
                binding.fragmentUseriptBtnEvent2.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent2.setTextColor(Color.WHITE)
            }else{
                flag_event2 =0
                binding.fragmentUseriptBtnEvent2.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent2.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "박람회"
            count = (count + 1) % 3
        }
        binding.fragmentUseriptBtnEvent3.setOnClickListener{
            if (flag_event3 == 0){
                flag_event3 = 1
                binding.fragmentUseriptBtnEvent3.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent3.setTextColor(Color.WHITE)
            }else{
                flag_event3 =0
                binding.fragmentUseriptBtnEvent3.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent3.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "컨퍼런스"
            count = (count + 1) % 3
        }
        binding.fragmentUseriptBtnEvent4.setOnClickListener {
            if (flag_event4 == 0) {
                flag_event4 = 1
                binding.fragmentUseriptBtnEvent4.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent4.setTextColor(Color.WHITE)
            } else {
                flag_event4 = 0
                binding.fragmentUseriptBtnEvent4.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent4.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "전시"
            count = (count + 1) % 3
        }

        binding.fragmentUseriptBtnEvent5.setOnClickListener {
            if (flag_event5 == 0) {
                flag_event5 = 1
                binding.fragmentUseriptBtnEvent5.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent5.setTextColor(Color.WHITE)
            } else {
                flag_event5 = 0
                binding.fragmentUseriptBtnEvent5.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent5.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "페스티벌"
            count = (count + 1) % 3
        }

        binding.fragmentUseriptBtnEvent6.setOnClickListener {
            if (flag_event6 == 0) {
                flag_event6 = 1
                binding.fragmentUseriptBtnEvent6.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent6.setTextColor(Color.WHITE)
            } else {
                flag_event6 = 0
                binding.fragmentUseriptBtnEvent6.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent6.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "콘서트"
            count = (count + 1) % 3
        }

        binding.fragmentUseriptBtnEvent7.setOnClickListener {
            if (flag_event7 == 0) {
                flag_event7 = 1
                binding.fragmentUseriptBtnEvent7.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent7.setTextColor(Color.WHITE)
            } else {
                flag_event7 = 0
                binding.fragmentUseriptBtnEvent7.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent7.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "연극/뮤지컬"
            count = (count + 1) % 3
        }

        binding.fragmentUseriptBtnEvent8.setOnClickListener {
            if (flag_event8 == 0) {
                flag_event8 = 1
                binding.fragmentUseriptBtnEvent8.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnEvent8.setTextColor(Color.WHITE)
            } else {
                flag_event8 = 0
                binding.fragmentUseriptBtnEvent8.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnEvent8.setTextColor(Color.argb(255, 113, 113, 1))
            }
            events[count] = "기타"
            count = (count + 1) % 3
        }

        binding.fragmentUseriptBtnRegion1.setOnClickListener{
            if (flag_region1 == 0) {
                flag_region1 = 1
                binding.fragmentUseriptBtnRegion1.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion1.setTextColor(Color.WHITE)
            } else {
                flag_region1 = 0
                binding.fragmentUseriptBtnRegion1.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion1.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "서울"
            count_region = (count_region + 1) % 3
        }
        binding.fragmentUseriptBtnRegion2.setOnClickListener{
            if (flag_region2 == 0) {
                flag_region2 = 1
                binding.fragmentUseriptBtnRegion2.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion2.setTextColor(Color.WHITE)
            } else {
                flag_region2 = 0
                binding.fragmentUseriptBtnRegion2.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion2.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "경기"
            count_region = (count_region + 1) % 3
        }
        binding.fragmentUseriptBtnRegion3.setOnClickListener{
            if (flag_region3 == 0) {
                flag_region3 = 1
                binding.fragmentUseriptBtnRegion3.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion3.setTextColor(Color.WHITE)
            } else {
                flag_region3 = 0
                binding.fragmentUseriptBtnRegion3.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion3.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "충청"
            count_region = (count_region + 1) % 3
        }
        binding.fragmentUseriptBtnRegion4.setOnClickListener{
            if (flag_region4 == 0) {
                flag_region4 = 1
                binding.fragmentUseriptBtnRegion4.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion4.setTextColor(Color.WHITE)
            } else {
                flag_region4 = 0
                binding.fragmentUseriptBtnRegion4.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion4.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "강원"
            count_region = (count_region + 1) % 3
        }
        binding.fragmentUseriptBtnRegion5.setOnClickListener{
            if (flag_region5 == 0) {
                flag_region5 = 1
                binding.fragmentUseriptBtnRegion5.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion5.setTextColor(Color.WHITE)
            } else {
                flag_region5 = 0
                binding.fragmentUseriptBtnRegion5.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion5.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "전라"
            count_region = (count_region + 1) % 3
        }
        binding.fragmentUseriptBtnRegion6.setOnClickListener{
            if (flag_region6 == 0) {
                flag_region6 = 1
                binding.fragmentUseriptBtnRegion6.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion6.setTextColor(Color.WHITE)
            } else {
                flag_region6 = 0
                binding.fragmentUseriptBtnRegion6.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion6.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "경상"
            count_region = (count_region + 1) % 3
        }
        binding.fragmentUseriptBtnRegion7.setOnClickListener{
            if (flag_region7 == 0) {
                flag_region7 = 1
                binding.fragmentUseriptBtnRegion7.setBackgroundResource(R.drawable.gray_stroke_button)
                binding.fragmentUseriptBtnRegion7.setTextColor(Color.WHITE)
            } else {
                flag_region7 = 0
                binding.fragmentUseriptBtnRegion7.setBackgroundResource(R.drawable.hcolor_button)
                binding.fragmentUseriptBtnRegion7.setTextColor(Color.argb(255, 113, 113, 1))
            }
            regions[count] = "제주"
            count_region = (count_region + 1) % 3
        }

    }

    val watcherListener = object : TextWatcher {

        // 문자열 변경 전
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        // 문자열 변경 직후
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        // 문자열 변경 완료 ( 화면에 반영)
        override fun afterTextChanged(s: Editable?) {
            if (s != null){
                binding.fragmentUseriptEdtBirth.setBackgroundResource(R.drawable.gray_stroke_button)
            }else{
                binding.fragmentUseriptEdtBirth.setBackgroundResource(R.drawable.gray_button)
            }
        }
    }

    fun isValidNickname_Korea(nickname: String?): Boolean {
        val trimmedNickname = nickname?.trim().toString()
        val exp = Regex("^[가-힣ㄱ-ㅎ]{8,}\$")
        return !trimmedNickname.isNullOrEmpty() && exp.matches(trimmedNickname)
    }

    fun isValidNickname_Eng(nickname: String?): Boolean {
        val trimmedNickname = nickname?.trim().toString()
        val exp = Regex("^[a-zA-Z]{10,}\$")
        return !trimmedNickname.isNullOrEmpty() && exp.matches(trimmedNickname)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}