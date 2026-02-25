package com.example.kittystore.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {

    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    protected abstract fun inflateBinding(inflater: LayoutInflater): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = inflateBinding(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    protected open fun setupUi() {}

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected fun navigateTo(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    protected fun showToast(uiText: UiText) {
        Toast.makeText(this, uiText.asString(this), Toast.LENGTH_SHORT).show()
    }
}