package com.example.kittystore.ui.screen.store

import android.view.LayoutInflater
import com.example.kittystore.databinding.ActivityStoreBinding
import com.example.kittystore.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreActivity : BaseActivity<ActivityStoreBinding>() {

    override fun inflateBinding(inflater: LayoutInflater) = ActivityStoreBinding.inflate(inflater)
}