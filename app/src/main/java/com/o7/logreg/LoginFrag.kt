package com.o7.logreg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.o7.logreg.databinding.FragmentHomeBinding
import com.o7.logreg.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentLoginBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        navController = findNavController()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFragSignIn.setOnClickListener {
            val email = binding.edtSignInEmail.editText?.text.toString()
            val pass = binding.edtSignInPass.editText?.text.toString()

            if(email.isEmpty() ){
                binding.edtSignInEmail.error = "Email Please"
            }
            if(!SignUpFrag.isValidEmail(email)){
                binding.edtSignInEmail.error = "Valid Email Please"
            }else{
                binding.edtSignInEmail.setError(null)
            }
            if(pass.isEmpty()){
                binding.edtSignInPass.error = "Password is empty"
            }else{
                binding.edtSignInPass.setError(null)
            }


            if (email.isEmpty() || pass.isEmpty() || !SignUpFrag.isValidEmail(email)){
                Snackbar.make(view, "Failled - Fill your Details Correctly", Snackbar.LENGTH_SHORT).show()

            }else {
                Snackbar.make(view, "Success", Snackbar.LENGTH_SHORT).show()

                val bundle = Bundle().apply {
                    putString("email", email)
                    putString("pass", pass)
                }


                navController.navigate(R.id.action_loginFrag_to_fragmentHome, bundle)
            }
        }

        binding.tvBottomSignUp.setOnClickListener {
            navController.navigate(R.id.action_loginFrag_to_signUpFrag)

        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFrag().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}