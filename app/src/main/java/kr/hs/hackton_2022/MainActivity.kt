package kr.hs.hackton_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import kr.hs.hackton_2022.databinding.ActivityMainBinding
import kr.hs.hackton_2022.main.MainFragment
import kr.hs.hackton_2022.main.MyDataFragment
import kr.hs.hackton_2022.main.PostFragment
import kr.hs.hackton_2022.main.SchoolDataFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<View>(R.id.bottom_menu) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.data -> {
                val data_fragment = SchoolDataFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, data_fragment)
                    .commit()
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
                val post_fragment = PostFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, post_fragment)
                    .commit()
            }
        }
        return true
    }
}