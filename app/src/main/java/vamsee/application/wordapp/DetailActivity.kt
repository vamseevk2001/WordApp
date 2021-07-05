package vamsee.application.wordapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity(), OnItemClick {

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }


    private lateinit var mAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val letter = intent?.extras?.getChar(LETTER)

        words.layoutManager = LinearLayoutManager(this)
        mAdapter = letter?.let { WordAdapter(it, this, this) }!!
        words.adapter = mAdapter

//        words.addItemDecoration(
//            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
//        )
    }

    override fun onWordClick(word: String) {
        val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${word}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        this.startActivity(intent)
    }
}