package com.androdevelopment.electronicse_commerce.Adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Layout
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.androdevelopment.electronicse_commerce.Models.NewModel
import com.androdevelopment.electronicse_commerce.databinding.ItemNewBinding

class NewAdapter(private var list:List<NewModel>, val onNewClick:OnNewClick) : Adapter<NewAdapter.NewViewHolder>() {

    private lateinit var binding: ItemNewBinding

    inner class NewViewHolder(binding: ItemNewBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        binding = ItemNewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {

        val item = list[position]

        binding.textViewNewTitle.text = item.title

        val bitmap = getBitmapFromEncodedString(item.encodedString)

        if (bitmap != null) binding.imageView2.setImageBitmap(bitmap)

        binding.root.setOnClickListener{
            onNewClick.onItemClick(item)
        }


    }

    private fun getBitmapFromEncodedString(encodedImage: String): Bitmap? {

        val imageBytes = Base64.decode(encodedImage, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return if (encodedImage.isNotEmpty()){
            decodedImage
        }else{
            null
        }

    }

    interface OnNewClick{
        fun onItemClick(newModel: NewModel)
    }
}