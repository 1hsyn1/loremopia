package com.huseyinbulbul.loremopia.common

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.databinding.ActivityBaseBinding
import kotlinx.android.synthetic.main.activity_base.*

open class BaseActivity : AppCompatActivity(){
    lateinit var _viewModel: BaseViewModel
    lateinit var _bind: ActivityBaseBinding

    fun setView(layoutResID: Int, viewModel: BaseViewModel): View {
        _bind = DataBindingUtil.setContentView(this, R.layout.activity_base)
        _viewModel = viewModel
        val view = LayoutInflater.from(this).inflate(layoutResID, fl_content, false)

        _viewModel.hideKeyboard.observe(this){
            if(it != -1) {
                hideKeyboard()
            }
        }

        _viewModel.shouldShowActionBar.observe(this){
            if(!it) hideActionBar()
        }

        _viewModel.shouldShowLoading.observe(this){
            if(it) showLoading() else hideLoading()
        }

        _viewModel.titleText.observe(this){
            if(it != ""){
                setPageTitle(it)
            }
        }

        _viewModel.titleStringId.observe(this){
            if(it > -1){
                setPageTitle(it)
            }
        }

        _viewModel.shouldShowRightIcon.observe(this){
            showRightIcon(it)
        }

        _viewModel.shouldShowLeftIcon.observe(this){
            showLeftIcon(it)
        }

        _viewModel.setRightIconBitmap.observe(this){
            if(it != null){
                setRightIcon(it)
            }
        }

        _viewModel.setRightIconDrawableId.observe(this){
            if(it != -1){
                setRightIcon(it)
            }
        }

        _viewModel.setLeftIconBitmap.observe(this){
            if(it != null){
                setLeftIcon(it)
            }
        }

        _viewModel.setLeftIconDrawableId.observe(this){
            if(it != -1){
                setLeftIcon(it)
            }
        }

        _viewModel.setOnRightIconClicked.observe(this){
            if(it != null){
                setOnRightIconClicked(it)
            }
        }

        _viewModel.openActivity.observe(this){
            if(it != null){
                openActivity(it)
            }
        }

        _viewModel.showMessage.observe(this){
            if(!it.isNullOrEmpty()){
                showMessage(it)
            }
        }

        _viewModel.showMessageStringId.observe(this){
            if(it != -1){
                showMessage(it)
            }
        }

        _viewModel.close.observe(this){
            if(it != -1){
                close()
            }
        }

        iv_left_icon.setOnClickListener {
            close()
        }

        fl_content.addView(view)
        return view
    }

    fun hideActionBar() {
        apl_toolbar.visibility = View.GONE
    }

    fun showLoading() {
        if (!isOnScreen())
            return

        rl_loading.visibility = View.VISIBLE
    }

    fun hideLoading() {
        if (!isOnScreen())
            return

        rl_loading.visibility = View.GONE
    }

    fun setPageTitle(title: String) {
        if (!isOnScreen())
            return

        tv_title.text = title
    }

    fun setPageTitle(title: Int) {
        if (!isOnScreen())
            return

        setPageTitle(getString(title))
    }

    fun showRightIcon(isShow: Boolean) {
        if (!isOnScreen())
            return

        iv_right_icon.visibility = if(isShow) View.VISIBLE else View.GONE
    }

    fun showLeftIcon(isShow: Boolean) {
        if (!isOnScreen())
            return

        iv_left_icon.visibility = if(isShow) View.VISIBLE else View.GONE
    }

    fun setRightIcon(bitmap: Bitmap) {
        if (!isOnScreen())
            return

        iv_right_icon.setImageBitmap(bitmap)
    }

    fun setRightIcon(id: Int) {
        if (!isOnScreen())
            return

        iv_right_icon.setImageDrawable(resources.getDrawable(id, null))
    }

    fun setLeftIcon(bitmap: Bitmap) {
        if (!isOnScreen())
            return

        iv_left_icon.setImageBitmap(bitmap)
    }

    fun setLeftIcon(id: Int) {
        if (!isOnScreen())
            return

        iv_left_icon.setImageDrawable(resources.getDrawable(id, null))
    }

    fun setOnRightIconClicked(listener: View.OnClickListener) {
        if (!isOnScreen())
            return

        iv_right_icon.setOnClickListener(listener)
    }

    fun openActivity(cls: Class<*>) {
        if (!isOnScreen())
            return

        startActivity(Intent(this, cls))
    }

    fun close() {
        if (!isOnScreen())
            return

        finish()
    }

    fun showMessage(message: String) {
        val showMessage = if(message.isEmpty()) getString(R.string.error) else message
        AlertDialog.Builder(this)
            .setMessage(showMessage)
            .setPositiveButton(R.string.ok
            ) { dialog, which ->
                dialog?.let {
                    it.dismiss()
                }
            }
            .show()
    }

    fun showMessage(message: Int) {
        AlertDialog.Builder(this)
            .setMessage(getString(message))
            .setPositiveButton(R.string.ok
            ) { dialog, which ->
                dialog?.let {
                    it.dismiss()
                }
            }
            .show()
    }

    fun hideKeyboard() {
        if (!isOnScreen())
            return

        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isOnScreen(): Boolean {
        return !isFinishing
    }
}