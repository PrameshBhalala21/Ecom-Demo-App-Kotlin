package com.natureland.ui.fragments.homePage.model

import com.google.gson.annotations.SerializedName

data class ProductSellingModel(

    @SerializedName("productImageUrl") val productImageUrl: String? = null,

    @SerializedName("productId") val productId: String? = null,

    @SerializedName("productName") val productName: String? = null,

    @SerializedName("productPrice") val productPrice: String? = null,

    @SerializedName("productDiscountPrice") val productDiscountPrice: String? = null,

    @SerializedName("productDiscountPercent") val productDiscountPercent: String? = null,

    @SerializedName("productRating") val productRating: String? = null,

    @SerializedName("productNew") val isNewProduct: Boolean? = false,

    @SerializedName("discountProduct") val isDiscountProduct: Boolean? = false

)