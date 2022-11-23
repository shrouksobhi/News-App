package com.shrouk.newsapp.details

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shrouk.newsapp.R
import com.shrouk.newsapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class DetailsFragment : Fragment() {
private lateinit var binding:FragmentDetailsBinding
private val detailsViewModel:DetailsViewModel by viewModels()
    private  val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_details,container,false)
        setHasOptionsMenu(true)

        return binding.root

       // return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(View(context)).load(args.articles?.urlToImage).into(binding.detailsImg)
       // args =DetailsFragmentArgs.fromBundle(requireArguments())

     //   binding.detailsImg.setImageURI(args.articles?.urlToImage!!.toUri())
        binding.title.text=args.articles?.title
        binding.description.text=args.articles?.description

        binding.btnsaveurl.setOnClickListener {
            var url = args.articles?.url
            showBottomSheetDialog(url!!)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.save -> {
                detailsViewModel.saveNews(args.articles!!)
                true
            }

            else -> super.onOptionsItemSelected(item)


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