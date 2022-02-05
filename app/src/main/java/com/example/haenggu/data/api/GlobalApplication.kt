package com.example.haenggu.data.api

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 다른 초기화 코드들

        // Kakao SDK 초기화
        KakaoSdk.init(this, "{ebbf1d1a2bb30b68d03a94b1a115ddef}")
    }
}