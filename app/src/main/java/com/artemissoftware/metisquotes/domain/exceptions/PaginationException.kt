package com.artemissoftware.metisquotes.domain.exceptions

import com.artemissoftware.metisquotes.domain.error.Error


class PaginationException(
    val error: Error,
) : RuntimeException()