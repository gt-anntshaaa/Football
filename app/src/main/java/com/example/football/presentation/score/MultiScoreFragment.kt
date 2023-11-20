package com.example.football.presentation.score

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.football.R
import com.example.football.data.app.MultiScore
import com.example.football.databinding.FragmentMultiScoreBinding
import com.example.football.databinding.ItemMultiscoreBinding
import com.example.football.domain.common.Resource
import com.example.football.domain.common.toast
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MultiScoreFragment : Fragment() {
    private var _binding: FragmentMultiScoreBinding? = null
    private val binding get() = _binding!!

    private val scoreViewModel: ScoreViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMultiScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUI()

        binding.btnAddClub.setOnClickListener {
            addView()
        }

        binding.btnSave.setOnClickListener {
            savingData()
        }


    }



    private fun observeUI() {
        scoreViewModel.matches.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.data, Toast.LENGTH_SHORT).show()}
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}

                else -> {}
            }
        })
        scoreViewModel.listClubName.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading -> {
                    Log.e("testing", "observeUI: load", )}
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

    private fun savingData() {
        val count = binding.layoutAddClub.childCount


        for (i in 0 until count){
            val v = binding.layoutAddClub.getChildAt(i)

            val et_host_name: AutoCompleteTextView = v.findViewById(R.id.tv_host)
            val et_guest_name: AutoCompleteTextView = v.findViewById(R.id.tv_guest)
            val et_host_score: TextInputEditText = v.findViewById(R.id.et_host_score)
            val et_guest_score: TextInputEditText = v.findViewById(R.id.et_guest_score)


            val nameHost = et_host_name.text.toString()
            val nameGuest = et_guest_name.text.toString()
            val scoreHost = et_host_score.text.toString()
            val scoreGuest = et_guest_score.text.toString()

            scoreViewModel.doMatches(nameHost, nameGuest, scoreHost, scoreGuest)

        }
    }



    @SuppressLint("SetTextI18n")
    private fun setupDropDownMenu(data: Array<String>) {
        val count = binding.layoutAddClub.childCount

        for (i in 0 until count){
            val v = binding.layoutAddClub.getChildAt(i)

            val match: TextView = v.findViewById(R.id.tv_no_club)

            val dropDownHost: TextInputLayout = v.findViewById(R.id.dropdown_host_team)
            val dropDownGuest: TextInputLayout = v.findViewById(R.id.dropdown_guest_team)

            val num = i+1

            match.text = "Match $num"
            (dropDownHost.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(data)
            (dropDownGuest.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(data)

//            val nameHost = et_host_name.text.toString()
//            val nameGuest = et_guest_name.text.toString()
//            val scoreHost = et_host_score.text.toString().toInt()
//            val scoreGuest = et_guest_score.text.toString().toInt()
//
//            scoreViewModel.doMatches(nameHost, nameGuest, scoreHost, scoreGuest)
        }
    }

    private fun addView() {
        val inflate = LayoutInflater.from(binding.root.context).inflate(R.layout.item_multiscore, null)
        binding.layoutAddClub.addView(inflate, binding.layoutAddClub.childCount)

        //val bindingItem = ItemMultiscoreBinding.inflate(LayoutInflater.from(binding.root.context))

        scoreViewModel.load()

    }
}