package com.example.webapidata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webapidata.adapter.ExchangeAdapter
import com.example.webapidata.controller.MainController
import com.example.webapidata.model.MainModel

class MainActivity : AppCompatActivity() {
    private lateinit var textViewLastUpdate: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainController: MainController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        mainController = MainController(this)
        textViewLastUpdate = findViewById(R.id.textViewLastUpdate)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        mainController.setView()
    }
    fun mainWhenCase(id: String){
        when(id){
            "setLastView" -> { setLastView(mainController.getLastUpdate()) }
            "setRecyclerView" -> { setProgressBar(mainController.getProgressBarView()) }
            "setProgressBar" -> { setRecyclerView(mainController.getRecyclerView()) }
        }
    }
    private fun setLastView(timeLastUpdateUtc: String) { textViewLastUpdate.text = timeLastUpdateUtc }
    private fun setRecyclerView(exchangeAdapter: ExchangeAdapter) { recyclerView.adapter = exchangeAdapter }
    private fun setProgressBar(view: Int) { progressBar.visibility = view }
}