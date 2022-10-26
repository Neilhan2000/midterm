package com.neil.publisher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.neil.publisher.data.Article
import java.util.*

class PublishViewModel: ViewModel() {

    fun addData(article: Article) {
        val articles = FirebaseFirestore.getInstance()
            .collection("articles")
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to article.author?.email,
                "id" to article.author?.id,
                "name" to article.author?.name
            ),
            "title" to article.title,
            "content" to article.content,
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "category" to article.category
        )
        document.set(data)
    }


}