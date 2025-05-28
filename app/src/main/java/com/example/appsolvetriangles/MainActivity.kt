package com.example.appsolvetriangles

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import com.example.appsolvetriangles.databinding.MainActivityBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var binding: MainActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFrag(SolveTriangleFragment.newInstance(),R.id.main_frag)
        binding.apply {
            NavViewLeft.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menu_item_solve_trian -> openFrag(SolveTriangleFragment.newInstance())
                    R.id.menu_item_calcu -> Toast.makeText(this@MainActivity,"В разработке :D",Toast.LENGTH_LONG).show()
                    R.id.menu_item_conv_deg_rad -> openFrag(ConvDegRadFragment.newInstance())
                    R.id.menu_item_prime_numbers -> openFrag(IsPrimeNumberFragment.newInstance())
                    R.id.menu_item_calcu_roots -> openFrag(CalcuRootsFragment.newInstance())

                    R.id.menu_item_info -> openFrag(AppInfoFragment.newInstance())
                    R.id.menu_item_settings -> Toast.makeText(this@MainActivity,"В разработке :D",Toast.LENGTH_LONG).show()
                }
                true
            }
        }
    }

    //Функция открытия фрагмента для удобства
    fun openFrag(openingFrag:Fragment , place: Int = R.id.main_frag){
        supportFragmentManager.
            beginTransaction().
            replace(place, openingFrag).
            commit()
    }
}

