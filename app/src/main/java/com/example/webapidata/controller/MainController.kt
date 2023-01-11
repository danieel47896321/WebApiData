package com.example.webapidata.controller

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.webapidata.adapter.ExchangeAdapter
import com.example.webapidata.MainActivity
import com.example.webapidata.myClass.MyExchange
import com.example.webapidata.myClass.Request
import com.example.webapidata.model.MainModel
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainController(var view: MainActivity) {
    private var mainModel: MainModel = ViewModelProvider(view)[MainModel::class.java]
    fun setView() {
        fetchData().start()
    }
    private fun fetchData(): Thread{
        return Thread {
            val url = URL(mainModel.url)
            val connection = url.openConnection() as HttpURLConnection
            if (connection.responseCode == mainModel.responseCode) {
                val input = connection.inputStream
                val inputStreamReader = InputStreamReader(input, mainModel.type)
                mainModel.request = Gson().fromJson(inputStreamReader, Request::class.java)
                updateValues(mainModel.request)
                inputStreamReader.close()
                input.close()
            }
        }
    }
    private fun updateValues(request: Request) {
        view.runOnUiThread{
            kotlin.run{
                view.mainWhenCase(mainModel.lastViewID)
                mainModel.list.add(MyExchange("EUR",request.rates.EUR))
                mainModel.list.add(MyExchange("AOA",request.rates.AOA))
                mainModel.list.add(MyExchange("USD",request.rates.USD))
                mainModel.list.add(MyExchange("ILS",request.rates.ILS))
                mainModel.list.add(MyExchange("AUD",request.rates.AUD))
                mainModel.list.add(MyExchange("AWG",request.rates.AWG))
                mainModel.list.add(MyExchange("AZN",request.rates.AZN))
                mainModel.list.add(MyExchange("BAM",request.rates.BAM))
                mainModel.list.add(MyExchange("PEN",request.rates.PEN))
                mainModel.list.add(MyExchange("RSD",request.rates.RSD))
                mainModel.list.add(MyExchange("PEN",request.rates.PEN))
                view.mainWhenCase(mainModel.progressBarID)
                view.mainWhenCase(mainModel.recyclerViewID)
            }
        }
    }
    fun getLastUpdate(): String { return mainModel.request.time_last_update_utc }
    fun getProgressBarView(): Int { return View.GONE }
    fun getRecyclerView(): ExchangeAdapter { return ExchangeAdapter(mainModel.list) }
}