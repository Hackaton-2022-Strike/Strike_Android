package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.hs.hackton_2022.databinding.ActivityMainBinding
import kr.hs.hackton_2022.main.DataFragment
import kr.hs.hackton_2022.main.MainFragment
import kr.hs.hackton_2022.main.MyDataFragment

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
            R.id.data -> {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
            }
            R.id.main -> {
                val main_fragment = MainFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, main_fragment)
                    .commit()
            }
            R.id.my_data -> {
                val my_data_fragment = MyDataFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, my_data_fragment)
                    .commit()
            }
            R.id.post -> {
                val data_fragment = DataFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, data_fragment)
                    .commit()
            }
        }
        return true
    }
}