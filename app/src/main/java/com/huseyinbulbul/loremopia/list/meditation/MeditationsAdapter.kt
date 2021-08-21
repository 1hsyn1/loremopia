package com.huseyinbulbul.loremopia.list.meditation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.huseyinbulbul.loremopia.R
import com.huseyinbulbul.loremopia.common.data.Meditation
import com.huseyinbulbul.loremopia.databinding.ItemMeditationBinding

class MeditationsAdapter(private val list: List<Meditation>, val meditationOnClick: MeditationOnClick?): RecyclerView.Adapter<MeditationsAdapter.MeditationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        return MeditationViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_meditation, parent, false))
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        holder.bind(list[position], meditationOnClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MeditationViewHolder(private val binding: ItemMeditationBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(meditation: Meditation, meditationOnClick: MeditationOnClick?){
            binding.meditation = meditation
            meditationOnClick?.let { onClick ->
                Log.i("hus","0")
                binding.root.setOnClickListener {
                    Log.i("hus","1")
                    onClick.meditationClicked(meditation)
                }
            }
        }
    }
}