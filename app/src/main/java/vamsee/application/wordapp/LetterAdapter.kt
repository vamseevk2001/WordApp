package vamsee.application.wordapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class LetterAdapter(private val listener: OnClick) : RecyclerView.Adapter<LetterViewHolder>() {

    val list = ('A').rangeTo('Z').toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        val viewHolder = LetterViewHolder(view)
        view.setOnClickListener {
            listener.onLetterClick(list[viewHolder.adapterPosition], view)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list.get(position)
        holder.letters.text = item.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class LetterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val letters = itemView.findViewById<Button>(R.id.buttonItem)
}

interface OnClick {
    fun onLetterClick(item: Char, view: View)
}