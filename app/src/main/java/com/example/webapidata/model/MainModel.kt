package com.example.webapidata.model

import androidx.lifecycle.ViewModel
import com.example.webapidata.myClass.MyExchange
import com.example.webapidata.myClass.Request

class MainModel: ViewModel() {
    var list = ArrayList<MyExchange>()
    var url = "https://open.er-api.com/v6/latest/aud"
    var type = "UTF-8"
    var responseCode = 200
    lateinit var request: Request
    var lastViewID = "setLastView"
    var recyclerViewID = "setRecyclerView"
    var progressBarID = "setProgressBar"
}