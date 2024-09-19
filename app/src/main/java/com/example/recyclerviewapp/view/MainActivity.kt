package com.example.recyclerviewapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recyclerviewapp.model.City
import com.example.recyclerviewapp.R
import com.example.recyclerviewapp.model.Singleton
import com.example.recyclerviewapp.databinding.ActivityMainBinding
import com.example.recyclerviewapp.presenter.MainRecyclerViewAdapter
import com.example.recyclerviewapp.viewmodel.MainViewModel
import com.example.recyclerviewapp.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Singleton.init(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = MainViewModelFactory().create(MainViewModel::class.java)

        viewModel.citiesLiveData.observe(this) {
            (binding.mainRecyclerView.adapter as? MainRecyclerViewAdapter)?.notifyDataSetChanged()
        }

        val spanCount = 4
        val spacing = resources.getDimensionPixelSize(R.dimen.grid_item_spacing)
        val includeEdge = true

        val layoutManager = GridLayoutManager(this, spanCount)
        binding.mainRecyclerView.layoutManager = layoutManager
        binding.mainRecyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        binding.mainRecyclerView.adapter = MainRecyclerViewAdapter(object: MainRecyclerViewAdapter.ItemClickListener {
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

        binding.button.setOnClickListener {
            viewModel.addCity(
                City(
                    0, "City ${Singleton.cities.size}",
                    Singleton.cities.size * 1000000
                )
            )
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
