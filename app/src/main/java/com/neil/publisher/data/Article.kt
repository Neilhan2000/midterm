package com.neil.publisher.data

data class Article (
    val author: Author? = null,
    val category: String? = null,
    val content: String? = null,
    val createdTime: Long? = null,
    val id: String? = null,
    val title: String? =null
) {

}

data class Author (
    val email: String? = null,
    val id: String? = null,
    val name: String? = null
) {

}