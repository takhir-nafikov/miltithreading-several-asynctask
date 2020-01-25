package io.github.psgroup.model

import android.os.AsyncTask
import java.util.concurrent.Executors

class CookModel {

    interface IPresenter {
        fun update(index: Int, progress: Int)
        fun reset(index: Int)
    }

    private val executorService = Executors.newCachedThreadPool()

    private var mPresenter: IPresenter? = null
    private var mTask: AsyncTask<*, *, *>? = null

    private val arrayOfTasks = arrayOf(mTask, mTask, mTask)

    fun start(index: Int) {
        if (arrayOfTasks[index]?.status == AsyncTask.Status.RUNNING) {
            arrayOfTasks[index]?.cancel(false)
            mPresenter?.reset(index)
        } else {
            arrayOfTasks[index] = OrderPizzaTask().executeOnExecutor(executorService, index)
        }
    }

    fun subscribe(presenter: IPresenter) {
        mPresenter = presenter
    }

    fun unsubscribe() {
        mPresenter = null
    }

    companion object {
        const val MIN_PROGRESS = 0
        const val MAX_PROGRESS = 100
    }

    inner class OrderPizzaTask : AsyncTask<Int, Int, Int>() {

        override fun doInBackground(vararg params: Int?): Int? {
            val buttonIndex = params.getOrNull(0) ?: -1
            var progress = MIN_PROGRESS
            Thread.sleep(1000)


            while (progress <= MAX_PROGRESS) {
                Thread.sleep(100)
                progress++

                if (isCancelled) return null

                publishProgress(buttonIndex, progress)
            }

            return buttonIndex
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            val buttonIndex = values.getOrNull(0) ?: -1
            val progress = values.getOrNull(1) ?: 0

            mPresenter?.update(buttonIndex, progress)
        }

        override fun onPostExecute(result: Int?) {
            if (result is Int) {
                mPresenter?.reset(result)
            }
        }
    }
}
