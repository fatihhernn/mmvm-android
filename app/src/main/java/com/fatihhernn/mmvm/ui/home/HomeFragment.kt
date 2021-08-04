package com.fatihhernn.mmvm.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fatihhernn.mmvm.R
import com.fatihhernn.mmvm.databinding.FragmentHomeBinding
import com.fatihhernn.mmvm.util.Resource

class HomeFragment : Fragment() {

    private lateinit var _binding:FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentHomeBinding.inflate(inflater,container,false)
        return  _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchRickMortyList().observe(viewLifecycleOwner,{
            when(it.status){
                Resource.Status.LOADING ->{

                }
                Resource.Status.SUCCESS ->{

                }
                Resource.Status.ERROR ->{

                }
            }
        })
    }
}