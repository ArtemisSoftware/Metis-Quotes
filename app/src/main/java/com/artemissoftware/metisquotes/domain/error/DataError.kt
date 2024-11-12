package com.artemissoftware.metisquotes.domain.error

sealed interface DataError : Error {

    sealed class NetworkError : DataError {
        data class Error(val message: String) : NetworkError()
        data object SocketTimeout : NetworkError()
        data object Unknown : NetworkError()
        data object UnknownHost : NetworkError()
        data object Connect : NetworkError()
    }
}