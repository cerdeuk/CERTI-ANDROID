package org.sopt.certi.core.component.webview

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CertWebView(
    url: String,
    closeWebView: () -> Unit
) {
    var webView: WebView? by remember { mutableStateOf(null) }

    val onPressedBack = {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            closeWebView()
        }
    }

    BackHandler(enabled = true) {
        onPressedBack()
    }

    AndroidView(factory = {
        val myWebView = WebView(it)
        myWebView.webViewClient = CertWebViewClient()

        myWebView.apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            webView = this
        }
    }, update = {
        it.loadUrl(url)
    })
}

class CertWebViewClient : WebViewClient() {

}