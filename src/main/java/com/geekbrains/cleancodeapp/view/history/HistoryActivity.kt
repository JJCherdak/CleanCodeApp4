package com.geekbrains.cleancodeapp.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import com.geekbrains.cleancodeapp.model.data.AppState
import com.geekbrains.cleancodeapp.view.base.BaseActivity
import com.geekbrains.cleancodeapp.databinding.ActivityHistoryBinding
import com.geekbrains.cleancodeapp.model.data.DataModel
import org.koin.android.viewmodel.ext.android.viewModel


class HistoryActivity: BaseActivity<AppState, HystoryInteractor>() {
    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}