package com.example.data.interceptor

import com.example.data.local.datasource.LocalAuthDataStore
import com.example.domain.entity.auth.TokenEntity
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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
        val expiredAt = LocalDateTime.parse(
            localAuthDataStore.getExpiredAt(),
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")
        )
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        if (expiredAt.isAfter(currentTime)) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://3.35.190.72:8080/auth/reissue")
                .patch(RequestBody.create(MediaType.parse("application/json"), ""))
                .addHeader("RefreshToken", localAuthDataStore.getRefreshToken())
                .build()
            val jsonParser = JsonParser()
            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val token = jsonParser.parse(response.body()!!.string()) as JsonObject
                localAuthDataStore.setAccessToken(token["accessToken"].toString().removeDot())
                localAuthDataStore.setRefreshToken(token["refreshToken"].toString().removeDot())
                localAuthDataStore.setExpiredAt(token["expiredAt"].toString().removeDot())
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

private fun String.removeDot(): String {
    return this.replace("^\"|\"$".toRegex(), "")
}