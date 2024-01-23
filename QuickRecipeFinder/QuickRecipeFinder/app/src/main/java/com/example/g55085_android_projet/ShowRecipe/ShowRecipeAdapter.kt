package com.example.g55085_android_projet.ShowRecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.g55085_android_projet.R
import com.example.g55085_android_projet.retrofit.Step

class ShowRecipeAdapter : RecyclerView.Adapter<ShowRecipeAdapter.ViewHolder>() {

    var data = listOf<Step>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_instruction_recipe, parent, false)
    ) {
        val instructionTextView: TextView = itemView.findViewById(R.id.instruction)
        val stepsInstruction: TextView = itemView.findViewById(R.id.steps)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.instructionTextView.text = item.step
        holder.stepsInstruction.text = item.number.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
