package com.hitesh.musicwiki.ui.genre

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hitesh.musicwiki.R
import com.hitesh.musicwiki.adapter.TagsAdapter
import com.hitesh.musicwiki.databinding.FragmentGenreBinding
import com.hitesh.musicwiki.repository.MusicRepository
import kotlinx.coroutines.launch

class GenreFragment : Fragment() {

    companion object {
        fun newInstance() = GenreFragment()
    }

    private var _binding: FragmentGenreBinding? = null

    private val binding get() = _binding!!

    private lateinit var genreAdapter: TagsAdapter

    private lateinit var viewModel: GenreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val musicRepository = MusicRepository()

        val viewModelFactory = GenreViewModelFactory(musicRepository)

        viewModel = ViewModelProvider(this, viewModelFactory)[GenreViewModel::class.java]

        genreAdapter = TagsAdapter()

        binding.rvGenre.apply {
            adapter = genreAdapter
            layoutManager = GridLayoutManager(activity, 3)
        }
        var expanded = false

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getTopTags()
                viewModel.genreResponse.observe(viewLifecycleOwner) { response ->
                    Log.d("final result search", response.body().toString())
                    genreAdapter.differ.submitList(response.body()?.tags?.tag?.subList(0, 9))

                    binding.expandGenres.setOnClickListener {
                        expanded = !expanded
                        if (expanded) {
                            binding.expandGenres.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                            genreAdapter.differ.submitList(response.body()?.tags?.tag)
                        } else {
                            binding.expandGenres.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                            genreAdapter.differ.submitList(
                                response.body()?.tags?.tag?.subList(
                                    0, 9
                                )
                            )
                        }
                    }

                }
            }
        }
//        viewModel.getTopTags()

//        viewModel._genreResponse?.observe(viewLifecycleOwner) {
//            Log.d("final result search", it.toString())
//        }

        return root

    }

}