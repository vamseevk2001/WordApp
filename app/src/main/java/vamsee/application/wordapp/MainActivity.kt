package vamsee.application.wordapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClick {

    lateinit var  mAdapter: LetterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        letters.layoutManager = LinearLayoutManager(this)
        mAdapter = LetterAdapter(this)
        letters.adapter = mAdapter
    }

    override fun onLetterClick(item: Char) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.LETTER, item)
        this.startActivity(intent)
    }
}