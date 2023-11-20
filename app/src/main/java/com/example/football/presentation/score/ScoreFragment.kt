package com.example.football.presentation.score

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.football.R
import com.example.football.databinding.FragmentScoreBinding
import com.example.football.domain.common.Resource
import com.example.football.domain.common.toast
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScoreFragment : Fragment() {
    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    private val scoreViewModel: ScoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()

        scoreViewModel.load()

        binding.btnSave.setOnClickListener {
            onSaved()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSaved() {
        val nameHost = binding.tvHost.text.toString()
        val nameGuest = binding.tvGuest.text.toString()

        val scoreHost = binding.etHostScore.text.toString()
        val scoreGuest = binding.etGuestScore.text.toString()

        scoreViewModel.doMatches(nameHost, nameGuest, scoreHost, scoreGuest)
//        if (isComplete(nameHost, nameGuest)){
//
//        }


    }


    private fun observeUI() {
        scoreViewModel.matches.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    requireContext().toast(it.data)
                    //Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()
                    }
                is Resource.Failure -> {
                    requireContext().toast(it.message)}

                else -> {}
            }
        })
        scoreViewModel.listClubName.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {Log.e("testing", "observeUI: load", )}
                is Resource.Success -> {
                    setupDropDownMenu(it.data)
                }
                is Resource.Failure -> {
                    Log.e("testing", "observeUI: ${it.message}", )
                }

                else -> {}
            }
        })
    }

    private fun setupDropDownMenu(data: Array<String>) {
        (binding.dropdownHostTeam.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(data)
        (binding.dropdownGuestTeam.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(data)
    }



}