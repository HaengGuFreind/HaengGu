package com.example.haenggu.data.api

import android.content.Context
import com.example.haenggu.login.LoginContract
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class KaKaoAPI : LoginContract.KakaoAPIModel{

    override fun hasToken(): String {  // 로그인 전 [토큰 존재 여부 확인하기]
        var result: String = "False"
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { token, error ->
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        //로그인 필요
                    }
                    else {
                        //기타 에러
                    }
                }
                else {
                    // 토큰 유효성 체크 성공 (필요시 토큰이 갱신된다.)\
                    //액세스 토큰이 유효한 상태이므로 사용자 로그인 불필요
                    //해당 액세스 토큰으로 카카오 API 호출 가능
                    result = token.toString()

                }
            }
        }
        else {
            // 로근인 필요
            result = "NoToken"
        }
        return result
    }

    //[카카오 로그인 구현]
    override fun getLogin(context : Context, hasToken:String):Pair<String,String> {
        var pair = Pair("Fail","default")

        // 로그인 공통 callback
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                // 로그인 서버, 수단등의 에러로인해 로그인 실패
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        pair = Pair("Fail", "접근이 거부 되었습니다.")
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        pair = Pair("Fail", "유효하지 않은 앱을 사용하였습니다.")
                    }
//                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
//                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
//                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
//                    }
//                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
//                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                    error.toString() == AuthErrorCause.ServerError.toString() -> {
//                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
//                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        pair = Pair("Fail", "앱에대한 요청 권한이 없습니다.")
                    }
                }
            }
            else if (token != null) {
                //로그인 성공
                if (hasToken != "NoToken") {
                    // 기존에 회원의 경우 새 토큰 받아오기
                    pair = Pair("Success_Token", token.accessToken)
                } else {
                    // 사용자 정보 확인
//                    UserApiClient.instance.me { user, error ->
//                        if (error != null) {
//                            pair = Pair("Fail", "사용자 정보 요청 실패")
//                        } else if (user != null) {
//                            var scopes = mutableListOf<String>()
//
//                            if (user.kakaoAccount?.emailNeedsAgreement == true) {
//                                scopes.add("account_email")
//                            }
//                            if (user.kakaoAccount?.birthyearNeedsAgreement == true) {
//                                scopes.add("birthyear")
//                            }
//
//                            if (scopes.count() == 2) {
//                                // 회원가입 및 로그인 성공
                    pair = Pair("Success_NoToken", token.accessToken)
//                            }
//                            else {
//                                // 로그인 성공 하였으나 모든 정보를 제공하지 않아, 삭제
//                                pair = Pair("Fail", "회원가입을 위해 정보 제공에 모두 동의해주세요.")
//                                // 로그아웃, API요청의  성공 여부오 ㅏ관계없이 토큰을 삭제처리함
//                                UserApiClient.instance.logout { error ->
//                                    if (error != null) {
////                                        Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
//                                        // 연결 끊기
//                                        UserApiClient.instance.unlink { error ->
//                                            if (error != null) {
////                                                Log.e(TAG, "연결 끊기 실패", error)
//                                            }
//                                            else {
////                                                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
//                                            }
//                                        }
//                                    } else {
////                                        Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
//                                    }
//                                }
//                            }
//                        }
//                    }
                }
            }
        }
        // [카카오톡으로 로그인 구현하기]
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            // [카카오 계정으로 로그인]
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }

        return pair


    }
}