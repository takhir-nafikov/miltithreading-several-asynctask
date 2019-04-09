package io.github.psgroup.model

class CookModel {

    interface IPresenter {
        fun update(cookingState: CookingState)
    }

    private var mPresenter: IPresenter? = null
    private var mState = CookingState.NotStarted

    fun start(pizza: String) {
    }

    fun stop() {
    }

    fun delete() {
    }

    fun subscribe(presenter: IPresenter) {
        mPresenter = presenter
        mPresenter?.update(mState)
    }

    fun unsubscribe() {
        mPresenter = null
    }

    companion object {
        private val AVAILABLE_PIZZA = arrayOf(
                "Margarita",
                "Venezia",
                "Salami"
        )
    }

}
