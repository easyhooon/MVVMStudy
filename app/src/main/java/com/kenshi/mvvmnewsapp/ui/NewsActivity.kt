package com.kenshi.mvvmnewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kenshi.mvvmnewsapp.databinding.ActivityNewsBinding
import com.kenshi.mvvmnewsapp.db.ArticleDatabase
import com.kenshi.mvvmnewsapp.repository.NewsRepository
import com.kenshi.mvvmnewsapp.ui.NewsViewModel
import com.kenshi.mvvmnewsapp.ui.NewsViewModelProviderFactory

// shift + f6 (edit)
class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)


        // fragment 를 사용해서 NavHostFragment 를 만든 경우
//        binding.apply {
//            bottomNavigationView.setupWithNavController(binding.newsNavHostFragment.findNavController())
//        }

        // FragmentContainerView 를 사용해서 NavHostFragment 를 만든 경우
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
