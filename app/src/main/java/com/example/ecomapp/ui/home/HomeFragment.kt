package com.example.ecomapp.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ecomapp.R
import com.example.ecomapp.databinding.FragmentHomeBinding
import com.example.ecomapp.ui.adapter.BannerAdapter
import com.example.ecomapp.ui.adapter.ProductSellingAdapter
import com.example.ecomapp.ui.model.BannerModel
import com.example.ecomapp.ui.model.ProductSellingModel


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private  var bannerModel : BannerModel?= null

    private  var bannerAdapter: BannerAdapter?= null

    private var linearLayoutManager: LinearLayoutManager? = null

    private var bannerModelList: ArrayList<BannerModel>? = null

    private  var productModel : ProductSellingModel?= null

    private  var productAdapter: ProductSellingAdapter?= null

//    private  var shopByCategoryAdapter: ShopByCategoryAdapter?= null

    private var productModelList1: ArrayList<ProductSellingModel>? = null

    private var productModelList: ArrayList<ProductSellingModel>? = null

    private var mCon : Context?= null

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        var root = binding.root
        mCon = activity


        setProductListData()

        setBannerData()

        Glide.with(this )
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsFR4MmVF89UqZP1I4MU-NE_hj6G5E1dGDYIl2IYGTX1QJiuuk&s")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(binding.offerImageView)

        Glide.with(this )
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTJVAUPpkR5NXaYa09ZKSIL9hQdeQoLMBWuhFOgmlAR6_2A5OY9")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(binding.offerTwoImageView)

        Glide.with(mCon!!)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTrSMnQpxR6hxx8K3MaObiUxKSo5ev9mf62wTf8D_Io6j7oP6As")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(binding.offerImageViewThree)

        Glide.with(mCon!!)
            .load("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTNizNNmkiclIZLXxmdPUXvTuWyyddqetr02e2K9u7GzY5CjfgK")
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading)
            )
            .into(binding.offerImageViewFour)

        return root
    }

    private fun setProductListData() {


        linearLayoutManager = LinearLayoutManager(mCon, RecyclerView.HORIZONTAL, false);
        binding.bestSellingRecyclerView.layoutManager = linearLayoutManager
        binding.bestSellingRecyclerView.isNestedScrollingEnabled = false

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.upComingProductRecyclerView.layoutManager = staggeredGridLayoutManager
        binding.upComingProductRecyclerView.isNestedScrollingEnabled = false

        productModelList = ArrayList()

        productModel = ProductSellingModel("data:image/jpeg;base64,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","1","Lemons/cold Drink/ Fanta/ Thums Up (300gm,bottle Pack)","Rs. 140","Rs. 125","-15%","2",false,true)
        productModelList!!.add(productModel!!)

        productModel = ProductSellingModel("https://us.123rf.com/450wm/monticello/monticello1804/monticello180400265/103002442-poznan-poland-apr-6-2018-bottles-of-global-soft-drink-brands-including-products-of-coca-cola-company.jpg?ver=6","1","Cold Sprite Soft Drink Stock Photos And Images - 123RF","Rs. 245","Rs. 200","-10%","5",false,true)
        productModelList!!.add(productModel!!)

        productModel = ProductSellingModel("https://amasauda.com/pub/media/catalog/product/cache/e4d64343b1bc593f1c5348fe05efa4a6/t/h/thumbsup_400ml.jpg","1","Thumbs Up Cold Drink (400ml)","Rs. 200","Rs. 180","-20%","4",false,true)
        productModelList!!.add(productModel!!)

        productModel = ProductSellingModel("https://cakefizz.com/wp-content/uploads/2019/06/CF1711.jpg","1","1 L Sprite Cold Drink (CF1711)","Rs. 225","Rs. 200","-18%","3",false,true)
        productModelList!!.add(productModel!!)

        productModel = ProductSellingModel("https://sherebengal.com/wp-content/uploads/2017/09/Cold-drinks-200ml.jpg","1","Cold Drinks 200ml","Rs. 215","Rs. 195","-25%","4",false,true)
        productModelList!!.add(productModel!!)

        productModel = ProductSellingModel("https://4.imimg.com/data4/NM/LU/MY-29609186/chilled-cold-drink-500x500.jpg","1","Chilled Cold Drink","Rs. 250","","","5",false)
        productModelList!!.add(productModel!!)


        productModelList1 = ArrayList()

        productModel = ProductSellingModel("https://amasauda.com/pub/media/catalog/product/cache/e4d64343b1bc593f1c5348fe05efa4a6/m/a/maaza_600ml.jpg","1","Maaza Cold Drink (600ml) ","Rs. 240","KWD 1.200","-5%","1",false,false,true)
        productModelList1!!.add(productModel!!)

        productModel = ProductSellingModel("https://media.gettyimages.com/photos/classical-cocacola-bottle-picture-id157726102?s=612x612","1","Cold Drink Stock Pictures, Royalty-free Photos & Images - Getty Images","Rs. 510","","","2",false,false,true)
        productModelList1!!.add(productModel!!)

        productModel = ProductSellingModel("https://www.basketor.com/pub/media/catalog/product/cache/9c625a5e4f7ab3c97b131c674665ff0a/b/1/b180000658_1.jpg","1","Thums Up Cold Drink,2Ltr","Rs. 110","","","2",false,false,true)
        productModelList1!!.add(productModel!!)

        productModel = ProductSellingModel("https://cpimg.tistatic.com/03074758/s/4/Cold-Drink.jpg","1","Cold Drink - Cold Drink Manufacturer, Supplier, Trading Company ...","Rs. 225","","","3",false,false,true)
        productModelList1!!.add(productModel!!)

        productModel = ProductSellingModel("https://5.imimg.com/data5/ZK/NE/CB/SELLER-59097240/1-5l-sprite-cold-drink-500x500.jpg","1","1.5l Sprite Cold Drink","Rs. 215","","","4",false,false,true)
        productModelList1!!.add(productModel!!)

        productModel = ProductSellingModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSF5GoJ1uGqGQVtSyZIz9ETP4qz8b1Pr88iud22iLFnEaW5qyeC_A&s","1","Mountain Dew Soft Drink / Cold Drink, 1.25 LTR","Rs. 220","","","2",false,false,true)
        productModelList1!!.add(productModel!!)



        productAdapter = ProductSellingAdapter(productModelList1!!,mCon!! )
        binding.upComingProductRecyclerView.smoothScrollToPosition(0)
        binding.upComingProductRecyclerView.adapter = productAdapter

        productAdapter = ProductSellingAdapter(productModelList!!,mCon!! )
        binding.bestSellingRecyclerView.smoothScrollToPosition(0)
        binding.bestSellingRecyclerView.adapter = productAdapter



    }

    private fun setBannerData( ) {

        linearLayoutManager = LinearLayoutManager(mCon, RecyclerView.HORIZONTAL, false);
        binding.bannerRecyclerView.layoutManager = linearLayoutManager
        binding.bannerRecyclerView.isNestedScrollingEnabled = false

        val snapHelper : SnapHelper = PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.bannerRecyclerView);

        bannerModelList = ArrayList()


        bannerModel = BannerModel("","","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTyUdrI0WXKZwzElRsMa3yNpy26TcD1Dh6SkpZ0Vc01k9ogsS7L&usqp=CAU")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSJPnEHGXQ69AQ2E1PWvo-5EUjz2-pZPWQ812RUnaXA9R-Cafmo&usqp=CAU")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSqbkTaNnDJYw486FppIkKXyuL4_ow0Nj0nnowyLdjUFZhPfcjP&usqp=CAU")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSNyOYEcHn3CpxU6Mw8jN3OpsJxNX3H8hfGrLPCA7wMm5D9PXac&usqp=CAU")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","https://us.123rf.com/450wm/monticello/monticello1804/monticello180400265/103002442-poznan-poland-apr-6-2018-bottles-of-global-soft-drink-brands-including-products-of-coca-cola-company.jpg?ver=6")
        bannerModelList!!.add(bannerModel!!)

        bannerModel = BannerModel("","","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRR_ELHaQ99bcyvAxWrk1V3nNjmbpIkm4bWPpADP3IJZQBwTawo&usqp=CAU")
        bannerModelList!!.add(bannerModel!!)

        bannerAdapter = BannerAdapter(bannerModelList!!,mCon!!)
        binding.bannerRecyclerView.smoothScrollToPosition(0)
        binding.bannerRecyclerView.adapter = bannerAdapter


    }
}
