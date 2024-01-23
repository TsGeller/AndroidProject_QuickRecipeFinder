package com.example.g55085_android_projet


import com.example.g55085_android_projet.searchRecipe.RecipeAdapter
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.g55085_android_projet.databinding.FragmentSearchRecipeBinding
import com.example.g55085_android_projet.searchRecipe.RecipeDtoListener

import com.example.g55085_android_projet.searchRecipe.RecipeViewModel


class SearchRecipe : Fragment() {

    private lateinit var viewModel: RecipeViewModel
    private lateinit var binding: FragmentSearchRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_recipe,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        val adapter =
            RecipeAdapter(RecipeDtoListener { recipeId, recipeName, recipeImage, listIngredients ->
                val bundle = Bundle().apply {
                    putString("fragment_origin", "recherche")
                    putLong("recipeId", recipeId)
                    putString("recipeName", recipeName)
                    putString("recipeImage", recipeImage)
                    putString("listIngredients", listIngredients)

                }
                view?.findNavController()?.navigate(R.id.action_searchRecipe_to_showRecipe, bundle)

            })
        //button search
        binding.searchRecipes.setOnClickListener { view: View ->
            val input: String = getInput()
            if (input.isNotEmpty()) {
                viewModel.searchRecipe(input)
            }
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.form.windowToken, 0)
        }
        viewModel.listRecipes.observe(viewLifecycleOwner, Observer { listRecipes ->
            listRecipes?.let {
                adapter.submitList(it)
            }
        })
        binding.recipeList.adapter = adapter
        //button back
        binding.backMain.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_searchRecipe_to_about_Fragment)
        }


        //recycler view


        return binding.root
    }

    private fun getInput(): String {
        val input: String = binding.form.text.toString().trim()
        val ingredients: List<String> = input.split(" ")
        val result: String = ingredients.joinToString(",")
        return if (result.isEmpty()) {
            Toast.makeText(requireContext(), R.string.string_vide, Toast.LENGTH_SHORT).show()
            ""
        } else {
            result
        }
    }

}


