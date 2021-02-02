package thevoid.whichbinds.punk.core.exceptions

sealed class MyFailure: Throwable() {
    object InvalidObject: MyFailure()
    object NotFound: MyFailure()
    object ServerError: MyFailure()
    object Unauthorized: MyFailure()
    object NetworkConnectionError: MyFailure()
}