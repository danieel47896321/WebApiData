package com.example.webapidata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.webapidata.myClass.MyExchange
import com.example.webapidata.R

class ExchangeAdapter (list: ArrayList<MyExchange>) : RecyclerView.Adapter<ExchangeAdapter.ViewHolder>() {
    private var exchangeList = ArrayList<MyExchange>()
    init { exchangeList = list }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.exchange_view,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewType.text = exchangeList[position].name
        holder.textViewRate.text = exchangeList[position].rate.toString()
    }
    override fun getItemCount(): Int { return exchangeList.size }
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var textViewType : TextView
        var textViewRate : TextView
        init {
            textViewType = view.findViewById(R.id.textViewType)
            textViewRate = view.findViewById(R.id.textViewRate)
        }
    }
}