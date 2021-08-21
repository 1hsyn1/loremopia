package com.huseyinbulbul.loremopia.list.story

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.loremopia.common.data.Story
import com.huseyinbulbul.loremopia.databinding.ItemStoriesBinding

class StoriesViewHolder(val binding: ItemStoriesBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(stories: List<Story>?, storyOnClick: StoryOnClick?){
        stories?.let {
            binding.rvStoriesList?.apply {
                layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
                adapter = StoriesAdapter(it, storyOnClick)
            }
        }
    }
}