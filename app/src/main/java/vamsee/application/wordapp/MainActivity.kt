package vamsee.application.wordapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClick {

    lateinit var mAdapter: LetterAdapter
    private var isLinearLayout = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chooseLayout()
    }

    override fun onLetterClick(item: Char) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.LETTER, item)
        this.startActivity(intent)
    }

    private fun chooseLayout() {
        if (isLinearLayout) {
            letters.layoutManager = LinearLayoutManager(this)
        } else {
            letters.layoutManager = GridLayoutManager(this, 4)
        }
        mAdapter = LetterAdapter(this)
        letters.adapter = mAdapter
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return;
        menuItem.icon = if (isLinearLayout)
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_grid_on_24)
        else
            ContextCompat.getDrawable(this, R.drawable.ic_baseline_list_24)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.switch_layout)
        setIcon(layoutButton)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}