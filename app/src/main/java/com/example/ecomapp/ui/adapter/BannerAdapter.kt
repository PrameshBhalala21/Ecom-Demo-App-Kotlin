package com.example.ecomapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ecomapp.R
import com.example.ecomapp.databinding.BannerItemsBinding
import com.example.ecomapp.ui.model.BannerModel


class BannerAdapter(val bannerDataList : ArrayList<BannerModel>, val mCon : Context)
    :RecyclerView.Adapter<BannerAdapter.ViewHolder>()  {


    private lateinit var binding : BannerItemsBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        //To change body of created functions use File | Settings | File Templates.
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_product_row, parent, false)
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.banner_items,parent,false)

        var view = binding.root

        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        //To change body of created functions use File | Settings | File Templates.
        return bannerDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //To change body of created functions use File | Settings | File Templates.
        val bannerModel = bannerDataList[position]



        Glide.with(mCon)
            .load(bannerModel.banner_Image)
            .apply(
                    RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(holder.bannerRowBinding.bannerImageView)


    }


     class ViewHolder(itemView: BannerItemsBinding)
         : RecyclerView.ViewHolder(itemView.root) {

        var bannerRowBinding: BannerItemsBinding = itemView

     }





  /*  class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


        fun bindItems(bannerModel: BannerModel){

            itemView

        }


    }
*/

}