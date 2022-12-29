package com.example.webapidata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var textViewLastUpdate: TextView
    private lateinit var recyclerView: RecyclerView
    private var list = ArrayList<MyExchange>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        textViewLastUpdate = findViewById(R.id.textViewLastUpdate)
        recyclerView = findViewById(R.id.recyclerView)
        fetchData().start()
    }
    private fun fetchData(): Thread{
        return Thread {
            val url = URL("https://open.er-api.com/v6/latest/aud")
            val connection = url.openConnection() as HttpURLConnection
            if (connection.responseCode == 200) {
                val input = connection.inputStream
                val inputStreamReader = InputStreamReader(input, "UTF-8")
                val request = Gson().fromJson(inputStreamReader, Request::class.java)
                updateValues(request)
                inputStreamReader.close()
                input.close()
            }
        }
    }
    private fun updateValues(request: Request) {
        runOnUiThread{
            kotlin.run{
                textViewLastUpdate.text = request.time_last_update_utc
                list.add(MyExchange("EUR",request.rates.EUR))
                list.add(MyExchange("AOA",request.rates.AOA))
                list.add(MyExchange("USD",request.rates.USD))
                list.add(MyExchange("ILS",request.rates.ILS))
                list.add(MyExchange("AUD",request.rates.AUD))
                list.add(MyExchange("AWG",request.rates.AWG))
                list.add(MyExchange("AZN",request.rates.AZN))
                list.add(MyExchange("BAM",request.rates.BAM))
                list.add(MyExchange("PEN",request.rates.PEN))
                list.add(MyExchange("RSD",request.rates.RSD))
                list.add(MyExchange("PEN",request.rates.PEN))
                val exchangeAdapter = ExchangeAdapter(list)
                recyclerView.layoutManager = GridLayoutManager(this, 1)
                recyclerView.adapter = exchangeAdapter
            }
        }
    }
}