package org.sopt.certi.core.network

import android.content.SharedPreferences
import com.google.gson.Gson
import org.sopt.certi.domain.model.user.UserInformationAuth
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.jvm.java

@Singleton
class TokenManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        private const val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val KEY_REFRESH_TOKEN = "REFRESH_TOKEN"
    }

    fun getToken(): String {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, "").orEmpty()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, token).apply()
    }

    fun getRefreshToken(): String {
        return sharedPreferences.getString(KEY_REFRESH_TOKEN, "").orEmpty()
    }

    fun saveRefreshToken(token: String) {
        sharedPreferences.edit().putString(KEY_REFRESH_TOKEN, token).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    private var onLogoutCallback: (() -> Unit)? = null

    fun setLogoutCallback(callback: () -> Unit) {
        onLogoutCallback = callback
    }

    fun triggerLogout() {
        onLogoutCallback?.invoke()
    }

    fun savePreSignupToken(token: String) {
        sharedPreferences.edit().putString("PRE_SIGNUP_TOKEN", token).apply()
    }

    fun getPreSignupToken(): String {
        return sharedPreferences.getString("PRE_SIGNUP_TOKEN", "").orEmpty()
    }

    fun saveUserInformation(userInformation: UserInformationAuth) {
        val json = Gson().toJson(userInformation)
        sharedPreferences.edit().putString("USER_INFORMATION", json).apply()
    }

    fun getUserInformation(): UserInformationAuth? {
        val json = sharedPreferences.getString("USER_INFORMATION", null)
        return if (json != null) Gson().fromJson(json, UserInformationAuth::class.java) else null
    }

    fun saveNickName(nickname: String) {
        sharedPreferences.edit().putString("NICKNAME", nickname).apply()
    }

    fun getNickName(): String {
        return sharedPreferences.getString("NICKNAME", "").orEmpty()
    }
}
