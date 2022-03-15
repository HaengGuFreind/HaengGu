package com.example.haenggu.main.home

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.haenggu.R
import com.example.haenggu.databinding.FragmentEventFrilterBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EventFilterFragment() : BottomSheetDialogFragment() {

    private lateinit var _binding: FragmentEventFrilterBinding
    //private val binding get() = _binding!!
    private val item = listOf("동아리행사", "박람회", "컨퍼런스", "연극/뮤지컬", "전시", "페스티벌", "콘서트", "기타")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEventFrilterBinding.inflate(inflater, container, false)

        initSpinner()
        return _binding.root
    }

    private fun initSpinner() {
//        val spinner = _binding.spTag1
//        val adapter = ArrayAdapter(context as CategoryActivity, R.layout.item_spinner, item)
//        spinner.adapter = adapter
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
//        dialog.setOnShowListener {
//            val bottomSheetDialog = it as BottomSheetDialog
//            // 다이얼로그 크기 설정 (인자값 : DialogInterface)
//            setupRatio(bottomSheetDialog)
//        }

        return dialog
    }

//    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
//        val bottomSheet = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
//        val behavior = BottomSheetBehavior.from<View>(bottomSheet)
//        val layoutParams = bottomSheet!!.layoutParams
//        layoutParams.height = getBottomSheetDialogDefaultHeight()
//        bottomSheet.layoutParams = layoutParams
//        behavior.state = BottomSheetBehavior.STATE_EXPANDED
//    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EventFilterFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}