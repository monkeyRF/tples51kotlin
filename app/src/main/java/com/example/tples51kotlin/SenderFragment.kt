package com.example.tples51kotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider

class SenderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("LifecycleTag", "SenderFragment: onCreateView")
        return inflater.inflate(R.layout.fragment_sender, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("LifecycleTag", "SenderFragment: onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        // находим поле ввода на фрагменте
        val etMessage = view.findViewById<EditText>(R.id.etMessage)

        // Получаем модель
        val viewModelS = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // находим кнопку на фрагменте и подписываемся на нажатие
        view.findViewById<Button>(R.id.bSend).setOnClickListener {
            // Вызываем функцию замены фрагмента и передаем в нее текст из поля ввода через бандл
            replaceFragment(etMessage.text.toString())

            // Передаем сообщение через LiveData
            viewModelS.setTextMessage(etMessage.text.toString())
        }
    }

    // Функция замены фрагмента
    private fun replaceFragment(textMessage: String) {
        Log.i("LifecycleTag", "replace to ReceiverFragment")
        // создаем экземпляр второго фрагмента, в качестве аргумента передает текст из поля ввода
        val receiverFragment = ReceiverFragment.newInstance(textMessage)

        //Обращаемся к активити и ее менеджеру фрагментов. Используем backStack.
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_receiver, receiverFragment)
            .addToBackStack(null)
            .commit()
    }
}