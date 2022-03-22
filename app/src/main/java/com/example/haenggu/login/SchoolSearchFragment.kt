package com.example.haenggu.login

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.haenggu.R
import com.example.haenggu.databinding.FragmentLoginSearchschoolBinding
import androidx.appcompat.widget.SearchView

class SchoolSearchFragment : Fragment() {

    private var _binding: FragmentLoginSearchschoolBinding? = null
    private val binding get() = _binding!!

    var schoolName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginSearchschoolBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lActivity = activity as LoginActivity

        binding.fragmentSearchschoolBtnbackstep.setOnClickListener {
            Log.d("검색", "2")
            val lActivity = activity as LoginActivity
            lActivity.getsinfo(schoolName)
            Log.d("검색", "3")
            var fragment_useript = UserIptFragment()
            var bundle = Bundle()
            bundle.putString("schoolname", schoolName)
            fragment_useript.arguments = bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.frameLayout_login, fragment_useript)
                .addToBackStack(null)
                .commit()

//            activity?.supportFragmentManager!!
//                .beginTransaction()
//                .replace(R.id.frameLayout_login, fragment_useript)
//                .commit()
        }

        var school = arrayOf("가톨릭대학교","건국대학교","경기대학교","경희대학교","고려대학교","광운대학교","국민대학교","동국대학교", "명지대학교","삼육대학교"
                ,"상명대학교","서강대학교","서경대학교","서울대학교","서울과학기술대학교","서울시립대학교","성공회대학교","성균관대학교","세종대학교","숭실대학교"
                ,"연세대학교","중앙대학교","총신대학교","케이씨대학교[1]","한국외국어대학교","한성대학교","한양대학교","홍익대학교","경성대학교","고신대학교"
                ,"동명대학교","동서대학교","동아대학교","동의대학교","부경대학교","부산대학교","부산외국어대학교","신라대학교","영산대학교","인제대학교"
                ,"한국해양대학교","경북대학교","계명대학교","대구가톨릭대학교","대구대학교","영남대학교","가천대학교","안양대학교","연세대학교","인천대학교"
                ,"인하대학교","청운대학교","광신대학교","광주대학교","남부대학교","송원대학교","전남대학교","조선대학교","호남대학교","건양대학교","대전대학교"
                ,"목원대학교","배재대학교","우송대학교","을지대학교","충남대학교","한남대학교","한밭대학교","울산대학교","고려대학교 세종캠퍼스","홍익대학교"
                ,"가천대학교","가톨릭대학교","강남대학교", "경기대학교","경동대학교","경희대학교","단국대학교","대진대학교","동국대학교","명지대학교"
                ,"성균관대학교","수원대학교","신경대학교","신한대학교","아주대학교","안양대학교","용인대학교","을지대학교","중부대학교","중앙대학교"
                ,"평택대학교","한경대학교","한국공학대학교","한국교통대학교", "한국외국어대학교","한국항공대학교","한세대학교","한신대학교","한양대학교 ERICA캠퍼스"
                ,"협성대학교","덕성여자대학교","동덕여자대학교","서울여자대학교","성신여자대학교","숙명여자대학교","이화여자대학교", "광주여자대학교"
        )

        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,school)
        //리스트뷰 초기에 안보이게 설정
        binding.fragmentSearchschoolListView.visibility = View.INVISIBLE
        //SearchBar와 ListView 연동
        binding.fragmentSearchschoolListView.adapter = adapter

        binding.fragmentSearchschoolSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.fragmentSearchschoolSearchBar.clearFocus()
                if(school.contains(query)){
                    adapter.filter.filter(query)
                    if (query != null) {
                        schoolName = query
                    }
                }else{
                    Toast.makeText(requireContext(),"일치하는 학교가 없습니다.",Toast.LENGTH_SHORT)
                }
                binding.fragmentSearchschoolListView.visibility = View.INVISIBLE
                // 데이터에 없는 학교일 경우 직접입력 가능?
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                binding.fragmentSearchschoolListView.visibility = View.VISIBLE
                return false
            }

        })

        binding.fragmentSearchschoolListView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            var selection = parent.getItemAtPosition(position).toString()
            binding.fragmentSearchschoolSearchBar.setQuery(selection,true)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}