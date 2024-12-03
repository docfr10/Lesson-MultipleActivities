package com.example.lesson_multipleactivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lesson_multipleactivities.databinding.ActivitySecondBinding

// Класс второй активности
class SecondActivity : AppCompatActivity() {
    // Binding для SecondActivity
    private lateinit var secondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("Activity 2", "onCreate")

        secondBinding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(secondBinding.root)

        // Получение сообщения из Intent, переданное из MainActivity
        val message = intent.getStringExtra("EXTRA_MESSAGE")
        secondBinding.textView.text = message

        // Устанавливаем слушатель на кнопку отправки ответа
        secondBinding.button.setOnClickListener {
            // Получение текста ответа из EditText
            val reply = secondBinding.editText.text.toString()
            // Создание нового Intent для отправки результата
            val replyIntent = Intent()
            replyIntent.putExtra("EXTRA_REPLY", reply)
            // Отправка резульата обратно в MainActivity
            if (secondBinding.editText.text.isNotEmpty())
                setResult(Activity.RESULT_OK, replyIntent)
            else
                setResult(Activity.RESULT_CANCELED, replyIntent)
            // Закрытие текущей активности
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        Log.i("Activity 2", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Activity 2", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Activity 2", "onDestroy")
    }
}