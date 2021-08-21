package com.huseyinbulbul.loremopia.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.BaseActivity
import com.huseyinbulbul.loremopia.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    lateinit var viewModel: LoginViewModel
    lateinit var bind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProvider(this).get(LoginViewModel::class.java)
        val view = setView(R.layout.activity_login, viewModel)
        bind = DataBindingUtil.bind<ActivityLoginBinding>(view)!!

        bind.tvContinue.setOnClickListener {
            viewModel.continueTouched(et_username.text.toString(), et_password.text.toString())
        }

        viewModel.onCreated()
    }
}