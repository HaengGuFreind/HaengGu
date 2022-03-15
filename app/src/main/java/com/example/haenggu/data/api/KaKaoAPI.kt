package com.example.haenggu.data.api

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.haenggu.data.local.SharedManager
import com.example.haenggu.data.repository.LoginRepository
import com.example.haenggu.login.LoginContract
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class KaKaoAPI(var interator: LoginContract.LoginInterator?) : LoginContract.KakaoAPIModel {
    fun unregister() {
        interator = null
    }
    override fun getToken(context: Context) {  // 로그인 전 [토큰 존재 여부 확인하기]
        Log.d("herer","kakao")

        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.accessTokenInfo { token, error ->
                if (error != null) {
                    Log.d("hasToken", error.message.toString())
                    if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                        //로그인 필요
                        Log.d("hasToken1", "로그인 필요")
                    } else {
                        //기타 에러
                        Log.d("hasToken2", "기타에러")
                    }
                } else {
                    // 토큰 유효성 체크 성공 (필요시 토큰이 갱신된다.)\
                    //액세스 토큰이 유효한 상태이므로 사용자 로그인 불필요
                    //해당 액세스 토큰으로 카카오 API 호출 가능
                    Log.d("hasToken3", "성공")
                    interator?.resultToken(token.toString(),context)

                }

            }
            Log.d("hasToken4", "욜라리")
            interator?.resultToken("NoToken",context)
        } else {
            // 로그인 필요
            interator?.resultToken("NoToken",context)
        }
    }

    //[카카오 로그인 구현]
    override fun getLogin(context: Context){
        val sharedManager: SharedManager by lazy { SharedManager(context) }
        // 로그인 공통 callback
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                // 로그인 서버, 수단등의 에러로인해 로그인 실패
//                when {
//                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
//                         "접근이 거부 되었습니다."
//                    }
//                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
//                        "Fail", "유효하지 않은 앱을 사용하였습니다."
//                    }
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
//                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
//                      "Fail", "앱에대한 요청 권한이 없습니다."
//
//                }
                //}
                    interator?.resultKLogin("fail",context)
            } else if (token != null) {
                //로그인 성공

                // 사용자 정보 확인
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 추가 동의 실패", error)
                        Toast.makeText(context, "회원가입을 위해 정보 제공에 모두 동의해주세요.", Toast.LENGTH_LONG)
                        interator?.resultKLogin("fail",context)
                        //회원 탈퇴

                    } else if (user != null) {
                        var scopes = mutableListOf<String>()

                        if (user.kakaoAccount?.emailNeedsAgreement == true) {
                            user.kakaoAccount?.email?.let { scopes.add(it) }
                        }
                        if (user.kakaoAccount?.genderNeedsAgreement == true) {
                            user.kakaoAccount?.gender?.toString().let { scopes.add(it!!) }
                        }

                        if (scopes.count() == 2) {
                            // 회원가입 및 로그인 성공
                            Log.d("getLogin", token.accessToken)
                            Log.d("getLogin", "Success_NoToken")
                            sharedManager.saveEmail(scopes[0])
                            sharedManager.saveGender(scopes[2])
                            interator?.resultKLogin(token.accessToken,context)

                        } else {

                            // 로그인 성공 하였으나 모든 정보를 제공하지 않아, 삭제
                            Log.d(TAG, "사용자에게 추가 동의를 받아야 함")
                            UserApiClient.instance.loginWithNewScopes(
                                context,
                                scopes
                            ) { token, error ->
                                if (error != null) {
                                    Log.e(TAG, "사용자 추가 동의 실패", error)
                                    Toast.makeText(
                                        context,
                                        "회원가입을 위해 정보 제공에 모두 동의해주세요.",
                                        Toast.LENGTH_LONG
                                    )
                                    interator?.resultKLogin("fail",context)
                                    //회원 탈퇴
                                } else {
                                    Log.d(TAG, "allowed scopes: ${token!!.scopes}")

                                    // 사용자 정보 재요청
                                    UserApiClient.instance.me { user, error ->
                                        if (error != null) {
                                            Log.e(TAG, "사용자 정보 요청 실패", error)
                                            Toast.makeText(
                                                context,
                                                "회원가입을 위해 정보 제공에 모두 동의해주세요.",
                                                Toast.LENGTH_LONG
                                            )
                                            interator?.resultKLogin("fail",context)
                                            //회원 탈퇴
                                        } else if (user != null) {
                                            interator?.resultKLogin(token.accessToken,context)
                                        }
                                    }
                                }

                            }
                        }

                    }

                }

            }

        }

        // [카카오톡으로 로그인 구현하기]
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    // 여기도 있어야 하나????체크하기!!!!!!!!!!!!!!!!!!!!!+++
                    interator?.resultKLogin(token.accessToken,context)
                }
                Log.d("아", "진짜 엥스럽네")

            }
            Log.d("아", "진짜 엥스럽네2")
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    //[로그아웃]
    fun logout(): Boolean {
        var result = false
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                result = true
            }
        }
        return result
    }

    //[탈퇴]
    fun unlink(): Boolean {
        var result = false
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(TAG, "연결 끊기 실패", error)
            } else {
                Log.i(TAG, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                result = true
            }
        }
        return result
    }
}