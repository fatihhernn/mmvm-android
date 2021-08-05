package com.fatihhernn.mmvm.ui.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fatihhernn.mmvm.R
import com.fatihhernn.mmvm.databinding.FragmentLoginBinding
import com.fatihhernn.mmvm.util.Resource
import com.fatihhernn.mmvm.util.gone
import com.fatihhernn.mmvm.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var _binding:FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.saveButton.setOnClickListener {
            val mail=_binding.mailTextView.text.toString()
            val password=_binding.passwordTextView.text.toString()
            viewModel.login(mail,password).observe(viewLifecycleOwner, Observer {
                when(it.status){
                    Resource.Status.LOADING ->{
                        _binding.progressBar.show()
                    }
                    Resource.Status.SUCCESS ->{
                        _binding.progressBar.gone()
                    }
                    Resource.Status.ERROR ->{
                        _binding.progressBar.gone()
                    }
                }
            })
        }
    }

}