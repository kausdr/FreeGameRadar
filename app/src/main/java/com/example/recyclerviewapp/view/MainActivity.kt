package com.example.recyclerviewapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.model.Singleton
import com.example.recyclerviewapp.databinding.ActivityMainBinding
import com.example.recyclerviewapp.model.UserSingleton
import com.example.recyclerviewapp.presenter.MainRecyclerViewAdapter
import com.example.recyclerviewapp.viewmodel.LoginViewModel
import com.example.recyclerviewapp.viewmodel.LoginViewModelFactory
import com.example.recyclerviewapp.viewmodel.MainViewModel
import com.example.recyclerviewapp.viewmodel.MainViewModelFactory
import com.example.recyclerviewapp.model.Filme

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var adapter: MainRecyclerViewAdapter
    private var filmesList = mutableListOf<Filme>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Singleton.init(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        UserSingleton.init(this)

        loginViewModel = LoginViewModelFactory(UserSingleton.userDao).create(LoginViewModel::class.java)
        viewModel = MainViewModelFactory().create(MainViewModel::class.java)

        setupRecyclerView()

        loginViewModel.fetchPopularMovies("5c308328e2231e55206210b20a696644")

        loginViewModel.filmesList.observe(this) { filmes ->
            adapter.addAll(filmes)
            Log.d("MainActivity", "Filmes recebidos: ${filmes.size}")
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupRecyclerView() {
        val spanCount = 2
        val spacing = resources.getDimensionPixelSize(R.dimen.grid_item_spacing)
        val includeEdge = true

        val layoutManager = GridLayoutManager(this, spanCount)
        binding.mainRecyclerView.layoutManager = layoutManager
        binding.mainRecyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        adapter = MainRecyclerViewAdapter(object : MainRecyclerViewAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Singleton.cities[position].apply {
                    population += 100
                }
                viewModel.updateCity(Singleton.cities[position])
            }

            override fun onLongClick(view: View, position: Int) {
                Singleton.deleteCity(position)
                (binding.mainRecyclerView.adapter as? MainRecyclerViewAdapter)?.notifyItemRemoved(position)
            }
        })

        binding.mainRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
