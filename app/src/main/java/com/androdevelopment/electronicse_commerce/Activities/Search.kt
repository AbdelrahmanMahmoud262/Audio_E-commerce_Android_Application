package com.androdevelopment.electronicse_commerce.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androdevelopment.electronicse_commerce.Adapters.HistoryAdapter
import com.androdevelopment.electronicse_commerce.Database.Helper
import com.androdevelopment.electronicse_commerce.Models.HistoryModel
import com.androdevelopment.electronicse_commerce.databinding.ActivitySearchBinding

class Search : AppCompatActivity(), HistoryAdapter.OnHistoryClick {

    private lateinit var binding:ActivitySearchBinding

    private lateinit var historyAdapter:HistoryAdapter
    private val historyList = arrayListOf<HistoryModel>()
    private val helper: Helper = Helper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecycler()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val model = HistoryModel(query)
                val size = historyList.size

                historyList.removeAll(helper.getHistory().toSet())
                historyAdapter.notifyItemRangeRemoved(0,size)

                helper.addHistory(model)
                historyList.addAll(helper.getHistory())
                historyAdapter.notifyItemRangeInserted(0,historyList.size)

//                historyList.add(0,model)
//                historyAdapter.notifyItemInserted(0)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun setUpRecycler() {
        historyAdapter = HistoryAdapter(historyList,this)


        historyList.addAll(helper.getHistory())

        binding.recyclerHistory.apply{
            layoutManager = LinearLayoutManager(this@Search,LinearLayoutManager.VERTICAL,false)
            adapter = historyAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onDeleteClick(item: HistoryModel) {
        Toast.makeText(this,"Sdfs",Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        binding.searchView.requestFocus()
        binding.searchView.setQuery(intent.getStringExtra("q"),true)
    }
}