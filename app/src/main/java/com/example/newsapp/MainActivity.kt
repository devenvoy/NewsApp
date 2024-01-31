package com.example.newsapp

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.Constants.Companion.countryMap
import com.example.newsapp.Constants.Companion.selectedCategory
import com.example.newsapp.Constants.Companion.selectedCountry
import com.example.newsapp.Instances.RetrofitInstance
import com.example.newsapp.adapters.MyRecViewAdapter
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponseData
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var responseData: NewsResponseData? = null
    private val articles = mutableListOf<Article>()
    lateinit var dialog: Dialog
    lateinit var settingDialog: Dialog

    lateinit var countryspinner: Spinner
    lateinit var categorySpinner: Spinner
    lateinit var applyButton: Button

    lateinit var newsListAdapter: MyRecViewAdapter

    lateinit var cadapter: ArrayAdapter<String>
    lateinit var ladapter: ArrayAdapter<String>

    override fun onBackPressed() {
        if (binding.webview.visibility == View.VISIBLE) {
            binding.webview.visibility = View.INVISIBLE
        } else {
            super.onBackPressed()
        }
    }

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sp = getSharedPreferences("Query", MODE_PRIVATE)

        dialog = Dialog(this)
        settingDialog = Dialog(this)

        settingDialog.window!!.setGravity(Gravity.CENTER)

        selectedCountry = sp.getString("country", "India")
        selectedCategory = sp.getString("category", null)

        cadapter = ArrayAdapter(this, R.layout.sp_item, Constants.country)

        ladapter = ArrayAdapter(this, R.layout.sp_item, Constants.categoryList)

        binding.settings.setOnClickListener {

            settingDialog.apply {
                setContentView(R.layout.activity_settings)
                window!!.setLayout(MATCH_PARENT, WRAP_CONTENT)
                setCancelable(false)
                show()
            }

            countryspinner = settingDialog.findViewById(R.id.category)
            categorySpinner = settingDialog.findViewById(R.id.country)
            applyButton = settingDialog.findViewById(R.id.apply)

            with(countryspinner) {
                adapter = cadapter
                setSelection(0, false)
                prompt = " Select Country : "
                gravity = Gravity.CENTER
            }

            with(categorySpinner) {
                adapter = ladapter
                setSelection(0, false)
                prompt = " Select Category : "
                gravity = Gravity.CENTER
            }

            applyButton.setOnClickListener {
                selectedCountry = countryspinner.selectedItem.toString()
                selectedCategory = categorySpinner.selectedItem.toString()
                sp.edit().putString("country", selectedCountry).apply()
                sp.edit().putString("category", selectedCategory).apply()
                getNewsData()
                settingDialog.dismiss()
            }

        }


        newsListAdapter = MyRecViewAdapter(this@MainActivity)
        newsListAdapter.setData(articles)
        newsListAdapter.setOnItemClickListner(object : MyRecViewAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                showProgress()
                binding.webview.settings.javaScriptEnabled = true
                binding.webview.webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        hideProgress()
                        binding.webview.visibility = View.VISIBLE
                    }
                }
                binding.webview.loadUrl(articles[position].url)
            }

        })

        val stackLayoutManager =
            StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        stackLayoutManager.setPagerMode(true)
        stackLayoutManager.setPagerFlingVelocity(3000)
        binding.recyclerView.layoutManager = stackLayoutManager
        binding.recyclerView.adapter = newsListAdapter
        getNewsData()

    }

    private fun getNewsData() {
        showProgress()
        Log.e("====", "getNewsData: hello")
        RetrofitInstance.services.getNewsHeadLines(
            country = countryMap[selectedCountry],
            category = selectedCategory,
            page = null
        )
            .enqueue(object : Callback<NewsResponseData?> {
                override fun onResponse(
                    call: Call<NewsResponseData?>,
                    response: Response<NewsResponseData?>
                ) {
                    if (response.body() != null) {
                        responseData = response.body()!!
                    }
                    articles.clear()
                    articles.addAll(responseData!!.articles)
                    newsListAdapter.setData(articles)
                    hideProgress()
                }
                override fun onFailure(call: Call<NewsResponseData?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error Occured", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun showProgress() {
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.apply {
            setContentView(R.layout.progress_view)
            setCancelable(true)
            show()
        }
    }

    fun hideProgress() {
        dialog.dismiss()
    }

}