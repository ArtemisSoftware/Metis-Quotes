package com.artemissoftware.metisquotes.presentation.util

import android.os.Parcelable
import com.artemissoftware.metisquotes.presentation.composables.text.UiText
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorData(
    val message: UiText,
    val onClick: (() -> Unit)? = null,
): Parcelable