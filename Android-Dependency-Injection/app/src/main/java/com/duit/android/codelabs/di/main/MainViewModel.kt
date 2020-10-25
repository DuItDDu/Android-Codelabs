package com.duit.android.codelabs.di.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duit.android.codelabs.di.model.Data
import com.duit.android.codelabs.di.repository.DataRepository

class MainViewModel @ViewModelInject constructor(
    private val repository: DataRepository
) : ViewModel() {

    private val _dataList: MutableLiveData<List<Data>> = MutableLiveData()
    val dataList: LiveData<List<Data>> = _dataList


    fun loadDataList() {
        _dataList.postValue(repository.loadDataList() ?: emptyList())
    }

    fun saveData(data: Data) {
        repository.saveData(data).let {
            if(it) {
                loadDataList()
            }
        }
    }
}