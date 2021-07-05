package vamsee.application.wordapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(private val letter: Char, context: Context, private val listener: OnItemClick): RecyclerView.Adapter<WordsViewHolder>() {

    private var filteredWordList: List<String> = listOf("vamsee", "krishna", "vamseevk2001")


    init{
        val words = context.resources.getStringArray(R.array.words).toList()
        filteredWordList = words
            .filter { it.startsWith(letter, ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        val viewHolder = WordsViewHolder(view)
        view.setOnClickListener{
            listener.onWordClick(filteredWordList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val item = filteredWordList[position]
        holder.word.text = item
    }

    override fun getItemCount(): Int {
        return filteredWordList.size
    }

}

class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val word: Button = itemView.findViewById<Button>(R.id.buttonItem)
}

interface OnItemClick{
    fun onWordClick(word: String)
}