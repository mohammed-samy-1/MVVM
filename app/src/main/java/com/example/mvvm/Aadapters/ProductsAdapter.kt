package com.example.mvvm.Aadapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mo_samy.retrofit.R
import com.mo_samy.retrofit.models.Data
import com.mo_samy.retrofit.ui.ProductActivity

class ProductsAdapter(private val context:Context, var items :MutableList<Data>)
    : RecyclerView.Adapter<ProductHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_holder,parent,false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        Glide.with(context)
            .asBitmap()
            .load(items[position].image)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)
        holder.name.text = items[position].name
        holder.price.text = "${ items[position].price.toString() }$"
        holder.ccl.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra("id",items[position].id)
            context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

    class ProductHolder : RecyclerView.ViewHolder{
        var  img :ImageView = itemView.findViewById(R.id.img_list)
        var  name :TextView = itemView.findViewById(R.id.tv_name_list)
        var  price :TextView = itemView.findViewById(R.id.tv_price_list)
        val ccl :ConstraintLayout = itemView.findViewById(R.id.parent)
        constructor(itemView: View) : super(itemView)
    }

