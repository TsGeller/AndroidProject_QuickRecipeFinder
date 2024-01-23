package com.example.g55085_android_projet.searchRecipe


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.g55085_android_projet.databinding.ItemListBinding
import com.example.g55085_android_projet.retrofit.RecipeDto

class RecipeAdapter(private val clickListener: RecipeDtoListener) :
    ListAdapter<RecipeDto, RecipeAdapter.ViewHolder>(RecipeDtoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    class ViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val titleRecipe: TextView = binding.titleRecipe
        private val usedIngredients: TextView = binding.usedIngredients
        private val qualityImage: ImageView = binding.qualityImage
        private val missingIngredients: TextView = binding.missingIngredients


        fun bind(recipe: RecipeDto, clickListener: RecipeDtoListener) {
            binding.recipeDto = recipe
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }


}

class RecipeDtoDiffCallback : DiffUtil.ItemCallback<RecipeDto>() {
    override fun areItemsTheSame(oldItem: RecipeDto, newItem: RecipeDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RecipeDto, newItem: RecipeDto): Boolean {
        return oldItem == newItem
    }
}

class RecipeDtoListener(val clickListener: (recipeId: Long, recipeName: String, recipeImage: String, listIngredients: String) -> Unit) {
    fun onClick(recipe: RecipeDto) {
        val quantityIngredients = StringBuilder()

        for (ingredient in recipe.usedIngredients) {
            quantityIngredients.append(ingredient.original + ",")
        }
        for (ingredient in recipe.missedIngredients) {
            quantityIngredients.append(ingredient.original + ",")
        }
        var stringToSend: String = quantityIngredients.toString()
        stringToSend = stringToSend.dropLast(1)
        clickListener(recipe.id, recipe.title, recipe.image, stringToSend)
    }
}