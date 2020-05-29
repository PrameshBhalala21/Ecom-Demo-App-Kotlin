package com.natureland.ui.fragments.homePage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.natureland.R
import com.natureland.databinding.CustomRoundImageviewBinding
import com.natureland.ui.fragments.homePage.model.ProductSellingModel

class ShopByCategoryAdapter(
    private val categoryProductImageDataList: ArrayList<ProductSellingModel>,
    private val mCon: Context
) : RecyclerView.Adapter<ShopByCategoryAdapter.ViewHolder>() {

    private lateinit var binding: CustomRoundImageviewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.custom_round_imageview, parent, false
        )

        return ViewHolder(binding)


    }

    override fun getItemCount(): Int {

        return categoryProductImageDataList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        if (categoryProductImageDataList != null) {

            val productModel = categoryProductImageDataList[position]

            Glide.with(mCon)
                .load(productModel.productImageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.ic_natureland_logo)
                )
                .into(holder.productRowBinding.appCompactImageViewShopByCategory)


        }


    }


    class ViewHolder(itemView: CustomRoundImageviewBinding)

        : RecyclerView.ViewHolder(itemView.root) {

        var productRowBinding: CustomRoundImageviewBinding = itemView

    }
}