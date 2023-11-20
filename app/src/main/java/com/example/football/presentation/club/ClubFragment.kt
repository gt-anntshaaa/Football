package com.example.football.presentation.club

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.football.R
import com.example.football.databinding.FragmentClubBinding
import com.example.football.domain.common.Resource
import com.example.football.domain.common.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClubFragment : Fragment() {
    private var _binding: FragmentClubBinding? = null
    private val binding get() = _binding!!

    private val clubViewModel: ClubViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClubBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUI()

        binding.btnSave.setOnClickListener {
            val club_name = binding.etName.text.toString()
            val club_city = binding.etCity.text.toString()

            clubViewModel.insert(club_name, club_city)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeUI() {
        clubViewModel.club.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    requireContext().toast(it.data)
                }
                is Resource.Failure -> {
                    requireContext().toast(it.message)
                }

                else -> {}
            }
        })
    }


}