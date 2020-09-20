package com.pieterventer.whatsgoingon.ui.dashboard

import android.animation.Animator
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pieterventer.whatsgoingon.R
import com.pieterventer.whatsgoingon.data.model.response.NewsItem
import com.pieterventer.whatsgoingon.util.hide
import com.pieterventer.whatsgoingon.util.show
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val viewModel by viewModel<NewsViewModel>()

    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar?.let {
            setSupportActionBar(it)
        }
        setupNewsAdapter()

        loadingAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                viewModel.retrieveLatestNews(this@DashboardActivity, ::handleApiError)
            }

            override fun onAnimationEnd(p0: Animator?) {
                pullToRefresh.setOnRefreshListener {
                    viewModel.retrieveLatestNews(this@DashboardActivity, ::handleApiError)
                }

                viewModel.currentLiveNews.observe(this@DashboardActivity, {
                    it?.let {
                        newsAdapter.items = it
                        pullToRefresh.isRefreshing = false
                        newsRecycler.show()
                        loadingAnimation.hide()

                    } ?: showEmptyOrLoading()
                })
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }
        })

    }

    private fun showEmptyOrLoading() {
        newsRecycler.hide()
        loadingAnimation.show()
        loadingAnimation.playAnimation()
    }

    private fun handleApiError(error: String) {
        Snackbar.make(pullToRefresh, error, Snackbar.LENGTH_LONG).show()
    }

    private fun handleClickCallback(newsItem: NewsItem) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(newsItem.url)

        startActivity(intent)
    }

    private fun setupNewsAdapter() {
        newsAdapter = NewsAdapter(::handleClickCallback)

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL

        newsRecycler.apply {
            layoutManager = manager
            adapter = newsAdapter
        }
    }
}