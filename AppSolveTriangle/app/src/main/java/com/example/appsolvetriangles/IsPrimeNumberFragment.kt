package com.example.appsolvetriangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appsolvetriangles.databinding.FragmentIsPrimeNumberBinding

class IsPrimeNumberFragment : Fragment() {
    lateinit var binding: FragmentIsPrimeNumberBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentIsPrimeNumberBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {

            if(! InputIsPrimeNumber.text.toString().isNullOrEmpty()){
                var num = InputIsPrimeNumber.text.toString().toInt()
                if (isNumPrime(num))
                    tvIsPrimeNum.text = "Число простое"
                else
                    tvIsPrimeNum.text = "Число cоставное"
            }

            bPrintPrimeNums.setOnClickListener{
                if ( LimitPrimNums.text.toString().isNullOrEmpty())
                    Toast.makeText(this@IsPrimeNumberFragment, "Введите число выше", Toast.LENGTH_LONG)
                        .show()
                else{
                    var str:String = reshetoEratosfena(LimitPrimNums.text.toString().toInt())
                                    .toString()
                    tvPrintPrimeNums.text = str.slice(1..str.count()- 2 )
                }
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = IsPrimeNumberFragment()
    }


    fun isNumPrime(num:Int):Boolean {
        if (num%2 ==0)
            return false
        else{
            var i: Int = 3
            while (i * i <= num) {
                if (num % i ==0)
                    return false
                i = i+2
            }
            return true
        }
    }

    fun reshetoEratosfena(n:Int):List<Int>{
        var i:Int = 2
        var j:Int = 0
        var A = (2..n).filter { it % 2 != 0 }.toMutableList()
        while (i*i <=n){
            if (A[i] !=0){
                j = 2*i
                for (j in (2*i)..(n+1) step i){
                    A[j] = 0
                }
            }
        }
        val B = A.filter { it !=0 }
        return B
    }
}