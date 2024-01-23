package com.example.g55085_android_projet

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.g55085_android_projet.database.RecipeFavoritesDatabase
import com.example.g55085_android_projet.databinding.FragmentStartBinding


class FragmentStart : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private var database: RecipeFavoritesDatabase? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start,
            container,
            false
        )
        binding.search.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_about_Fragment_to_searchRecipe)
        }
        binding.favorites.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_about_Fragment_to_favorites2)
        }
        binding.Infos.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_about_Fragment_to_aboutDev)
        }
        return binding.root

    }


}