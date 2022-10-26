package com.neil.publisher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.neil.publisher.data.Article

class HomeViewModel: ViewModel() {

    init {
        loadArticle()
    }

    private val _articleList = MutableLiveData<List<Article>>()
    val articleList: LiveData<List<Article>>
        get() = _articleList

    private var articleListGlobal = mutableListOf<Article>()


    private fun loadArticle() {
        val db = Firebase.firestore

        db.collection("articles").orderBy("createdTime", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val objecttt = document.toObject<Article>()

                    articleListGlobal.add(objecttt)

                }
                _articleList.value = articleListGlobal
//                Log.i("neil", "success load article = ${articleList}")
            }
            .addOnFailureListener { exception ->
                Log.w("neil", "Error getting documents.", exception)
            }

    }

    fun refresh() {
        _articleList.value = null
        articleListGlobal = mutableListOf<Article>()
        loadArticle()
    }


    fun login() {
        UserManager.userToken = "login success"
    }
}