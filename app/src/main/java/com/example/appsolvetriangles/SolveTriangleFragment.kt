package com.example.appsolvetriangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsolvetriangles.databinding.FragmentSolveTriangleBinding
import kotlin.math.*

class SolveTriangleFragment : Fragment() {
    private var _binding: FragmentSolveTriangleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolveTriangleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            bSolveIt.setOnClickListener {
                var a: Double = 0.0; var b: Double = 0.0; var c: Double = 0.0;
                var A: Double = 0.0; var B: Double = 0.0; var C: Double = 0.0;

                if (!InputSa.text.isNullOrEmpty())
                    a = InputSa.text.toString().toDouble()
                if (!InputSb.text.isNullOrEmpty())
                    b = InputSb.text.toString().toDouble()
                if (!InputSc.text.isNullOrEmpty())
                    c = InputSc.text.toString().toDouble()
                if (!InputA.text.isNullOrEmpty())
                    A = InputA.text.toString().toDouble()
                if (!InputB.text.isNullOrEmpty())
                    B = InputB.text.toString().toDouble()
                if (!InputC.text.isNullOrEmpty())
                    C = InputC.text.toString().toDouble()

                if (isCorrectVars(a, b, c, A, B, C)) {
                    val T: Triangle = Solve_Triangle(a, b, c, A, B, C)
                    InputSa.setText(roundTo(T.side_a,6).toString())
                    InputSb.setText(roundTo(T.side_b,6).toString())
                    InputSc.setText(roundTo(T.side_c,6).toString())
                    InputA.setText(roundTo(T.A,6).toString())
                    InputB.setText(roundTo(T.B,6).toString())
                    InputC.setText(roundTo(T.C,6).toString())
                }
            }
            bClearData.setOnClickListener{
                for (el in arrayOf(InputSa,InputSb,InputSc,InputA,InputB,InputC)){
                    el.setText("")
                }
                tVforErrs.setText(R.string.Solve_triangle)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = SolveTriangleFragment()
    }
    fun isCorrectVars(_a:Double , _b:Double, _c: Double,
                       _A:Double, _B:Double, _C:Double):Boolean{
        var a = _a; var b = _b; var c = _c
        var A = _A; var B = _B; var C = _C

        binding.apply {
            // Проверка на корректность углов
            for(angle in listOf(A,B,C)){
                if (angle>=180){
                    tVforErrs.text = "Угол не может быть >=180 град."; return false}
            }
            // Проверка на отрицательность и число введённых элементов
            var counter = 0
            for (element in listOf(a,b,c,A,B,C)){
                if (element>0)
                    counter++
                else if (element<0){
                    tVforErrs.text = "Элемент не может быть <0";return false}
            }
            if (counter<3){
                tVforErrs.text = "Нужно минимум 3 стороны или угла";return false}

            // Проверка на наличие И нормальный ввод сторон
            if ((a==0.0) and (b==0.0) and (c==0.0)){
                tVforErrs.text = "Нужна хотя бы 1 сторона";return false
            }else if ( ((a+b<=c) or (a+c<=b) or (c+b<=a)) and
                        ((a>0.0) and (b>0.0) and (c>0.0)) ){
                tVforErrs.text = "2 стороны не могут быть равны или меньше третьей"
                return false
            }
        }
        return true
    }
    fun Solve_Triangle( _a:Double , _b:Double, _c: Double,
                        _A:Double, _B:Double, _C:Double ) : Triangle{
// Создание изменяемых переменных т.к. аргументы, переданные в ф-цию
// Инициализируются как val
        var a:Double = _a
        var b:Double = _b
        var c:Double = _c
        var A:Double = radians(_A)
        var B:Double = radians(_B)
        var C:Double = radians(_C)

//Особый порядок применения теорем для нахождения элементов
        // 1. сторона по т. косинусов
        if (a ==0.0 && A>0.0 && b>0.0 && c>0.0)
            a=sqrt(b*b + c*c - 2*b*c*cos(A))
        else if (b ==0.0 && B>0.0 && a>0.0 && c>0.0)
            b=sqrt(a*a + c*c - 2*a*c*cos(B))
        else if (c ==0.0 && C>0.0 && a>0.0 && b>0.0)
            c=sqrt(a*a + b*b - 2*a*b*cos(C))

        //2. Углы по т. косинусов
        if (A==0.0 && B==0.0 && C==0.0 && a>0.0 && b>0.0 && c>0.0){
            A=acos( (b*b + c*c - a*a) / (2*b*c) )
            B=asin((b * sin(A)) / a)
            C=PI - A - B
            return Triangle(a,b,c, degrees(A),degrees(B),degrees(C))
        }

        //3. Углы по т. синусов
        if (a>0.0 && A==0.0){
            if (b>0.0 && B>0.0)
                A=asin((a * sin(B)) / b)
            else if (c>0.0 && C>0.0)
                A=asin((a * sin(C)) / c)
        }
        if (b>0.0 && B==0.0){
            if (a>0.0 && A>0.0)
                B=asin((b * sin(A)) / a)
            else if (c>0.0 && C>0.0)
                B=asin((b * sin(C)) / c)
        }
        if (c>0.0 && C==0.0){
            if (a>0.0 && A>0.0)
                C=asin((a * sin(A)) / a)
            else if (B>0.0 && b>0.0)
                C=asin((c * sin(B)) / b)
        }

        //4. Углы по т. суммы углов
        if (A>0.0 && B>0.0 && C==0.0)
            C=PI - A - B
        else if (A>0.0 && C>0.0 && B==0.0)
            B=PI - A - C
        else if (B>0.0 && C>0.0 && A==0.0)
            A=PI - B - C

        //5. стороны по т. синусов
        if (a==0.0 && A>0.0){
            if (b>0.0 && B>0.0)
                a=sin(A) * b / sin(B)
            else if (c>0.0 && C>0.0)
                a=sin(A) * c / sin(C)
        }
        if (b==0.0 && B>0.0){
            if (a>0.0 && A>0.0)
                b=sin(B) * a / sin(A)
            else if (c>0.0 && C>0.0)
                b=sin(B) * c / sin(C)
        }
        if (c==0.0 && C>0.0){
            if (a>0.0 && A>0.0)
                c=sin(C) * a / sin(A)
            else if (b>0.0 && B>0.0)
                c=sin(C) * b / sin(B)
        }
        return Triangle(a,b,c, degrees(A),degrees(B),degrees(C))
    }
}