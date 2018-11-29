package com.juangm.roomwordsample.data.ui.wordlist

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import com.juangm.roomwordsample.R
import com.juangm.roomwordsample.data.ui.models.Word

class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var mInflater = LayoutInflater.from(context)
    private var mWords: List<Word>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if(mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.text = current.word
        } else {
            holder.wordItemView.text = "No word"
        }
    }

    internal fun setWords(words: List<Word>?) {
        mWords = words
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if(mWords != null) mWords!!.size else 0
    }

    class WordViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }
}