package com.example.g55085_android_projet.retrofit


data class RecipeStepsDto(
    val steps: List<Step>
)

data class Step(
    val number: Int,
    val step: String,


    )
