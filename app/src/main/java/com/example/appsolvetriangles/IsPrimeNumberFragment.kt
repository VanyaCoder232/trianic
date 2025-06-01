package com.example.appsolvetriangles

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.collection.intFloatMapOf
import com.example.appsolvetriangles.databinding.FragmentIsPrimeNumberBinding

class IsPrimeNumberFragment : Fragment() {
    private var _binding: FragmentIsPrimeNumberBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIsPrimeNumberBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.apply {
            bPrintPrimeNums.setOnClickListener{
                var lowLim:Int = 1
                if ( UpLimPrimNums.text.isNullOrEmpty())
                    UpLimPrimNums.error = "Введите верхний предел"
                else{
                    val upLim:Int = UpLimPrimNums.text.toString().toInt()
                    if(upLim<=0)
                        UpLimPrimNums.error = "Верх. предел должен быть больше 0"
                    else{
                        if (!LowLimPrimNums.text.isNullOrEmpty())
                            lowLim = LowLimPrimNums.text.toString().toInt()
                        if (lowLim<0)
                            LowLimPrimNums.error = "Нижний предел должен быть больше или равен 0"
                        else{
                            if (lowLim>=upLim) {
                                tvPrintPrimeNums.setText("Хорошая попытка меня взломать ;)")
                                tvCountNums.setText("Желаю удачи. Хе-хе")
                            }else{
                                val listPrNums:List<Int> = reshetoEratosfena(lowLim,upLim)
                                tvCountNums.setText("Простых чисел: ${listPrNums.size.toString()}")
                                val str:String = listPrNums.toString()
                                tvPrintPrimeNums.setText(str.slice(1..str.count()-2))
                            }
                        }
                    }
                }
            }
            bClearNums.setOnClickListener{
                tvPrintPrimeNums.setText(R.string.here_will_print_prime_nums)
                tvCountNums.setText(R.string.And_how_many_them)
                LowLimPrimNums.setText("")
                UpLimPrimNums.setText("")
                UpLimPrimNums.setError(null)
                LowLimPrimNums.setError(null)


            }
            val txtWatcherIsPrNums = object :TextWatcher {

                override fun beforeTextChanged(s: CharSequence?,start: Int,count: Int,after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (!InputIsPrimeNumber.text.isNullOrEmpty()){
                        var num = InputIsPrimeNumber.text.toString().toULong()

                        if (num==0UL)
                            tvIsPrimeNum.text = "Введите число"
                        else if (num==1UL)
                            tvIsPrimeNum.text = "Число 1 НИ простое, НИ составное"
                        else {
                            if (isNumPrime(num))
                                tvIsPrimeNum.text = "Число простое"
                            else
                                tvIsPrimeNum.text = "Число cоставное"
                        }
                    } else {
                        tvIsPrimeNum.setText("Введите число")
                    }
                }
            }
            InputIsPrimeNumber.addTextChangedListener(txtWatcherIsPrNums)

        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = IsPrimeNumberFragment()
    }


    fun isNumPrime(num:ULong):Boolean {
        if (num==2UL || num ==3UL)
            return true
        else if((num%2UL ==0UL) || (num%3UL==0UL)|| (num<=1UL))
            return false
        else{
            var i: ULong = 5UL
            while (i * i <= num) {
                if (  (num % i ==0UL)||(num%(i+2UL)==0UL)  )
                    return false
                i += 6UL
            }
            return true
        }
    }
    fun reshetoEratosfena(Llim:Int ,Ulim:Int):List<Int>{
        var i:Int = 2
        var j:Int = 0
        var A = (0..Ulim).toMutableList()
        A[1] = 0
        while(i*i<=Ulim){
            if (A[i]!=0){
                j = i+i
                while (j<=(Ulim)){
                    A[j] = 0
                    j = j+i
                }
            }
            i++
        }
        return A.slice(Llim..Ulim).filter { it!=0 }
    }
}