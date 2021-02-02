package thevoid.whichbinds.punk.core.exceptions

sealed class DataPreferencesError : Throwable() {
    object NotFoundData : DataPreferencesError()
}