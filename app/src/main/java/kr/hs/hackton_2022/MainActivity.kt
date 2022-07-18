package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.hs.hackton_2022.databinding.ActivityMainBinding
import kr.hs.hackton_2022.main.MainFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<View>(R.id.bottom_menu) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main -> {
                val main_fragment = MainFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, main_fragment)
                    .commit()
            }
        }
        return true
    }
}