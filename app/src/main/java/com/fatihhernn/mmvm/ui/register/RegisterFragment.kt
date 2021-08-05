package com.fatihhernn.mmvm.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.fatihhernn.mmvm.R
import com.fatihhernn.mmvm.databinding.FragmentRegisterBinding
import com.fatihhernn.mmvm.util.Resource
import com.fatihhernn.mmvm.util.gone
import com.fatihhernn.mmvm.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var _binding:FragmentRegisterBinding
    private val viewModel:RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.saveButton.setOnClickListener {
            var userName= _binding.userNameTextView.text.toString()
            var email=_binding.mailTextView.text.toString()
            var password=_binding.passwordTextView.text.toString()

            viewModel.register(email,userName,password).observe(
                viewLifecycleOwner, Observer {
                    when(it.status){
                        Resource.Status.LOADING ->{
                            _binding.progressBar.show()
                        }
                        Resource.Status.SUCCESS ->{
                            Toast.makeText(context,"Register is success! ${it.message}",Toast.LENGTH_LONG).show()
                            _binding.progressBar.gone()
                        }
                        Resource.Status.ERROR ->{
                            Toast.makeText(context,"Register is failure! ${it.message}",Toast.LENGTH_LONG).show()
                            _binding.progressBar.gone()
                        }
                    }
                }
            )
        }
    }
}