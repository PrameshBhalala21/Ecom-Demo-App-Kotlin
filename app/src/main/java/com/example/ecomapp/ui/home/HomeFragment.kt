package com.example.ecomapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ecomapp.R
import com.example.ecomapp.databinding.ActivityMainBinding
import com.example.ecomapp.databinding.FragmentHomeBinding
import com.natureland.ui.fragments.homePage.adapter.BannerAdapter
import com.natureland.ui.fragments.homePage.adapter.ProductSellingAdapter
import com.natureland.ui.fragments.homePage.adapter.ShopByCategoryAdapter
import com.natureland.ui.fragments.homePage.model.BannerModel
import com.natureland.ui.fragments.homePage.model.ProductSellingModel

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private  var bannerModel : BannerModel?= null

    private  var bannerAdapter: BannerAdapter?= null

    private var linearLayoutManager: LinearLayoutManager? = null

    private var bannerModelList: ArrayList<BannerModel>? = null

    private  var productModel : ProductSellingModel?= null

    private  var productAdapter: ProductSellingAdapter?= null

    private  var shopByCategoryAdapter: ShopByCategoryAdapter?= null

    private var productModelList1: ArrayList<ProductSellingModel>? = null

    private var productModelList2: ArrayList<ProductSellingModel>? = null

    private var mCon : Context?= null

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        Glide.with(this )
            .load("http://staging.natureland.net/media/banner/offers/existing-arabic.png")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(binding.offerImageView)

        Glide.with(this )
            .load("http://staging.natureland.net/media/banner/existing-organic-pumpkin.png")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(binding.offerTwoImageView)

        return root
    }

    private fun setBannerData( ) {

        linearLayoutManager = LinearLayoutManager(mCon, RecyclerView.HORIZONTAL, false);
        binding.bannerRecyclerView.layoutManager = linearLayoutManager
        binding.bannerRecyclerView.isNestedScrollingEnabled = false

        val snapHelper : SnapHelper = PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.bannerRecyclerView);

        bannerModelList = ArrayList()

        bannerModel = BannerModel("","","http://staging.natureland.net/media/banner/banner-organic-breakfast-1.jpg")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","http://staging.natureland.net/media/banner/discounted-products-img.jpg")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","http://staging.natureland.net/media/banner/existing-organic-pumpkin.png")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","http://staging.natureland.net/media/banner/offers/existing-arabic.png")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","http://staging.natureland.net/media/banner/offers/existing-healthy-break.png")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","http://staging.natureland.net/media/banner/offers/existing-alkaline.png")
        bannerModelList!!.add(bannerModel!!)

        bannerAdapter = BannerAdapter(bannerModelList!!,mCon!!)
        binding.bannerRecyclerView.smoothScrollToPosition(0)
        binding.bannerRecyclerView.adapter = bannerAdapter


    }
}
