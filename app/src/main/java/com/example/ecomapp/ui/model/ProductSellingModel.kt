package com.example.ecomapp.ui.model


data class ProductSellingModel(

    val productImageUrl: String? = null,

    val productId: String? = null,

    val productName: String? = null,

    val productPrice: String? = null,

    val productDiscountPrice: String? = null,

    val productDiscountPercent: String? = null,

    val productRating: String? = null,

    val isNewProduct: Boolean? = false,

    val isDiscountProduct: Boolean? = false,

    val isRemoveButton: Boolean? = false



)