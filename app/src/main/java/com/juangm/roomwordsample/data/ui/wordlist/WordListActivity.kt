package com.juangm.roomwordsample.data.ui.wordlist

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.widget.Toast
import android.app.Activity
import com.juangm.roomwordsample.R
import com.juangm.roomwordsample.data.ui.models.Word
import com.juangm.roomwordsample.data.ui.newword.NewWordActivity

class WordListActivity : AppCompatActivity() {

    lateinit var mWordListViewModel: WordListViewModel

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this@WordListActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }

        val adapter = WordListAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        mWordListViewModel = ViewModelProviders.of(this).get(WordListViewModel::class.java)
        mWordListViewModel.getAllWords()?.observe(this, Observer {
            adapter.setWords(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val word =
                Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
            mWordListViewModel?.insert(word)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
