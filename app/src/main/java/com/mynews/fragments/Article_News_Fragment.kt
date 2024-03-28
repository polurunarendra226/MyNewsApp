package com.mynews.fragments


import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mynews.R
import com.mynews.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Article_News_Fragment() : Fragment(R.layout.fragment_article__news_) {
    val args:Article_News_FragmentArgs by navArgs()
    val viewmodel:NewsViewModel by viewModels()
lateinit var webview:WebView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var articles = args.articles
        webview = view.findViewById(R.id.webview)
        webview.webViewClient = WebViewClient()
        webview.loadUrl(articles.url)
        webview.settings.setSupportZoom(true)
        webview.settings.javaScriptEnabled = true


        view.findViewById<FloatingActionButton>(R.id.fab_save).setOnClickListener {
            viewmodel.upsert(articles)
            Toast.makeText(activity,"Article Saved Successfully",Toast.LENGTH_LONG).show()
        }





    }

}