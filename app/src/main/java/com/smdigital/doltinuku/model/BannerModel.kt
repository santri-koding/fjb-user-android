package com.smdigital.doltinuku.model

import android.support.annotation.DrawableRes

data class BannerModel(
        val id: Int,
        val link: String,
        @DrawableRes val res: String)