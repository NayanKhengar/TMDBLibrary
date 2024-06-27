// YourResponseModel.kt
package com.nayan.networksdk

data class YourResponseModel(
    val id: Int,
    val keywords: List<Keyword>
)

data class Keyword(
    val id: Int,
    val name: String
)
