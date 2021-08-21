package com.huseyinbulbul.loremopia.list.meditation

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.loremopia.common.data.Meditation
import com.huseyinbulbul.loremopia.databinding.ItemMeditationsBinding

class MeditationsViewHolder(val binding: ItemMeditationsBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(meditations: List<Meditation>?, meditationOnClick: MeditationOnClick?){
        meditations?.let {
            binding.rvMeditationsList?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = MeditationsAdapter(it, meditationOnClick)
            }
        }
    }
}