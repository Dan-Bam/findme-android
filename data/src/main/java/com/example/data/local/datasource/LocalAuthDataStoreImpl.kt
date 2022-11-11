package com.example.data.local.datasource

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalAuthDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
): LocalAuthDataStore {
    companion object {
        const val TOKEN = "TOKEN"
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
    override fun setAccessToken(token: String) {
        getSharedPreferences().edit().let {
            it.putString(ACCESS_TOKEN, token)
            it.apply()
        }
    }

    override fun getAccessToken(): String {
        return getSharedPreferences().getString(ACCESS_TOKEN, "") ?: ""
    }

    override fun setRefreshToken(token: String) {
        getSharedPreferences().edit().let {
            it.putString(REFRESH_TOKEN, token)
            it.apply()
        }
    }

    override fun getRefreshToken(): String {
        return this.getSharedPreferences().getString(REFRESH_TOKEN, "") ?: ""
    }

    private fun getSharedPreferences() =
        context.getSharedPreferences(TOKEN, MODE_PRIVATE)
}