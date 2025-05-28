package com.example.appsolvetriangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsolvetriangles.databinding.FragmentCalcuRootsBinding
import com.example.appsolvetriangles.databinding.FragmentConvDegRadBinding
import kotlin.math.*

class CalcuRootsFragment : Fragment() {
    private var _binding: FragmentCalcuRootsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalcuRootsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            bFindFourthEl.setOnClickListener{
                var counter = 0
                for (edtxt in listOf(edForA,edForB,edForN,edForX)){
                    if (!edtxt.text.isNullOrEmpty())
                        counter++
                }
                if (counter<3){
                    tvForErrs.setText("Введите 3 элемента")
                }
                else if (counter==4){
                    tvForErrs.setText("Введите РОВНО 3 элемента для нахождения 4-го")
                }
                else{
                    var A:Double = 0.0; var B:Double = 0.0;
                    var N:Double = 0.0; var X:Double = 0.0;
                    if (!edForA.text.isNullOrEmpty())
                        A = edForA.text.toString().toDouble()
                    if (!edForB.text.isNullOrEmpty())
                        B = edForB.text.toString().toDouble()
                    if (!edForN.text.isNullOrEmpty())
                        N = edForN.text.toString().toDouble()
                    if (!edForX.text.isNullOrEmpty())
                        X = edForX.text.toString().toDouble()

                    if (N%2==0.0 && B<=0.0)
                        tvForErrs.setText("Корень четной степени из числа <0")
                    else{
                        if (X==0.0)
                            edForX.setText(String.format("%.5f",A * Math.pow(B ,1.0/N )))
                        else if (A==0.0)
                            edForA.setText(String.format("%.5f",X / Math.pow(B ,1.0/N )))
                        else if (B==0.0)
                            edForB.setText(String.format("%.5f",Math.pow(X/A,N)))
                        else if (N==0.0)
                            edForN.setText(String.format("%.5f",1/log(X/A , B)))
                    }
                }
            }
            bClear.setOnClickListener{
                edForA.setText("")
                edForB.setText("")
                edForX.setText("")
                edForN.setText("")
                tvForErrs.setText("A * n√B = X")
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = CalcuRootsFragment()
    }
}