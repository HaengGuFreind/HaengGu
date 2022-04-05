package com.example.haenggu.login

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.haenggu.R
import com.example.haenggu.data.remote.datasources.SchoolItem
import com.example.haenggu.databinding.FragmentLoginMajorBinding

class MajorSearchFragment() : Fragment() {

    private var _binding: FragmentLoginMajorBinding? = null
    private val binding get() = _binding!!
    var majorname: String = ""
    var majorlist= ArrayList<String>()
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginMajorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lActivity = activity as LoginActivity
        var pair = lActivity.getmlist()
        var mlist = ArrayList<SchoolItem>()
//        val schoolName = lActivity.get_school()
        if(pair?.first == true) {
            mlist = pair.second


            for (i: Int in 0 until mlist.size)
                majorlist.add(i,mlist[i].deptName)
            Log.d("우르롹끼",majorlist.toString())
        }else{
            if (count == 0){
                count = count + 1
            }else{
            Toast.makeText(requireContext(),"학교를 먼저 선택해주세요",Toast.LENGTH_SHORT).show()
            }
        }

        binding.fragmentMajorBtnbackstep.setOnClickListener{
            lActivity.setFrag(5)
//            var fragment_useript = UserIptFragment()
//            var bundle = Bundle()
//            bundle.putString("schoolname", schoolName)
//            if (majorname  != ""){
//                var dept_id = mlist[majorlist.indexOf(majorname)].deptId
//                bundle.putString("dept_id",dept_id)
//            }
//            bundle.putString("majorname",majorname)
//            fragment_useript.arguments = bundle //fragment의 arguments에 데이터를 담은 bundle을 넘겨줌

//            activity?.supportFragmentManager!!
//                .beginTransaction()
//                .replace(R.id.frameLayout_login, fragment_useript)
//                .commit()
//
//            parentFragmentManager
//                .beginTransaction()
//                .replace(R.id.frameLayout_login, fragment_useript)
//                .commit()
        }

        // 서버에서 받아오는걸로 변경

        var major= majorlist.toArray(arrayOfNulls<String>(majorlist.size))
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, major)
        //리스트뷰 초기에 안보이게 설정
        binding.fragmentMajorListView.visibility = View.INVISIBLE
        //SearchBar와 ListView 연동
        binding.fragmentMajorListView.adapter = adapter

        binding.fragmentMajorSearchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.fragmentMajorSearchBar.clearFocus()
                if(major.contains(query)){
                    adapter.filter.filter(query)
                    if (query != null) {
                        majorname = query
                    }
                }else{
                    Toast.makeText(requireContext(),"일치하는 학과가 없습니다.", Toast.LENGTH_SHORT)
                }
                binding.fragmentMajorListView.visibility = View.INVISIBLE
                // 데이터에 없는 학교일 경우 직접입력 가능?
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                binding.fragmentMajorListView.visibility = View.VISIBLE
                return false
            }

        })

        binding.fragmentMajorListView.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->
            var selection = parent.getItemAtPosition(position).toString()
            binding.fragmentMajorSearchBar.setQuery(selection,true)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}