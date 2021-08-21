package com.huseyinbulbul.loremopia.list.story

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.data.Story
import com.huseyinbulbul.loremopia.databinding.ItemStoryBinding

class StoriesAdapter(private val stories: List<Story>, private val storyOnClick: StoryOnClick?): RecyclerView.Adapter<StoriesAdapter.StoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        return StoryViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_story, parent, false))
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(stories[position], storyOnClick)
    }

    override fun getItemCount(): Int {
        return stories.size
    }


    class StoryViewHolder(val binding: ItemStoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(story: Story, storyOnClick: StoryOnClick?){
            binding.story = story
            storyOnClick?.let { onClick ->
                binding.root.setOnClickListener {
                    onClick.storyClicked(story)
                }
            }
        }
    }
}