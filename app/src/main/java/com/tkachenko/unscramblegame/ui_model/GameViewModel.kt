package com.tkachenko.unscramblegame.ui_model

import androidx.lifecycle.ViewModel
import com.tkachenko.unscramblegame.data.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init{
        resetGame()
    }
    fun resetGame(){
        //
    }
}