package com.example.recyclerviewapp.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.databinding.ActivityMainBinding
import com.example.recyclerviewapp.model.UserSingleton
import com.example.recyclerviewapp.presenter.MainRecyclerViewAdapter
import com.example.recyclerviewapp.viewmodel.LoginViewModel
import com.example.recyclerviewapp.viewmodel.LoginViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var adapter: MainRecyclerViewAdapter
    private lateinit var modeButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        UserSingleton.init(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        modeButton = findViewById<Button>(R.id.button)

        loginViewModel = LoginViewModelFactory(UserSingleton.userDao).create(LoginViewModel::class.java)

        setupRecyclerView()

        modeButton.setOnClickListener{
            Log.d("TAG", "MUDOU TEMA")
            toggleMode()
        }

        loginViewModel.fetchPopularMovies("5c308328e2231e55206210b20a696644")

        loginViewModel.filmesList.observe(this) { filmes ->
            Log.d("MainActivity", "Filmes recebidos: ${filmes.size}")
            adapter.addAll(filmes)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun toggleMode() {
        modeButton = findViewById<Button>(R.id.button)
        val currentMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentMode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        ActivityCompat.recreate(this)
    }

    private fun updateButtonText() {
        val currentMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentMode == Configuration.UI_MODE_NIGHT_YES) {
            modeButton.text = "☀\uFE0F"
        } else {
            modeButton.text = "\uD83C\uDF12"
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
                val filme = adapter.filmes[position]
                val intent = Intent(this@MainActivity, Detail::class.java).apply {
                    putExtra("movie_title", filme.title)
                    putExtra("movie_description", filme.overview)
                }
                startActivity(intent)
            }

            override fun onLongClick(view: View, position: Int) {
            }
        })

        binding.mainRecyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        updateButtonText()
    }
}
