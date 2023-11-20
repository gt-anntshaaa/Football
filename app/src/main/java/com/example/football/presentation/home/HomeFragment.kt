package com.example.football.presentation.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.football.R
import com.example.football.databinding.ClassementLayoutBinding
import com.example.football.databinding.FragmentHomeBinding
import com.example.football.domain.common.Resource
import com.example.football.domain.common.hide
import com.example.football.domain.common.show
import com.example.football.presentation.home.adapter.TableClassementAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//    private var _classementLayout: ClassementLayoutBinding? = null
//    private val classementLayout get() = _classementLayout!!

    private val classementViewModel: ClassementViewModel by viewModels()
    @Inject lateinit var classementAdapter: TableClassementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        _classementLayout = ClassementLayoutBinding.bind(binding.root)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()

        classementViewModel.load()

        // setup classement adapter
        binding.tableRecyclerView.adapter = classementAdapter
    }

    private fun observeUI() {
        classementViewModel.classement.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    classementAdapter.submitList(it.data)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}