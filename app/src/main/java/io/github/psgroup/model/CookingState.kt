package io.github.psgroup.model

sealed class CookingState {
    object NotStarted : CookingState()
    class InProgress(val max: Int, val current: Int) : CookingState()
    class Completed(val error: CookingError?) : CookingState()
}
