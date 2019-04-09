package io.github.psgroup.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.psgroup.R
import io.github.psgroup.application.PizzaMakerApplication
import io.github.psgroup.model.CookModel
import io.github.psgroup.model.CookingState

class CookActivity : AppCompatActivity(), CookModel.IPresenter {

    private val mModel by lazy { PizzaMakerApplication.cookModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cook)

        // TODO: Реализуйте вызовы модели при нажатии на кнопки
    }

    // TODO: Реализуйте состояния активити
    override fun update(cookingState: CookingState) = when (cookingState) {
        CookingState.NotStarted -> Unit
        is CookingState.InProgress -> Unit
        is CookingState.Completed -> Unit
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