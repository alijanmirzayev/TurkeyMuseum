package com.alijan.turkeymuseum.ui.city

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alijan.turkeymuseum.databinding.FragmentCityBinding
import com.alijan.turkeymuseum.ui.adapter.CityAdapter
import com.alijan.turkeymuseum.util.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CityFragment : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CityViewModel>()
    private val cityAdapter = CityAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        observeData()
    }

    private fun setAdapter() {
        binding.rvCity.adapter = cityAdapter
        cityAdapter.onClick = {
            findNavController().navigate(CityFragmentDirections.actionCityFragmentToDistrictFragment(it))
        }
    }

    private fun observeData() {
        viewModel.cities.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Error -> {

                }
                is NetworkResponse.Loading -> {

                }
                is NetworkResponse.Success -> {
                    cityAdapter.updateList(it.data!!)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}