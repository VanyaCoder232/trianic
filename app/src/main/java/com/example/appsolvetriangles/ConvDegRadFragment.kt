package com.example.appsolvetriangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appsolvetriangles.databinding.FragmentConvDegRadBinding

class ConvDegRadFragment : Fragment() {
    private var _binding: FragmentConvDegRadBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConvDegRadBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            bRadToDeg.setOnClickListener{
                if(InputRads.text.isNullOrEmpty())
                    InputRads.error = "Введите число"
                else
                    InputDegs.setText(
                        degrees(InputRads.getText().toString().toDouble()).toString() )
            }
            bDegToRad.setOnClickListener{
                if (InputDegs.text.isNullOrEmpty())
                    InputDegs.error = "Введите число"
                else
                    InputRads.setText(
                        radians(InputDegs.getText().toString().toDouble()) .toString() )
            }
            bClearData.setOnClickListener{
                InputRads.setText("")
                InputDegs.setText("")
                InputDegs.setError(null)
                InputRads.setError(null)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ConvDegRadFragment()
    }
}