package com.o7.logreg

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.o7.logreg.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val NAME = "name"
private const val EMAIL = "email"
private const val PASS = "pass"
private const val CONTACT = "contact"
/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var name: String? = null
    private var email: String? = null
    private var pass: String? = null
    private var contact: String? = null
    var list = arrayOf("one", "two", "three", "four")
//    lateinit var navController: NavController
    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "created", Toast.LENGTH_SHORT).show()


        arguments?.let {
            name = it.getString(NAME, "empty")
            email = it.getString(EMAIL, "empty")
            pass = it.getString(PASS, "empty")
            contact = it.getString(CONTACT, "empty")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHomeName.setOnClickListener {

            AlertDialog.Builder(requireContext()).apply{
                setMessage("Name - $name")

                setNeutralButton("Cancel"){ _ , _ ->
                }

                setCancelable(true)
                create()
                show()
            }
        }

        binding.btnHomeEmail.setOnClickListener {

            AlertDialog.Builder(requireContext()).apply{
                setMessage("Email - $email")

                setNeutralButton("Cancel"){ _ , _ ->
                }

                setCancelable(true)
                create()
                show()
            }


            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/html")
            intent.putExtra(Intent.EXTRA_EMAIL, email)
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.")
            startActivity(Intent.createChooser(intent, "Send Email"))


        }
        binding.btnHomePass.setOnClickListener {

            AlertDialog.Builder(requireContext()).apply{
                setMessage("Password - $pass")

                setNeutralButton("Cancel"){ _ , _ ->
                }

                setCancelable(true)
                create()
                show()
            }
        }

        binding.btnHomeSMS.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW);
            //intent.setData(Uri.parse("sms:"));
            intent.setType("vnd.android-dir/mms-sms");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            intent.putExtra("address",  contact);
            context?.startActivity(intent);


        }


        binding.btnHomeShowAll.setOnClickListener {
            val pramList = arrayOf(name, email, pass, contact)


                AlertDialog.Builder(requireContext()).apply{
                    setMessage("Details\n\n$name\n$email\n$pass\n$contact")
                    setItems(list){ _ , which ->
                        run {
                            Toast.makeText(
                                activity,
                                list[which], Toast.LENGTH_LONG
                            ).show()
                        }

                    }

                    setNeutralButton("Cancel"){ _ , _ ->
                    }

                     setCancelable(true)
                    create()
                    show()
                }

        }



    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentHome().apply {
                arguments = Bundle().apply {

                }
            }
    }
}