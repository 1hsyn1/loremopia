package com.huseyinbulbul.loremopia.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.BaseActivity
import com.huseyinbulbul.loremopia.common.data.Meditation
import com.huseyinbulbul.loremopia.common.data.Story
import com.huseyinbulbul.loremopia.databinding.ActivityListBinding
import com.huseyinbulbul.loremopia.list.meditation.MeditationOnClick
import com.huseyinbulbul.loremopia.list.story.StoryOnClick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_list.*

@AndroidEntryPoint
class ListActivity : BaseActivity() {
    lateinit var viewModel: ListViewModel
    lateinit var bind: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        val view = setView(R.layout.activity_list, viewModel)
        bind = DataBindingUtil.bind<ActivityListBinding>(view)!!

        viewModel.listResultToShow.observe(this){
            bind.rvList.apply {

                adapter = ListAdapter(it, object: MeditationOnClick{
                    override fun meditationClicked(meditation: Meditation) {
                        viewModel.meditationSelected(meditation)
                    }
                }, object: StoryOnClick{
                    override fun storyClicked(story: Story) {
                        viewModel.storySelected(story)
                    }
                })

            }
        }

        bind.rvList.apply {
            layoutManager = LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(GradientDecoration(this@ListActivity))
        }

        viewModel.onCreated()
    }
}