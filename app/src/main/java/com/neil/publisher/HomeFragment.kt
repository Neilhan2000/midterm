package com.neil.publisher

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.neil.publisher.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

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

        // eventChangeListener(articleList)

        viewModel.articleList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            articleAdapter.submitList(it)
            articleAdapter.notifyDataSetChanged()
        })

        // login to publish
        viewModel.login()

        // refresh
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = true
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }

        binding.floatingActionButton.setColorFilter(Color.WHITE)






        return binding.root
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