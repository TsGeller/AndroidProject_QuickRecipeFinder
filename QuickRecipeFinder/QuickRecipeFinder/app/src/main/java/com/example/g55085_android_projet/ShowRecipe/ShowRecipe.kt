package com.example.g55085_android_projet.ShowRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.g55085_android_projet.R
import com.example.g55085_android_projet.database.RecipeFavoritesDao
import com.example.g55085_android_projet.database.RecipeFavoritesDatabase
import com.example.g55085_android_projet.databinding.FragmentShowRecipeBinding
import com.squareup.picasso.Picasso

class ShowRecipe : Fragment() {

    private lateinit var binding: FragmentShowRecipeBinding
    private lateinit var viewModel: ShowRecipeViewModel
    private lateinit var recipeName: String
    private var recipeId: Long = 0
    private lateinit var recipeImage: String
    private lateinit var listIngredients: String
    private lateinit var argument: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            argument = it.getString("fragment_origin").toString()
            recipeName = it.getString("recipeName").toString()
            recipeId = it.getLong("recipeId")
            recipeImage = it.getString("recipeImage").toString()
            listIngredients = it.getString("listIngredients").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_show_recipe,
            container,
            false
        )

        val factory = ShowRecipeViewModelFactory(
            requireActivity().application,
            recipeId,
            recipeName,
            recipeImage,
            listIngredients,
            argument
        )
        viewModel = ViewModelProvider(this, factory).get(ShowRecipeViewModel::class.java)
        val adapter = ShowRecipeAdapter()
        binding.instructionList.adapter = adapter
        checkIsFavorite()
        viewModel.listStep.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })
        Picasso.get().load(recipeImage).into(binding.imageView)
        binding.NameRecipe.text = viewModel.recipeName

        binding.addButton.setOnClickListener {
            if (!viewModel.isFavorites) {
                viewModel.addFavorites()
                checkIsFavorite()
            } else {
                viewModel.deleteFavorites()
                checkIsFavorite()
            }

        }

        val recipeDao: RecipeFavoritesDao =
            RecipeFavoritesDatabase.getInstance(requireActivity().application).recipeDao
        viewModel.isFavorites = recipeDao.isfound(recipeId)

        binding.quantityIngredients?.text = viewModel.ingredients
        binding.favorites?.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_showRecipe_to_favorites)
        }
        return binding.root
    }

    private fun checkIsFavorite() {
        if (viewModel.isFavorites) {
            binding.addButton.text = requireContext().getString(R.string.supprimer)

        } else {
            binding.addButton.text = requireContext().getString(R.string.ajouter)
        }
    }


}

class TextItemViewHolder(val textView: ConstraintLayout) : RecyclerView.ViewHolder(textView)