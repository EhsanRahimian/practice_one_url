package com.nicootech.practiceone

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val webView: WebView = findViewById(R.id.web_browser)
        val search : ImageButton = findViewById(R.id.search_button)
        val back : ImageButton = findViewById(R.id.back_button)
        val url_txt :EditText = findViewById(R.id.url_txt)

        webView.loadUrl("https://www.yahoo.com")
        webView.settings.javaScriptEnabled = true
        webView.canGoBack()
        webView.webViewClient = WebClient(this)
        search.setOnClickListener{
            val InputURL : String = url_txt.text.toString()
            webView.loadUrl("https://www.$InputURL")
        }
        back.setOnClickListener {
            webView.goBack()
        }

        closeKeyboard()
//        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(search.windowToken, 0)

    }

    class WebClient internal constructor(private val activity : Activity) : WebViewClient(){
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}
