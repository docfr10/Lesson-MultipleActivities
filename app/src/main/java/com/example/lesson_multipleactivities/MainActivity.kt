package com.example.lesson_multipleactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_multipleactivities.databinding.ActivityMainBinding

// Класс первой активности
class MainActivity : AppCompatActivity() {
    // Создание объекта для запуска SecondActivity и получения результата
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // Проверка, что результат был успешным
            if (result.resultCode == RESULT_OK) {
                // Извлечение ответа из Intent
                val reply = result.data?.getStringExtra("EXTRA_REPLY")
                // Отображение ответа в TextView
                mainBinding.textView2.text = "Ответ: $reply"
            }
            else
                mainBinding.textView2.text = "Поле для ввода текста было пустым!"
        }

    // Binding для MainActivity
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Activity 1", "onCreate")

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        // Слушатель нажатий на кнопке
        mainBinding.button2.setOnClickListener {
            // Получение введенного сообщения
            val message = mainBinding.editText2.text.toString()
            // Создание Intent для запуска SecondActivity
            val intent = Intent(this, SecondActivity::class.java)
            // Добавление сообщения в Intent
            intent.putExtra("EXTRA_MESSAGE", message)
            // Использование startForResult для запуска активности и получения результата
            startForResult.launch(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity 1", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity 1", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity 1", "onDestroy")
    }
}