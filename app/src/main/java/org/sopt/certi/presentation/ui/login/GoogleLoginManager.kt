package org.sopt.certi.presentation.ui.login

import android.app.Activity
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import androidx.credentials.CustomCredential
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.certi.BuildConfig
import javax.inject.Inject

class GoogleLoginManager @Inject constructor() {
    companion object {
        const val WEB_CLIENT_ID = BuildConfig.GOOGLE_CLIENT_ID
    }

    fun login(
        activity: Activity,
        onResult: (Result<String>) -> Unit
    ) {
        val credentialManager = CredentialManager.create(activity)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(WEB_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = credentialManager.getCredential(
                    request = request,
                    context = activity
                )
                onResult(handleResponse(response))
            } catch (e: NoCredentialException) {
                onResult(Result.failure(e))
            } catch (e: GetCredentialException) {
                onResult(Result.failure(e))
            } catch (e: Throwable) {
                onResult(Result.failure(e))
            }
        }
    }

    private fun handleResponse(response: GetCredentialResponse): Result<String> {
        return try {
            val credential = response.credential

            when (credential) {
                is CustomCredential -> {
                    if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                        val googleCred = GoogleIdTokenCredential.createFrom(credential.data)
                        val idToken = googleCred.idToken
                        Result.success(idToken)
                    } else {
                        Result.failure(IllegalStateException("Unexpected credential type: ${credential.type}"))
                    }
                }

                else -> {
                    Result.failure(IllegalStateException("Unexpected credential class: ${credential::class.java.name}"))
                }
            }
        } catch (e: GoogleIdTokenParsingException) {
            Result.failure(e)
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
