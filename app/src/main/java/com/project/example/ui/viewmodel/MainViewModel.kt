package com.project.example.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.project.example.R
import com.project.example.data.model.Flys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    val flyLiveData = MutableLiveData<Flys>()

    fun getData(context: Context) {
        viewModelScope.launch {
            val loadJsonArray = async { loadJsonArray(context) }
            flyLiveData.value = loadJsonArray.await()
        }

    }


    private suspend fun loadJsonArray(context: Context): Flys? {

        val objectArrayString: String =
            context.resources.openRawResource(R.raw.enuygun_flight_search).bufferedReader()
                .use { it.readText() }
        return Gson().fromJson(objectArrayString, Flys::class.java)
    }


}