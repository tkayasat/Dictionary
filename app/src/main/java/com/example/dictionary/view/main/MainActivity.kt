package com.example.dictionary.view.main


import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dictionary.R
import com.example.dictionary.databinding.ActivityMainBinding
import com.example.dictionary.model.data.AppState
import com.example.dictionary.presenter.DataPresenterRU
import com.example.dictionary.presenter.Presenter
import com.example.dictionary.view.common.BaseActivity
import com.example.dictionary.view.common.OnListItemClickListener
import com.example.dictionary.view.common.OnSearchClickListener

class MainActivity : BaseActivity<AppState>() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: MainAdapter? = null

    private val onListItemClickListener: OnListItemClickListener =
        object : OnListItemClickListener {
            override fun onItemClick(data: DataPresenterRU) {
                Toast.makeText(
                    this@MainActivity, data.word,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun createPresenter(): Presenter<AppState, View> {
        return MainPresenter(baseContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord, true)
                }
            })
            searchDialogFragment.show(
                supportFragmentManager,
                SearchDialogFragment.TAG
            )
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.mainActivityRecyclerview.adapter =
                            MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            presenter.getData("hi", true)
        }
    }


    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }
}