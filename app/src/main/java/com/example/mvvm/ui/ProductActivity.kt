package com.example.mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.adapters.ProductsAdapter
import com.example.mvvm.model.Data
import com.example.mvvm.viewmodel.ProductVM


class ProductActivity : AppCompatActivity() {
    private lateinit var rv :RecyclerView
    private lateinit var adapter:ProductsAdapter
    private lateinit var progressBar: ProgressBar
    private val model :ProductVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        model.getProduct()
        model.productsLD.observe(this ) {
            initRv(it)
        }
        model.errorLiveData.observe(this) {
            Toast.makeText(this, it , Toast.LENGTH_LONG).show()
        }

    }

    private fun initViews() {
        rv = findViewById(R.id.rv_products)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun initRv(products : MutableList<Data>){
        progressBar.visibility = View.GONE
        rv.visibility = View.VISIBLE
        adapter= ProductsAdapter(this, products)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
    }

    override fun onDestroy() {
        super.onDestroy()
        model.productsLD.removeObservers(this)
        model.errorLiveData.removeObservers(this)
    }
}