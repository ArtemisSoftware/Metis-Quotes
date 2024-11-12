package com.artemissoftware.metisquotes.presentation.dailyquotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.metisquotes.domain.usecases.GetDailyQuoteUseCase
import com.artemissoftware.metisquotes.presentation.util.ErrorData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.artemissoftware.metisquotes.presentation.util.extensions.toUiText

@HiltViewModel
class DailyQuotesViewModel @Inject constructor(
    private val getDailyQuoteUseCase: GetDailyQuoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DailyQuotesState())
    val state: StateFlow<DailyQuotesState> = _state.asStateFlow()

    init {
        getDailyQuotes()
    }

    fun onTriggerEvent(event: DailyQuotesEvent) {
        when (event) {
            DailyQuotesEvent.GetQuotes -> getDailyQuotes()
        }
    }

    private fun reload(){ getDailyQuotes() }

    private fun getDailyQuotes() = with(_state) {
        update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {

            getDailyQuoteUseCase()
                .onSuccess { dailyQuotes ->
                    update {
                        it.copy(
                            quotes = dailyQuotes.quotes,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    update {
                        it.copy(
                            isLoading = false,
                            error = ErrorData(
                                message = error.toUiText(),
                                onClick = {
                                    reload()
                                }
                            ),
                        )
                    }
                }
        }
    }
}
