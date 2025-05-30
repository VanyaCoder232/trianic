package com.example.appsolvetriangles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appsolvetriangles.databinding.FragmentAppInfoBinding

/* Этот фрагмент просто отображает информацию о приложении
Он же самый короткий в приложении. Просто отображает 2 TextView и всё.
Ничего не вычисляет, просто стоит для красоты
(ну и для информативности)
 */
class AppInfoFragment : Fragment() {
    private var _binding: FragmentAppInfoBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAppInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = AppInfoFragment()
    }
}