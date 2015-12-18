package com.romansl.backgroundloadmanager

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var loadManager: LoadManager by Delegates.notNull()
    private var myTask: TaskFuture<String> by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        loadManager = LoadManager.create(savedInstanceState)

        myTask = loadManager.register(MyTask::class.java) {
            // complete
        }

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            myTask.run("Hello")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        loadManager.onStart()
    }

    override fun onStop() {
        super.onStop()
        loadManager.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        loadManager.onSave(outState)
    }

    class Fff : Task<String> {
        override fun run(args: String) {

        }
    }

    data class MyTask(val arg1: Int, val b: Fff) : Task<String> by b {
        override fun run(args: String) {
            b.run(args)
        }
    }
}
