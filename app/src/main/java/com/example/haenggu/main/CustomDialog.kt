package com.example.haenggu.main

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.haenggu.R
import com.example.haenggu.databinding.CustomDialogBinding

class CustomDialog(context : Context) {
    private val dlg: Dialog = Dialog(context)   //부모 액티비티의 context 가 들어감
    private lateinit var tvContent: TextView
    private lateinit var btnOK : Button
    private lateinit var btnCancel : Button
    private lateinit var listener : MyDialogOKClickedListener

    fun start(content: String, yesText: String, noText: String) {
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)   //타이틀바 제거
        dlg.setContentView(R.layout.custom_dialog)     //다이얼로그에 사용할 xml 파일을 불러옴
        dlg.setCancelable(false)    //다이얼로그의 바깥 화면을 눌렀을 때 다이얼로그가 닫히지 않도록 함
        dlg?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) //색 투명하게
        //dlg?.window?.requestFeature(Window.FEATURE_NO_TITLE)

        tvContent = dlg.findViewById(R.id.dialog_text)
        tvContent.text = content
        btnOK = dlg.findViewById(R.id.dialog_btn_yes)
        btnOK.text = yesText
        btnOK.setOnClickListener {
            listener.onOKClicked(false)
            dlg.dismiss()
        }

        btnCancel = dlg.findViewById(R.id.dialog_btn_no)
        btnCancel.text = noText
        btnCancel.setOnClickListener {
            dlg.dismiss()
        }

        dlg.show()
    }


    fun setOnOKClickedListener(listener: (Boolean) -> Unit) {
        this.listener = object: MyDialogOKClickedListener {
            override fun onOKClicked(content: Boolean) {
                listener(content)
            }
        }
    }


    interface MyDialogOKClickedListener {
        fun onOKClicked(content : Boolean)
    }
}