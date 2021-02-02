package thevoid.whichbinds.punk.domain.models

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import thevoid.whichbinds.punk.core.exceptions.DataPreferencesError
import thevoid.whichbinds.punk.core.exceptions.MyFailure

sealed class DomainError {

    companion object {
        fun fromThrowable(e: Throwable): DomainError =
            when (e) {
                is MyFailure.NotFound -> NotFoundError
                is MyFailure.Unauthorized -> AuthenticationError
                is MyFailure.NetworkConnectionError -> NetworkConnectionError
                is DataPreferencesError.NotFoundData -> NotFoundDataError
                else -> UnknownServerError((Some(e)))
            }
    }

    object NetworkConnectionError : DomainError()
    object AuthenticationError : DomainError()
    object NotFoundDataError : DomainError()
    object NotFoundError : DomainError()
    data class UnknownServerError(val e: Option<Throwable> = None) : DomainError()
}