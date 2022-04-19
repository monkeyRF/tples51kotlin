package com.example.tplesson51kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    //lateinit var binding: ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFrag(senderFragment.newInstance(), R.id.senderFragment)
        openFrag(receiverFragment.newInstance(), R.id.receiverFragment)

    }

    private fun openFrag(f: Fragment, idHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.senderFragment, f)
            .commit()
    }

}