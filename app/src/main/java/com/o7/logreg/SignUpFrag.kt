package com.o7.logreg

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.o7.logreg.databinding.FragmentSignUpBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFrag : Fragment() {
    lateinit var navController: NavController
    lateinit var binding: FragmentSignUpBinding


    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnFragSignUp).setOnClickListener {
            val name = binding.edtSignUpName.editText?.text.toString()
            val email = binding.edtSignUpEmail.editText?.text.toString()
            val pass = binding.edtSignUpPass.editText?.text.toString()
            val confirmPass = binding.edtSignUpPassConfirm.editText?.text.toString()


            if(!name.isEmpty() && !pass.isEmpty() && pass.equals(confirmPass) && Companion.isValidEmail(email)){

                val bundle = Bundle().apply {
                    putString("name", name)
                    putString("email", email)
                    putString("pass", pass)
                }

                Snackbar.make(view, "Success", Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signUpFrag_to_fragmentHome, bundle)
            }else{

                if(name.isEmpty()){
                    binding.edtSignUpName.error = "name please"
                }else{
                    binding.edtSignUpName.setError(null)
                }
                if(!Companion.isValidEmail(email)) {
                    binding.edtSignUpEmail.error = "Email Not Valid"
                }else{
                    binding.edtSignUpEmail.setError(null)
                }
                if(pass.isEmpty()) {
                    binding.edtSignUpPass.error = "Password Please"
                }else{
                    binding.edtSignUpPass.setError(null)
                }
                if(!pass.equals(confirmPass)){
                    binding.edtSignUpPassConfirm.error = "Doesn't match"
                }else{
                    binding.edtSignUpPassConfirm.setError(null)
                }


                Snackbar.make(view, "Failled - Fill your Details Correctly", Snackbar.LENGTH_SHORT).show()
            }

        }

        binding.tvBottomSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFrag_to_loginFrag)
        }


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            SignUpFrag().apply {
                arguments = Bundle().apply {

                }
            }

        fun isValidEmail(email: String): Boolean{
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}