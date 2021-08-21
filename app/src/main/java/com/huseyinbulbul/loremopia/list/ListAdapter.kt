package com.huseyinbulbul.loremopia.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.data.ListResult
import com.huseyinbulbul.loremopia.common.data.Story
import com.huseyinbulbul.loremopia.databinding.ItemListTitleBinding
import com.huseyinbulbul.loremopia.databinding.ItemStoriesBinding
import com.huseyinbulbul.loremopia.list.meditation.MeditationOnClick
import com.huseyinbulbul.loremopia.list.meditation.MeditationsViewHolder
import com.huseyinbulbul.loremopia.list.story.StoriesViewHolder
import com.huseyinbulbul.loremopia.list.story.StoryOnClick

class ListAdapter(val listResult: ListResult,
                  val meditationOnClick: MeditationOnClick?,
                  val storyOnClick: StoryOnClick?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
            1 -> TitleViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_list_title, parent, false))
            2 -> MeditationsViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_meditations, parent, false))
            3 -> BannerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false))
            4 -> StoriesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_stories, parent, false))
            else -> LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TitleViewHolder -> {
                val title = if(position == 0 && !listResult.meditations.isNullOrEmpty()) holder.itemView.context.getString(R.string.meditations)
                                else holder.itemView.context.getString(R.string.stories)
                holder.bind(title)
            }
            is MeditationsViewHolder -> holder.bind(listResult.meditations, meditationOnClick)
            is StoriesViewHolder -> holder.bind(listResult.stories, storyOnClick)
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        listResult.meditations?.let {
            count += 2
        }

        listResult.stories?.let {
            count+= 2
        }

        if(listResult.isBannerEnabled == true)
            count += 1

        return if(count == 0) 1 else count
    }

    /**
     *  0 -> error loading
     *  1 -> title
     *  2 -> meditaions list
     *  3 -> banner
     *  4 -> stories list
     *  5 -> loading
     */
    override fun getItemViewType(position: Int): Int {
        return when (position){
            0 -> if(itemCount == 1) 0 else if (listResult.meditations == null && listResult.isBannerEnabled == true) 3 else 1
            1 -> if (listResult.meditations != null) 2 else if (listResult.stories != null) 4 else 5
            2 -> if (listResult.meditations != null && listResult.isBannerEnabled == true) 3 else 1
            3 -> if(listResult.stories != null && listResult.isBannerEnabled == true) 1 else 5
            4 -> if(listResult.stories != null && listResult.isBannerEnabled == true) 4 else 5
            else -> 5
        }
    }

    class LoadingViewHolder(v: View): RecyclerView.ViewHolder(v)
    class BannerViewHolder(v: View): RecyclerView.ViewHolder(v)

    class TitleViewHolder(val binding: ItemListTitleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(title: String){
            binding.tvTitle.text = title
        }
    }
}