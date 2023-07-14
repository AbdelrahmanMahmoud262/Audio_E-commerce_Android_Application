package com.androdevelopment.electronicse_commerce.Adapters

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Layout
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.androdevelopment.electronicse_commerce.Models.FeaturedModel
import com.androdevelopment.electronicse_commerce.Models.NewModel
import com.androdevelopment.electronicse_commerce.databinding.ItemFeaturedBinding
import com.androdevelopment.electronicse_commerce.databinding.ItemNewBinding

class FeaturedAdapter(private var list: List<FeaturedModel>,val onFeaturedClick: OnFeaturedClick) :
    Adapter<FeaturedAdapter.FeaturedViewHolder>() {

    private lateinit var binding: ItemFeaturedBinding

    inner class FeaturedViewHolder(binding: ItemFeaturedBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeaturedViewHolder {
        binding = ItemFeaturedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeaturedViewHolder(binding)
    }

    override fun getItemCount() = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FeaturedViewHolder, position: Int) {

        val item = list[position]

        binding.textViewProductName.text = item.title
        binding.textViewProductPrice.text = "USD " + item.price.toString()

        val bitmap = getBitmapFromEncodedString(item.encodedString)

        if (bitmap != null) binding.imageViewProduct.setImageBitmap(bitmap)

        binding.root.setOnClickListener {
            onFeaturedClick.onItemClick(item)
        }

    }

    private fun getBitmapFromEncodedString(encodedImage: String): Bitmap? {

        val imageBytes = Base64.decode(encodedImage, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return if (encodedImage.isNotEmpty()) {
            decodedImage
        } else {
            null
        }

    }

    interface OnFeaturedClick{
        fun onItemClick(featuredModel: FeaturedModel)
    }
}