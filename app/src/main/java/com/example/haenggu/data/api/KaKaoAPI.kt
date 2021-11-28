package com.example.haenggu.data.api

import android.content.Context
import com.example.haenggu.login.LoginContract
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient

class KaKaoAPI : LoginContract.KakaoAPIModel{

    override fun hasToken(): String {
        var result: String = "False"
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { token, error ->
                if (error != null) {
//                    result =  "False"
                }
                else {
                    result = token.toString()

                }
            }
        }
        else {
            result = "NoToken"
        }
        return result
    }

    override fun getLogin(context : Context,hasToken:String):Pair<String,String> {
        var pair = Pair("Fail","default")
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
                    pair = Pair("Success_Token", token.accessToken)
                } else {
                    // 사용자 정보 확인
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            pair = Pair("Fail", "사용자 정보 요청 실패")
                        } else if (user != null) {
                            var scopes = mutableListOf<String>()

                            if (user.kakaoAccount?.emailNeedsAgreement == true) {
                                scopes.add("account_email")
                            }
                            if (user.kakaoAccount?.birthyearNeedsAgreement == true) {
                                scopes.add("birthyear")
                            }

                            if (scopes.count() == 2) {
                                // 회원가입 및 로그인 성공
                                pair = Pair("Success_NoToken", token.accessToken)
                            }
                            else {
                                pair = Pair("Fail", "회원가입을 위해 정보 제공에 모두 동의해주세요.")
                                UserApiClient.instance.logout { error ->
                                    if (error != null) {
//                                        Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                                    } else {
//                                        Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                                    }
                                }
                                //서버에 탈퇴 요청하기 - 데이터 삭제를 위해
                            }
                        }
                    }
                }
            }
        }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }

        return pair


    }

//    override fun hasToken(): String {
//        var result: String = "False"
//        if (AuthApiClient.instance.hasToken()) {
//            UserApiClient.instance.accessTokenInfo { token, error ->
//                if (error != null) {
////                    result =  "False"
//                }
//                else {
//                    //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
//                    // 메인으로 가야지서버에 토큰 보내서 Authtoken받아와야지
//                    result = token.toString()
//
//                }
//            }
//        }
//        else {
//            result = "NoToken"
//        }
//        return result
//    }
//
//    override fun getLogin(context : Context):String {
//        var result :String = "Fail"
//        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
//            if (error != null) {
//                // 로그인 실패
//
////                when {
////                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
////                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
////                    }
////                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
////                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
////                    }
////                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
////                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT)
////                            .show()
////                    }
////                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
////                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
////                    }
////                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
////                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
////                    }
////                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
////                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT)
////                            .show()
////                    }
////                    error.toString() == AuthErrorCause.ServerError.toString() -> {
////                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
////                    }
////                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
////                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
////                    }
////                    else -> { // Unknown
////                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
////                    }
////                }
//                result = "Fail"
//            }
//            else if (token != null) {
//                // 사용자 정보 요청 (기본)
//                UserApiClient.instance.me { user, error ->
//                    if (error != null) {
//                        result = "사용자 정보 요청 실패"
//                        //Log.e(TAG, "사용자 정보 요청 실패", error)
//                    }
//                    else if (user != null) {
//                        var scopes = mutableListOf<String>()
//
//                        if (user.kakaoAccount?.emailNeedsAgreement == true) { scopes.add("account_email") }
//                        if (user.kakaoAccount?.birthyearNeedsAgreement == true) { scopes.add("birthyear") }
//
//                        if (scopes.count() == 2 ) {
//                            // 회원가입 및 로그인 성공
//                            result = token.accessToken
//
//
////                            UserApiClient.instance.loginWithNewScopes(context, scopes) { token, error ->
////                                if (error != null) {
////                                    //Log.e(TAG, "사용자 추가 동의 실패", error)
////                                    // 회원 탈퇴 후 앱 종료
////                                } else {
////                                    //Log.d(TAG, "allowed scopes: ${token!!.scopes}")
////                                    // 사용자 정보 재요청
////                                    UserApiClient.instance.me { user, error ->
////                                        if (error != null) {
////                                            //Log.e(TAG, "사용자 정보 요청 실패", error)
////                                            // 회원 탈퇴 후 앱 종료
////                                        }
////                                        else if (user != null) {
////                                            // 회원가입 및 로그인 성공
////                                            result = token!!.accessToken
////                                            //Log.i(TAG, "사용자 정보 요청 성공")
////                                        }
////                                    }
////                                }
////                            }
//                        }else{
//                            //Log.d(TAG, "사용자에게 추가 동의를 받아야 합니다.")
//                            result = "전부 동의 바랍니다."
//                            UserApiClient.instance.logout { error ->
//                                if (error != null) {
//                                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
//                                }
//                                else {
//                                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
//                                }
//                            }
//                            //로그아웃 시킨 후 회원탈퇴(서버에서 이용자 삭제) - 프레젠터에서 로그인화면 다시 보여준다.
//                        }
//                    }
//                }
//
//            }
//        }
//
//// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
//        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
//            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
//        } else {
//            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
//        }
//
//        return result
//
//
//    }
}