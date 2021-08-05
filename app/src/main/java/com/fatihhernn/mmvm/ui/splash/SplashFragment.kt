package com.fatihhernn.mmvm.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fatihhernn.mmvm.R
import com.fatihhernn.mmvm.databinding.FragmentAuthBinding
import com.fatihhernn.mmvm.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var _binding:FragmentSplashBinding
    private val viewModel:SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSplashBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeNavigationLiveData().observe(viewLifecycleOwner, Observer {
            when(it){
                "auth" -> findNavController().navigate(R.id.action_splashFragment_to_authFragment)
                "home" -> findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
        })
        viewModel.checkTokenAndNavigation()
    }
}