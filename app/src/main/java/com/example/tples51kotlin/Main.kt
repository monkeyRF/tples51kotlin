package com.example.tples51kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("LifecycleTag", "Activity: onCreate")
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        title = "Activity with fragment"


        // Проверка нужна, чтобы при срабатывании onCreate не создавать два одинаковых фрагмента и срабатывания двух onResume
        if (savedInstanceState == null) {
            addFragmentA()
        }
    }

    // Функция создания фрагмента
    private fun addFragmentA() {
        Log.i("LifecycleTag", "Activity: addFragmentA")
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_sender, SenderFragment())
            .commit()
    }
}