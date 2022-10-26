package com.neil.publisher

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.neil.publisher.data.Article
import com.neil.publisher.databinding.FragmentHomeBinding
import java.util.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.floatingActionButton.setOnClickListener {
            this.findNavController().navigate(R.id.publishFragment)
        }


        // recycler view
        val articleAdapter = HomeArticleAdapter()
        binding.articleRecycler.adapter = articleAdapter

        // firebase
        val db = Firebase.firestore
//        addData()


        val articleList = mutableListOf<Article>()
        db.collection("articles")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("neil", "${document.id} => ${document.data}")
                    val objecttt = document.toObject<Article>()

                    Log.d("neil", "object = $objecttt")
                    articleList.add(objecttt)
                    Log.d("neil", "object list = $articleList")

                    articleAdapter.submitList(articleList)
                    articleAdapter.notifyDataSetChanged()


                }
            }
            .addOnFailureListener { exception ->
                Log.w("neil", "Error getting documents.", exception)
            }


//        eventChangeListener(articleList)

        Log.d("neil", "article list = $articleList")



        return binding.root
    }

    fun addData() {
        val articles = FirebaseFirestore.getInstance()
            .collection("articles")
        val document = articles.document()
        val data = hashMapOf(
            "author" to hashMapOf(
                "email" to "wayne@school.appworks.tw",
                "id" to "waynechen323",
                "name" to "AKA小安老師"
            ),
            "title" to "IU「亂穿」竟美出新境界！笑稱自己品味奇怪　網笑：靠顏值撐住女神氣場",
            "content" to "南韓歌手IU（李知恩）無論在歌唱方面或是近期的戲劇作品都有亮眼的成績，但俗話說人無完美、美玉微瑕，曾再跟工作人員的互動影片中坦言自己 品味很奇怪，近日在IG上分享了宛如「媽媽們青春時代的玉女歌手」超復古穿搭造型，卻意外美出新境界。",
            "createdTime" to Calendar.getInstance()
                .timeInMillis,
            "id" to document.id,
            "category" to "Beauty"
        )
        document.set(data)
    }

//    fun eventChangeListener(articleList: MutableList<Article>) {
//        val db = Firebase.firestore
//        db.collection("articles").
//                addSnapshotListener(object : EventListener<QuerySnapshot> {
//                    override fun onEvent(
//                        value: QuerySnapshot?,
//                        error: FirebaseFirestoreException?
//                    ) {
//                        if (error != null) {
//                            Log.d("neil", "error ${error.message}")
//                            return
//                        }
//
//                        for (dc: DocumentChange in value?.documentChanges!!) {
//                            if (dc.type == DocumentChange.Type.ADDED) {
//                                articleList.add(dc.document.toObject(Article::class.java))
//                            }
//                        }
//                    }
//
//                })
//    }
}