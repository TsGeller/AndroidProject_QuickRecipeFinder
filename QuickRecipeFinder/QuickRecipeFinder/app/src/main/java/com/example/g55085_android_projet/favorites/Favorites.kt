package com.example.g55085_android_projet.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.g55085_android_projet.R
import com.example.g55085_android_projet.databinding.FragmentFavoritesBinding


class Favorites : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorites,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        val adapter =
            FavoritesAdapter(RecipeFavoritesListener { recipeId, recipeName, recipeImage ->
                val bundle = Bundle().apply {
                    putString("fragment_origin", "favorites")
                    putLong("recipeId", recipeId)
                    putString("recipeName", recipeName)
                    putString("recipeImage", recipeImage)

                }
                view?.findNavController()?.navigate(R.id.action_favorites_to_showRecipe, bundle)

            })
        binding.listFavorites.adapter = adapter
        viewModel.listFavorites.observe(viewLifecycleOwner, Observer { listFavorites ->
            listFavorites?.let {
                adapter.submitList(it)
            }
        })
        binding.backMain.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_favorites2_to_about_Fragment)
        }
        return binding.root
    }

}