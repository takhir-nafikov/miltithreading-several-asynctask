package io.github.psgroup.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.github.psgroup.R
import io.github.psgroup.application.PizzaMakerApplication
import io.github.psgroup.model.CookModel
import io.github.psgroup.model.CookingState
import kotlinx.android.synthetic.main.activity_cook.*

class CookActivity : AppCompatActivity(), CookModel.IPresenter {

    private val mModel by lazy { PizzaMakerApplication.cookModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cook)

        runOne.setOnClickListener { mModel.start(1) }
        runTwo.setOnClickListener { mModel.start(2) }
        runThree.setOnClickListener { mModel.start(3) }

    }

    override fun update(index: Int, progress: Int) = when (index) {
        1 -> seekBarOne.progress = progress
        2 -> seekBarTwo.progress = progress
        3 -> seekBarThree.progress = progress
        else -> {}
    }

    override fun reset(index: Int) = when (index) {
        1 -> seekBarOne.progress = 0
        2 -> seekBarTwo.progress = 0
        3 -> seekBarThree.progress = 0
        else -> {}
    }

    override fun onResume() {
        super.onResume()
        mModel.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mModel.unsubscribe()
    }

}