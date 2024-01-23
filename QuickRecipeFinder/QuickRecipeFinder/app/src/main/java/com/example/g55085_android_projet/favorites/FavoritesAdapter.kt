package com.example.g55085_android_projet.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.g55085_android_projet.database.RecipeFavorites
import com.example.g55085_android_projet.databinding.ItemFavoritesBinding


class FavoritesAdapter(private val clickListener: RecipeFavoritesListener) :
    ListAdapter<RecipeFavorites, FavoritesAdapter.ViewHolder>(RecipeFavoritesDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoritesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(getItem(position)!!, clickListener)
    }

    inner class ViewHolder(private val binding: ItemFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val titleRecipe: TextView = binding.titleRecipe
        private val qualityImage: ImageView = binding.qualityImage


        fun bind(recipe: RecipeFavorites, clickListener: RecipeFavoritesListener) {
            binding.recipeFavorites = recipe
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

}

class RecipeFavoritesDiffCallback : DiffUtil.ItemCallback<RecipeFavorites>() {
    override fun areItemsTheSame(oldItem: RecipeFavorites, newItem: RecipeFavorites): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeFavorites, newItem: RecipeFavorites): Boolean {
        return oldItem == newItem
    }
}

class RecipeFavoritesListener(val clickListener: (recipeId: Long, recipeName: String, recipeImage: String) -> Unit) {
    fun onClick(recipe: RecipeFavorites) = clickListener(recipe.id, recipe.name, recipe.imageUrl)
}