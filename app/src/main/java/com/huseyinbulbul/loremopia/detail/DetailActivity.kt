package com.huseyinbulbul.loremopia.detail

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.BaseActivity
import com.huseyinbulbul.loremopia.common.data.DetailViewData
import com.huseyinbulbul.loremopia.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity() {
    lateinit var viewModel: DetailViewModel
    lateinit var bind: ActivityDetailBinding

    var mp: MediaPlayer? = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val view = setView(R.layout.activity_detail, viewModel)
        bind = DataBindingUtil.bind<ActivityDetailBinding>(view)!!

        viewModel.dataToShow.observe(this){
            bind.data = it
        }

        viewModel.soundToPlay.observe(this){
            mp?.apply {
                setDataSource(it)
                prepare()
            }
        }

        viewModel.shouldPlaySound.observe(this) {
            mp?.apply {
                if (it){
                    start()
                    bind.ivPlay.setImageDrawable(getDrawable(R.drawable.ic_pause))
                } else{
                    pause()
                    bind.ivPlay.setImageDrawable(getDrawable(R.drawable.ic_play))
                }
            }
        }

        bind.ivPlay.setOnClickListener {
            viewModel.onPlayTouched()
        }

        viewModel.onCreated()
    }

    override fun onDestroy() {
        Log.i("hus","releasing mp")
        super.onDestroy()
        mp?.let {
            it.release()
        }
        mp = null
    }
}