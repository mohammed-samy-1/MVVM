package com.example.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.api.RetrofitFactory
import com.example.mvvm.model.Data
import com.example.mvvm.model.DataModel
import com.example.mvvm.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductVM : ViewModel() {
    private val productsMLD: MutableLiveData<ArrayList<Data>> = MutableLiveData()
    val productsLD: LiveData<ArrayList<Data>> get() = productsMLD
    private val productMLD: MutableLiveData<Data> = MutableLiveData()
    val productLD: LiveData<Data> get() = productMLD
    private var errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = errorMessage


    fun getProduct() {
        val conn = RetrofitFactory.call
        val call = conn.getProducts()
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
                when (response!!.code()) {
                    200 -> productsMLD.postValue(response.body().data as ArrayList<Data>?)
                    else -> errorMessage.postValue(response.code().toString())
                }
            }

            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                errorMessage.postValue(t?.localizedMessage)
            }

        })

    }

    fun getProductById(id:Int) {
        val conn = RetrofitFactory.call
        val call = conn.getProductById(id)
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>?, response: Response<Product>?) {
                when (response!!.code()) {
                    200 -> productMLD.postValue(response.body().data)
                    else -> errorMessage.postValue(response.code().toString())
                }
            }

            override fun onFailure(call: Call<Product>?, t: Throwable?) {
                errorMessage.postValue(t?.localizedMessage)
            }

        })
    }
}