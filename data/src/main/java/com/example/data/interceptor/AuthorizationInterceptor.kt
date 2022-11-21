package com.example.data.interceptor

import com.example.data.local.datasource.LocalAuthDataStore
import com.example.domain.entity.auth.TokenEntity
import com.google.gson.Gson
import okhttp3.*
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val localAuthDataStore: LocalAuthDataStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "/auth/signup",
            "/auth/signin",
            "/auth/reissue",
            "/auth/send",
            "/auth/check"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)
        val expiredAt = localAuthDataStore.getExpiredAt() as LocalDateTime
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://192.168.16.86:8080/auth/reissue")
                .patch(RequestBody.create(MediaType.parse("application/json"), ""))
                .addHeader("RefreshToken", localAuthDataStore.getRefreshToken())
                .build()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body().toString(),
                    TokenEntity::class.java
                )
                localAuthDataStore.setAccessToken(token.accessToken)
                localAuthDataStore.setRefreshToken(token.refreshToken)
                localAuthDataStore.setExpiredAt(token.expiredAt)
            } else throw RuntimeException()
        }
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                localAuthDataStore.getAccessToken()
            ).build()
        )
    }
}