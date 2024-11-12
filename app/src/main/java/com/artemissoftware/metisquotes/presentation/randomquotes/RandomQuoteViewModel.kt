package com.artemissoftware.metisquotes.presentation.randomquotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.metisquotes.domain.usecases.GetRandomQuoteUseCase
import com.artemissoftware.metisquotes.presentation.util.ErrorData
import com.artemissoftware.metisquotes.presentation.util.extensions.toUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomQuoteViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RandomQuoteState())
    val state: StateFlow<RandomQuoteState> = _state.asStateFlow()

    init {
        getRandomQuote()
    }

    fun onTriggerEvent(event: RandomQuoteEvent) {
        when (event) {
            RandomQuoteEvent.GetNewQuote -> getRandomQuote()
        }
    }

    private fun reload(){ getRandomQuote() }

    private fun getRandomQuote() = with(_state) {
        update {
            it.copy(isLoading = true, error = null)
        }

        viewModelScope.launch {

            getRandomQuoteUseCase()
                .onSuccess { quote ->
                    update {
                        it.copy(quote = quote, isLoading = false)
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
