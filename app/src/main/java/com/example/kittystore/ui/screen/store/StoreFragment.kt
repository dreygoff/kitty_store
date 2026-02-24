package com.example.kittystore.ui.screen.store

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.kittystore.R
import com.example.kittystore.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreFragment : Fragment(R.layout.fragment_store) {

    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoreViewModel by activityViewModels()

    private lateinit var adapter: StoreAdapter
    private lateinit var imageLoader: ImageLoader

    private val preloadDistance = 7

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentStoreBinding.bind(view)

        imageLoader = ImageLoader.Builder(requireContext()).crossfade(true).build()
        adapter = StoreAdapter(imageLoader)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

        observePaging()
        observePagesForPreload()
    }

    private fun observePaging() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingData.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }
    }

    private fun observePagesForPreload() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.onPagesUpdatedFlow.collect {
                    preloadImages()
                }
            }
        }
    }

    private fun preloadImages() {
        val layoutManager = binding.recycler.layoutManager as LinearLayoutManager
        val lastVisible = layoutManager.findLastVisibleItemPosition()

        val start = lastVisible + 1
        val end = lastVisible + preloadDistance

        for (i in start..end) {
            val item = adapter.peek(i) ?: continue

            val request = ImageRequest.Builder(requireContext())
                .data(item.imageUrl)
                .build()

            imageLoader.enqueue(request)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}