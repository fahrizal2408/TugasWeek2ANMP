package com.appsolution.adv160420134week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    fun randomAndCalculateResult(): Int{
        var num1 = Random.nextInt(0,100)
        var num2 = Random.nextInt(0,100)
        var hasil = num1+num2
        var soal = requireView().findViewById<TextView>(R.id.textViewSoal)
        soal.text = "$num1 + $num2"
        return hasil
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textViewName=view.findViewById<TextView>(R.id.textViewName)
        var hasil = randomAndCalculateResult()
        var point = 0
        if(arguments !=null){
            val playerName= GameFragmentArgs.fromBundle(requireArguments()).playerName
            textViewName.text="$playerName's Turn"
        }
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener{
            val textJawaban=view.findViewById<EditText>(R.id.editTextJawaban).text.toString().toInt()
            if(hasil.equals(textJawaban)){
                point+=1
                hasil = randomAndCalculateResult()
            }
            else{
                val action = GameFragmentDirections.actionGameOver(point)
                Navigation.findNavController(it).navigate(action)
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
         * @return A new instance of fragment GameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}