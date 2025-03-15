package com.example.appsolvetriangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsolvetriangles.databinding.FragmentConvDegRadBinding

class ConvDegRadFragment : Fragment() {
    lateinit var binding: FragmentConvDegRadBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentConvDegRadBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            bRadToDeg.setOnClickListener{
                InputDegs.setText(
                    degrees(InputRads.getText().toString().toDouble()) .toString()
                )
            }
            bDegToRad.setOnClickListener{
                InputRads.setText(
                    radians(InputDegs.getText().toString().toDouble()) .toString()
                )
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = ConvDegRadFragment()

    }

    fun degrees(rads:Double):Double {
        return rads * 57.29577951308232
    }
    fun radians(degs:Double):Double{
        return degs / 57.29577951308232
    }
}