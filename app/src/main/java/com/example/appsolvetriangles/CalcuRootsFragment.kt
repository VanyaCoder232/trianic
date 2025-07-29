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
                for (edtxt in listOf(EdA,EdB,EdN,EdX)){
                    if (!edtxt.text.isNullOrEmpty())
                        counter++
                }
                if (counter!=3){
                    tvForErrs.setText("Введите 3 элемента")
                }
                else{
                    var A:Double = 0.0; var B:Double = 0.0;
                    var N:Double = 0.0; var X:Double = 0.0;
                    if (!EdA.text.isNullOrEmpty())
                        A = EdA.text.toString().toDouble()
                    if (!EdB.text.isNullOrEmpty())
                        B = EdB.text.toString().toDouble()
                    if (!EdN.text.isNullOrEmpty())
                        N = EdN.text.toString().toDouble()
                    if (!EdX.text.isNullOrEmpty())
                        X = EdX.text.toString().toDouble()

                    if(B<0.0 && A!=0.0) {
                        if (N % 2 == 0.0 && N != 0.0)
                            tvForErrs.setText("Корень четной степени из числа <0")
                        else if (N % 2 == 1.0) {
                            EdX.setText(roundTo(-1.0 * A * Math.pow(-B, 1.0 / N), 6).toString())
                        }else if (N==0.0){
                            if (X/A>0.0)
                                tvForErrs.setText("Корень числа <0 больше 0? Классно решили затестить")
                            else if (X/A<0.0)
                                EdN.setText(   roundTo(1/log(-1*X/A , -B),6).toString()  )
                        }
                    }
                    else{
                        if (X==0.0)
                            EdX.setText(roundTo(A * Math.pow(B ,1.0/N ),6).toString())
                        else if (A==0.0)
                            EdA.setText(roundTo(X / Math.pow(B ,1.0/N ),6).toString())
                        else if (B==0.0)
                            EdB.setText(roundTo(Math.pow(X/A,N),6).toString())
                        else if (N==0.0)
                            EdN.setText(roundTo(1/log(X/A , B),6).toString())
                    }
                }
            }
            bClear.setOnClickListener{
                EdA.setText("")
                EdB.setText("")
                EdX.setText("")
                EdN.setText("")
                tvForErrs.setText("A * n√B = X")
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = CalcuRootsFragment()
    }
}