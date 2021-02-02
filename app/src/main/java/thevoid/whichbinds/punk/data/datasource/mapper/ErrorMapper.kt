package thevoid.whichbinds.punk.data.datasource.mapper

import thevoid.whichbinds.punk.core.exceptions.MyFailure


fun Int.toNetworkError() = when (this) {
    401 -> MyFailure.Unauthorized
    else -> MyFailure.ServerError
}

fun Throwable.normalizeError() = when (this) {
    is MyFailure -> this
    else -> MyFailure.ServerError
}