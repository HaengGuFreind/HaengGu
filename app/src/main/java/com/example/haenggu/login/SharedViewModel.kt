package com.example.haenggu.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    private val _updateschoolname = MutableLiveData<String>("")
    val updateschoolname: LiveData<String> get() = _updateschoolname

    fun updateSchool(schoolname:String) {
        _updateschoolname.value = schoolname
    }

    private val _updatemajorname = MutableLiveData<String>("")
    val updatemajorname: LiveData<String> get() = _updatemajorname

    fun updateMajor(majorname:String) {
        _updatemajorname.value = majorname
    }
}