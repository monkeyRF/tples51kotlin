package com.example.tples51kotlin

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider

class ReceiverFragment : Fragment() {

    override fun onAttach(context: Context) {
        Log.i("Lifecycle", "ReceiverFragment: onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("Lifecycle", "ReceiverFragment: onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Lifecycle", "ReceiverFragment: onCreateView")
        return inflater.inflate(R.layout.fragment_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Достаем из аргументов бандла текст сообщения
        val textMessage = arguments?.getString(
            TEXT_MESSAGE,
            "defaultMessage"
        )

        // находим текстовое поле на фрагменте
        val tvMessage = view.findViewById<TextView>(R.id.tvMessage)
        //Заполняем поле через аргументы бандла
        //Закомментировал получение через бандл в пользу LiveData
        tvMessage.text = textMessage

        // Получаем модель
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // Устанавливаем наблюдателя. Получаем текст из первого фрагмента
        viewModel.textMessage.observe(viewLifecycleOwner) {text ->
            tvMessage.text = text.toString()
        }

        // находим кнопку и подписываемся на клик
        view.findViewById<Button>(R.id.bRead).setOnClickListener {
            // Меняем сообщение
            tvMessage.text = "All messages is read"
            // Сохраняем значение текстового поля в LiveData
            viewModel.setTextMessage(tvMessage.text.toString())
        }
    }

    companion object {
        private const val TEXT_MESSAGE = "TEXT_MESSAGE"

        fun newInstance(messageText: String): Fragment =
            ReceiverFragment().apply {
                val bundle = Bundle()
                bundle.putString(TEXT_MESSAGE, messageText)
                arguments = bundle
            }
    }
}