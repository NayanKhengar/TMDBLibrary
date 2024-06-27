package com.nayan.tmdbmoviesdemo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nayan.networksdk.NetworkSDK
import com.nayan.networksdk.YourResponseModel
import com.nayan.tmdbmoviesdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val networkSDK by lazy { NetworkSDK(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TopRatedMoviesFragment(), "Top Rated")
        adapter.addFragment(PopularMoviesFragment(), "Popular")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
    private fun fetchData() {
        networkSDK.fetchData(object : Callback<YourResponseModel> {
            override fun onResponse(
                call: Call<YourResponseModel>,
                response: Response<YourResponseModel>
            ) {
                if (response.isSuccessful) {
                    val data: YourResponseModel? = response.body()
                    // Handle successful response
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<YourResponseModel>, t: Throwable) {
                // Handle network errors
            }
        })
    }
}


class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitleList[position]
    }
}

