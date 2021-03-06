package com.example.haenggu.login

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.UserInfo
import com.example.haenggu.databinding.FragmentLoginUseriptBinding
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableStringBuilder
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.haenggu.data.local.SharedManager
import java.util.regex.Pattern


class UserIptFragment : Fragment(), View.OnClickListener{

    private var _binding: FragmentLoginUseriptBinding? = null
    private val binding get() = _binding!!

    val sharedManager: SharedManager by lazy { SharedManager(requireContext()) }
    private var gender = String()
    private var usergrade:Int = 0
    private var usermbti:String = ""
    private lateinit var spinnerAdapterGrade: SpinnerAdapter
    private lateinit var spinnerAdapterMBTI: SpinnerAdapter
    private val listOfGrade = ArrayList<SpinnerModel>()
    private val listOfMBTI = ArrayList<SpinnerModel>()

    var events =  ArrayList<String>()
    var regions= ArrayList<String>()

    private val activityViewModel: SharedViewModel by activityViewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                SharedViewModel() as T
        }
    }

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
        val lActivity = activity as LoginActivity

        Log.d("Htoken_useript",sharedManager.getHToken())
        // ???????????? ??? ?????????
        val ssb = SpannableStringBuilder(binding.fragmentUseriptTextView1.text)
        ssb.apply {
            setSpan(
                ForegroundColorSpan(Color.parseColor("#FF7171")),
                8,
                13,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(Color.parseColor("#FF7171")),
                15,
                23,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

        }
        binding.fragmentUseriptTextView1.text = ssb

//      ?????????
        binding.fragmentUseriptTv2.visibility = View.INVISIBLE
        binding.fragmentUseriptEtNickname.setupClearButtonWithAction()
        binding.fragmentUseriptEtNickname.setOnEditorActionListener (object : TextView.OnEditorActionListener
        { override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?):
                Boolean { if (actionId == EditorInfo.IME_ACTION_DONE){
            binding.fragmentUseriptEtNickname.setBackgroundResource(R.drawable.gray1_box_6)
            binding.fragmentUseriptEtNickname.isCursorVisible = false
            binding.fragmentUseriptEtNickname.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            //fragment ????????? ?????????
            val mInputMethodManager =
                context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow( binding.fragmentUseriptEtNickname.getWindowToken(), 0)
            return true }
            return false } })


        binding.fragmentUseriptEtNickname.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            val ps: Pattern =
                Pattern.compile("^[a-zA-Z???-???]+$")
            if (source == "" || ps.matcher(source).matches()) {
                return@InputFilter source
            }
    //        lActivity.toast( "??????,????????? ?????? ???????????????.")
            ""
        }, InputFilter.LengthFilter(8))


        //??????
        // ????????? ???????????? ???????????? ??????
        Log.d("gh","ii")

        try{
            val typeface = Typeface.createFromAsset(requireContext().assets, "font/pretendard_medium.otf")
            binding.fragmentUseriptBtnFemail.typeface = typeface
        }catch (e: Exception) {
            Log.d("exception occurred.","font")
        }

        Log.d("????????? ????????????242",sharedManager.getGender())
        if(sharedManager.getGender() == "female"){
            Log.d("????????? ????????????",sharedManager.getGender().toString())
            binding.fragmentUseriptBtnFemail.setBackgroundResource(R.drawable.pink_button)
            binding.fragmentUseriptBtnFemail.setTextColor(Color.parseColor("#FFFFFF"))
            gender = "FEMALE"
            Log.d("????????? ????????????",sharedManager.getGender().toString())
            binding.fragmentUseriptBtnMail.setBackgroundResource(R.drawable.pink_button)
            binding.fragmentUseriptBtnMail.setTextColor(Color.parseColor("#FFFFFF"))
            gender = "MALE"
        }

        //????????? ?????? ??????
        setupSpinnerGrade()
        setupSpinnerMBTI()
        setupSpinnerHandler()


        binding.fragmentUseriptBtnStore.setOnClickListener {

//            var nickname = binding.fragmentUseriptEtNickname.text.toString()
//            var major = binding.fragmentUseriptBtnSMajor.text.toString()
//            var userbirthday = binding.fragmentUseriptEdtBirth.text.toString()

//            if (isValidNickname_Eng(nickname) == false && isValidNickname_Korea(nickname) == false) {
//                toast("???????????? ????????? ?????????.")
//            } else if (userbirthday == null){
//                toast("??????????????? ????????? ?????????.")
//            } else if (binding.fragmentUseriptBtnSchool.text == "" && binding.fragmentUseriptBtnSchool.text == null ){
//                toast("????????? ????????? ?????????.")
//            }else if (major == ""){
//                toast("????????? ????????? ?????????.")
//            }else if(usergrade == 0){
//                toast("????????? ????????? ?????????.")
//            }else if(usermbti == ""){
//                toast("MBTI??? ????????? ?????????.")
//            }else {
                var userInfo =  UserInfo(
                    birthday = "0000-01-01",
                    gender = "MALE",
                    email = "gracious7272@gmail.com",
                    categoryTag = listOf("CLUB"),
                    grade = 4,
                    regionTag = listOf("BUSAN"),
                    username = "?????????",
                    deptId = "cc9d0c59-58ca-4c1f-9a49-28e6559a5dd8",
                    mbti = "ENTJ"
                )
//                var userInfo =  UserInfo(
//                    birthday = userbirthday,
//                    gender = gender,
//                    email = sharedManager.getEmail(),
//                    categoryTag = events,
//                    grade = usergrade,
//                    regionTag = regions,
//                    username = nickname,
//                    deptId = dept_id,
//                    mbti = usermbti
//                )
                //urn:uuid:

                Log.d("??????????????????","ff")
                Log.d("ddd",userInfo.toString())
                // LoginActivity??? JSON ????????? Presenter?????? ???????????? ??????
                lActivity.getUserIpt(userInfo)
//            }

        }


        binding.fragmentUseriptBtnSchool.setOnClickListener {
            lActivity.setFrag(3)
        }

        binding.fragmentUseriptBtnSMajor.setOnClickListener {
            if (binding.fragmentUseriptBtnSchool.text == "" && binding.fragmentUseriptBtnSchool.text == null)
            {
                toast("????????? ?????? ??????????????????.")
            }else{
                lActivity.setFrag(4)
            }
        }
        activityViewModel.updateschoolname.observe(viewLifecycleOwner, Observer {
            if(it != "") {
                binding.fragmentUseriptBtnSchool.setTextColor(Color.parseColor("#383838"))
                binding.fragmentUseriptBtnSchool.text = it
            }
        })

        activityViewModel.updatemajorname.observe(viewLifecycleOwner, Observer {
            if(it != "") {
                binding.fragmentUseriptBtnSchool.setTextColor(Color.parseColor("#383838"))
                binding.fragmentUseriptBtnSchool.text = it
            }
        })

// ????????????
//        setOnClickListener()
        binding.fragmentUseriptBtnEvent1.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent2.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent3.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent4.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent5.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent6.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent7.setOnClickListener(this)
        binding.fragmentUseriptBtnEvent8.setOnClickListener(this)

//??????
        binding.fragmentUseriptBtnRegion1.setOnClickListener(this)
        binding.fragmentUseriptBtnRegion2.setOnClickListener(this)
        binding.fragmentUseriptBtnRegion3.setOnClickListener(this)
        binding.fragmentUseriptBtnRegion4.setOnClickListener(this)
        binding.fragmentUseriptBtnRegion5.setOnClickListener(this)
        binding.fragmentUseriptBtnRegion6.setOnClickListener(this)
        binding.fragmentUseriptBtnRegion7.setOnClickListener(this)

        val watcherListener = object : TextWatcher {

            // ????????? ?????? ???
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            // ????????? ?????? ??????
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            // ????????? ?????? ?????? ( ????????? ??????)
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    binding.fragmentUseriptEdtBirth.setBackgroundResource(R.drawable.gray_stroke_button)
                } else {
                    binding.fragmentUseriptEdtBirth.setBackgroundResource(R.drawable.gray1_box_6)
                }
            }
        }
    }
    //?????????
    private fun setupSpinnerGrade() {
        val grades = resources.getStringArray(R.array.grade)

        for (i in grades.indices) {
            val grade = SpinnerModel(grades[i])
            listOfGrade.add(grade)
        }
        spinnerAdapterGrade = SpinnerAdapter(requireContext(), R.layout.spinner_item, listOfGrade)
        binding.fragmentUseriptSpnGrade.adapter = spinnerAdapterGrade
    }

    private fun setupSpinnerMBTI() {
        val mbti = resources.getStringArray(R.array.mbti)

        for (i in mbti.indices) {
            val temp = SpinnerModel(mbti[i])
            listOfMBTI.add(temp)
        }

        spinnerAdapterMBTI = SpinnerAdapter(requireContext(), R.layout.spinner_item, listOfMBTI)
        binding.fragmentUseriptSpnMBTI.adapter = spinnerAdapterMBTI
    }

    private fun setupSpinnerHandler() {
        binding.fragmentUseriptSpnGrade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val grade = binding.fragmentUseriptSpnGrade.getItemAtPosition(position) as SpinnerModel
                if (!grade.textString.equals("?????? ??????")) {
                    when (grade.textString){
                        "1??????" -> usergrade = 1
                        "2??????" -> usergrade = 2
                        "3??????" -> usergrade = 3
                        "4??????" -> usergrade = 4
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.fragmentUseriptSpnMBTI.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val mbti = binding.fragmentUseriptSpnMBTI.getItemAtPosition(position) as SpinnerModel
                if (!mbti.textString.equals("MBTI")) {
                    usermbti = "${mbti.textString}"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }


//????????? ???

//    fun isValidNickname_Korea(nickname: String?): Boolean {
//        val trimmedNickname = nickname?.trim().toString()
//        val exp = Regex("^[???-??????-???]{1,8}\$")
//        return !trimmedNickname.isNullOrEmpty() && exp.matches(trimmedNickname)
//    }
//
//    fun isValidNickname_Eng(nickname: String?): Boolean {
//        val trimmedNickname = nickname?.trim().toString()
//        val exp = Regex("^[a-zA-Z]{1,10}\$")
//        return !trimmedNickname.isNullOrEmpty() && exp.matches(trimmedNickname)
//    }

    // ?????????
    fun EditText.setupClearButtonWithAction() {

        var count = 0

        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                val clearIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_edittext_clear else 0
                val warningIcon = if (editable?.isNotEmpty() == true) R.drawable.ic_edittext_warning else 0
                var userinput = binding.fragmentUseriptEtNickname.text.toString().length

                    if (userinput <= 7 ) {
                        binding.fragmentUseriptEtNickname.setBackgroundResource(R.drawable.gray_stroke_button2)
                        binding.fragmentUseriptTv2.visibility = View.INVISIBLE
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
                        count = 0
                        Log.d("?????????","1???")
                        Log.d("?????????",userinput.toString())

                    } else if (userinput == 8 && count >= 0 && 4 > count) {
                        binding.fragmentUseriptEtNickname.setBackgroundResource(R.drawable.gray_stroke_button2)
                        binding.fragmentUseriptTv2.visibility = View.INVISIBLE
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, clearIcon, 0)
                        count = count+ 1
                        Log.d("?????????","2???")
                        Log.d("?????????",count.toString())
                        Log.d("?????????",userinput.toString())
                    } else if (userinput == 8 && count >= 4) {
                        binding.fragmentUseriptEtNickname.setBackgroundResource(R.drawable.gray_stroke_button)
                        binding.fragmentUseriptTv2.visibility = View.VISIBLE
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, warningIcon, 0)
                        Log.d("?????????","3???")
                        Log.d("?????????",count.toString())
                        Log.d("?????????",userinput.toString())


                    }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                Unit
        })

        setOnTouchListener(View.OnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (this.right - this.compoundPaddingRight)) {
                    this.setText("")
                    return@OnTouchListener true
                }else{
                    binding.fragmentUseriptEtNickname.setBackgroundResource(R.drawable.gray_stroke_button2)
                    binding.fragmentUseriptTv2.visibility = View.INVISIBLE
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_edittext_clear, 0)
                }
            }
            return@OnTouchListener false
        })
    }


    //????????????

    override fun onClick(v: View?) {

        when (v?.id) {
            binding.fragmentUseriptBtnEvent1.id
            -> {
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent1.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent1.setTextColor(Color.parseColor("#383838"))

                } else {
                    Log.d("?????????3","?????????333")
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent1.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent1.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnEvent2.id
            -> {
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent2.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent2.setTextColor(Color.parseColor("#383838"))

                } else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent2.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent2.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnEvent3.id
            ->{
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent3.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent3.setTextColor(Color.parseColor("#383838"))

                }  else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent3.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent3.setTextColor(Color.WHITE)
                }
            }
            binding.fragmentUseriptBtnEvent4.id
            ->{
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent4.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent4.setTextColor(Color.parseColor("#383838"))

                } else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent4.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent4.setTextColor(Color.WHITE)
                }
            }
            binding.fragmentUseriptBtnEvent5.id
            ->{
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent5.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent5.setTextColor(Color.parseColor("#383838"))

                }  else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent5.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent5.setTextColor(Color.WHITE)
                }
            }
            binding.fragmentUseriptBtnEvent6.id
            ->{
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent6.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent6.setTextColor(Color.parseColor("#383838"))

                } else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent6.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent6.setTextColor(Color.WHITE)
                }
            }
            binding.fragmentUseriptBtnEvent7.id
            ->{
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent7.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent7.setTextColor(Color.parseColor("#383838"))

                } else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent7.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent7.setTextColor(Color.WHITE)
                }
            }
            binding.fragmentUseriptBtnEvent8.id
            ->{
                if (events.contains(v?.id.toString())) {
                    events.remove(v?.id.toString())
                    binding.fragmentUseriptBtnEvent8.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnEvent8.setTextColor(Color.parseColor("#383838"))

                }  else {
                    events.add(v?.id.toString())
                    binding.fragmentUseriptBtnEvent8.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnEvent8.setTextColor(Color.WHITE)
                }
            }
            binding.fragmentUseriptBtnRegion1.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion1.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion1.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion1.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion1.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnRegion2.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion2.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion2.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion2.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion2.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnRegion3.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion3.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion3.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion3.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion3.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnRegion4.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion4.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion4.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion4.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion4.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnRegion5.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion5.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion5.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion5.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion5.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnRegion6.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion6.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion6.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion6.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion6.setTextColor(Color.WHITE)
                }
            }

            binding.fragmentUseriptBtnRegion7.id
            ->{
                if (regions.contains(v?.id.toString())) {
                    regions.remove(v?.id.toString())
                    binding.fragmentUseriptBtnRegion7.setBackgroundResource(R.drawable.solid_button)
                    binding.fragmentUseriptBtnRegion7.setTextColor(Color.parseColor("#383838"))

                } else {
                    regions.add(v?.id.toString())
                    binding.fragmentUseriptBtnRegion7.setBackgroundResource(R.drawable.pink_solid_button)
                    binding.fragmentUseriptBtnRegion7.setTextColor(Color.WHITE)
                }
            }

        }
    }

    private fun toast(string : String){
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


