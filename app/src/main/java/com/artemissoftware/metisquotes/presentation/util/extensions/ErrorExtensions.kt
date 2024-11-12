package com.artemissoftware.metisquotes.presentation.util.extensions

import com.artemissoftware.metisquotes.R
import com.artemissoftware.metisquotes.domain.error.DataError
import com.artemissoftware.metisquotes.presentation.composables.text.UiText
import com.artemissoftware.metisquotes.domain.error.Error


fun Error.toUiText(): UiText {
    return when (this) {
        is DataError.NetworkError -> {
            this.asUiText()
        }
        else -> UiText.StringResource(R.string.error_not_mapped)
    }
}

private fun DataError.NetworkError.asUiText(): UiText {
    return when (this) {
        DataError.NetworkError.Connect -> UiText.StringResource(
            R.string.connection_error,
        )
        is DataError.NetworkError.Error -> UiText.DynamicString(this.message)
        DataError.NetworkError.SocketTimeout -> UiText.StringResource(
            R.string.connection_socket_time_out,
        )
        DataError.NetworkError.Unknown -> UiText.StringResource(
            R.string.unknown_error,
        )
        DataError.NetworkError.UnknownHost -> UiText.StringResource(
            R.string.unknown_host_error,
        )
    }
}