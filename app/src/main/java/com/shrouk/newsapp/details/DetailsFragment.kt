package com.shrouk.newsapp.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shrouk.newsapp.R
import com.shrouk.newsapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class DetailsFragment : Fragment() {
private lateinit var binding:FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        return binding.root
       // return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var args =DetailsFragmentArgs.fromBundle(requireArguments())
        Glide.with(View(context)).load(args.articles?.urlToImage).into(binding.detailsImg)

     //   binding.detailsImg.setImageURI(args.articles?.urlToImage!!.toUri())
        binding.title.text=args.articles?.title
        binding.description.text=args.articles?.description

        binding.btnsaveurl.setOnClickListener {
            var url = args.articles?.url
            showBottomSheetDialog(url!!)
        }
    }

    private fun showBottomSheetDialog(url:String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout)
val browser:WebView? = bottomSheetDialog.findViewById<WebView>(R.id.webview)
        browser?.webViewClient=MyBrowser()
        browser?.settings?.loadsImagesAutomatically = true
        browser?.settings?.javaScriptEnabled = true
        browser?.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        browser?.loadUrl(url)
        bottomSheetDialog.show()

     //We use bottomSheetDialog.dismiss() to close the dialog once an element is clicked.
       // bottomSheetDialog.dismiss()
    }
    private class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}