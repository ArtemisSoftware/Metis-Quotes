package com.artemissoftware.metisquotes.data.remote

import com.artemissoftware.metisquotes.domain.Resource
import com.artemissoftware.metisquotes.domain.error.DataError
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException

internal object HandleNetwork {

    inline fun <T> safeNetworkCall(apiCall: () -> T): Resource<T> {
        return try {
            Resource.Success(data = apiCall())
        } catch (ex: Exception) {
            Resource.Failure(error = handleException(ex))
        }
    }

    fun handleException(ex: Exception): DataError {
        return when (ex) {
            is UnknownHostException -> {
                DataError.NetworkError.UnknownHost
            }

            is ConnectException -> {
                DataError.NetworkError.Connect
            }

            is SocketTimeoutException -> {
                DataError.NetworkError.SocketTimeout
            }
            is CancellationException -> {
                throw ex
            }
            else -> DataError.NetworkError.Unknown
        }
    }
}