package com.neil.publisher

import android.hardware.usb.UsbRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.neil.publisher.data.Article
import com.neil.publisher.data.Author
import com.neil.publisher.databinding.FragmentPublishBinding
import java.util.*


class PublishFragment : Fragment() {

    private val viewModel: PublishViewModel by lazy {
        ViewModelProvider(this).get(PublishViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPublishBinding.inflate(inflater, container, false)

        binding.publishButton.setOnClickListener {
            if (UserManager.userToken != null) {
                val article = Article(Author(UserManager.user.email, UserManager.user.id, UserManager.user.name),
                    binding.editTextTextCatrgory.text.toString(), binding.editTextTextContent.text.toString(), null, null,
                    binding.editTextTextTitle.text.toString())
                viewModel.addData(article)
                this.findNavController().navigate(R.id.homeFragment)

            } else {
                Toast.makeText(this.context, "Require login to publish", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}