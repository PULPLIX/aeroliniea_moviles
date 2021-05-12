package com.example.aerolinea.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aerolinea.databinding.ActivityLoginBinding
import com.example.aerolinea.databinding.ActivityUserViewBinding

class UserView : AppCompatActivity() {
    private lateinit var binding: ActivityUserViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserViewBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
    }
}