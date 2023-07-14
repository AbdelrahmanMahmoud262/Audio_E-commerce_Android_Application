package com.androdevelopment.electronicse_commerce.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.androdevelopment.electronicse_commerce.Adapters.FeaturedAdapter
import com.androdevelopment.electronicse_commerce.Adapters.NewAdapter
import com.androdevelopment.electronicse_commerce.Models.FeaturedModel
import com.androdevelopment.electronicse_commerce.Models.NewModel
import com.androdevelopment.electronicse_commerce.R
import com.androdevelopment.electronicse_commerce.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip
import java.util.Locale

class MainActivity : AppCompatActivity(), FeaturedAdapter.OnFeaturedClick, NewAdapter.OnNewClick {


    private lateinit var binding: ActivityMainBinding

    private lateinit var newAdapter: NewAdapter
    private val newList = arrayListOf<NewModel>()

    private lateinit var featuredAdapter: FeaturedAdapter
    private val featuredList = arrayListOf<FeaturedModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        addChip("Headphone")
        addChip("Headband")
        addChip("Ear-pads")




        setUpChipAnim()
        setUpRecyclerNew()
        setUpRecyclerFeatured()
        setSearchView()
    }

    private fun setSearchView() {
        binding.categoriesChipGroup.setOnCheckedChangeListener { group, checkedId ->
            Handler().postDelayed({
                val chip = group.findViewById<Chip>(checkedId)
                binding.searchView.queryHint =
                    "Search " + chip.text.toString().lowercase(Locale.ROOT)
            }, 100)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val intent = Intent(this@MainActivity, Search::class.java)
                intent.putExtra("q", binding.searchView.query)
                startActivity(intent)
                return false
            }
        })
    }


    private fun setUpRecyclerFeatured() {
        featuredAdapter = FeaturedAdapter(featuredList, this)

        featuredList.add(FeaturedModel("", "TWA", 13.0, ""))
        featuredList.add(FeaturedModel("", "TWA", 13.0, ""))
        featuredList.add(FeaturedModel("", "TWA", 13.0, ""))
        featuredList.add(FeaturedModel("", "TWA", 13.0, ""))
        featuredList.add(FeaturedModel("", "TWA", 13.0, ""))
        featuredList.add(FeaturedModel("", "TWA", 13.0, ""))

        binding.recyclerFeatured.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = featuredAdapter
        }
    }

    private fun setUpRecyclerNew() {
        newAdapter = NewAdapter(newList, this)

        newList.add(
            NewModel(
                1, "TMA-2\n" + "Modular\n" + "Headphone", ""
            )
        )
        newList.add(
            NewModel(
                1, "TMA-2\n" + "Modular\n" + "Headphone", ""
            )
        )
        newList.add(
            NewModel(
                1, "TMA-2\n" + "Modular\n" + "Headphone", ""
            )
        )
        newList.add(
            NewModel(
                1, "TMA-2\n" + "Modular\n" + "Headphone", ""
            )
        )
        newList.add(
            NewModel(
                1, "TMA-2\n" + "Modular\n" + "Headphone", ""
            )
        )

        binding.recyclerNew.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = newAdapter
        }
    }

    private fun setUpChipAnim() {
        for (view in binding.categoriesChipGroup.children) {
            val chip = view as Chip
            chip.setOnCheckedChangeListener { buttonView, _ ->
                val index = binding.categoriesChipGroup.indexOfChild(buttonView)
                binding.categoriesChipGroup.removeView(buttonView)
                binding.categoriesChipGroup.addView(buttonView, index)
            }
        }
    }


    private fun addChip(title: String) {
        val chip =
            layoutInflater.inflate(R.layout.item_chip, binding.categoriesChipGroup, false) as Chip

        chip.apply {
            text = title
        }
        binding.categoriesChipGroup.addView(chip)
        binding.categoriesChipGroup.isSingleSelection = true

        binding.categoriesChipGroup.check(binding.categoriesChipGroup[0].id)

    }

    override fun onItemClick(featuredModel: FeaturedModel) {
        Toast.makeText(this@MainActivity, "feat ureed", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(newModel: NewModel) {
        Toast.makeText(this@MainActivity, "new", Toast.LENGTH_SHORT).show()

    }


}

