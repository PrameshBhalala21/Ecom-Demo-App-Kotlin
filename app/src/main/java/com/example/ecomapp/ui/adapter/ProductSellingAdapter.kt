package com.natureland.ui.fragments.homePage.adapter

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.natureland.R
import com.natureland.databinding.HorizontalProductRowBinding
import com.natureland.ui.fragments.homePage.model.ProductSellingModel


class ProductSellingAdapter(
    val productDataList: ArrayList<ProductSellingModel>,
    val mCon: Context,
    var listeners: ClickListener?
) : RecyclerView.Adapter<ProductSellingAdapter.ViewHolder>() {

    private lateinit var binding: HorizontalProductRowBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.horizontal_product_row, parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return productDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val productModel = productDataList[position]

        if (productModel.isNewProduct!!)
            holder.productRowBinding.linearLayoutNewTextView.visibility = View.VISIBLE
        else
            holder.productRowBinding.linearLayoutNewTextView.visibility = View.GONE

        Glide.with(mCon)
            .load(productModel.productImageUrl)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_natureland_logo)
            )
            .into(holder.productRowBinding.productImageView)


        holder.productRowBinding.productNameTextView.text = productModel.productName
        holder.productRowBinding.sellingPriceTextView.text = productModel.productPrice
        holder.productRowBinding.appCompactRatingBar.rating =
            (productModel.productRating)!!.toFloat()



//        main RelativeLayout onClick Listeners
        holder.productRowBinding.mainRelativeLayout.setOnClickListener {

            listeners?.onClick( productDataList[holder.getAdapterPos()])

        }


        if (productModel.isDiscountProduct!!) {

            holder.productRowBinding.linearLayoutNewTextView.visibility = View.VISIBLE

                try {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        holder.productRowBinding.linearLayoutNewTextView.backgroundTintList =
                            mCon.resources?.getColorStateList(
                                R.color.yellow,
                                mCon.theme
                            )
                    }


                } catch (e: Exception) {
                    Log.d("Exception", e.message)
                }


            holder.productRowBinding.appCompTextViewDiscounts.text =
                productModel.productDiscountPercent

            holder.productRowBinding.basePriceTextView.visibility = View.VISIBLE
            holder.productRowBinding.basePriceTextView.text = productModel.productPrice
            holder.productRowBinding.sellingPriceTextView.text = productModel.productDiscountPrice

            holder.productRowBinding.basePriceTextView.paintFlags =
                holder.productRowBinding.basePriceTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        } else {
//            holder.productRowBinding.linearLayoutNewTextView.visibility = View.GONE
            holder.productRowBinding.basePriceTextView.visibility = View.INVISIBLE
        }

    }


    class ViewHolder(itemView: HorizontalProductRowBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var productRowBinding: HorizontalProductRowBinding = itemView

        fun getAdapterPos() : Int{

            return adapterPosition
        }

    }


    public interface ClickListener {

        fun onClick(productModel: ProductSellingModel)

    }

}