package com.tkachenko.unscramblegame

// обратите внимание, тут должен быть ваш пакет
import androidx.compose.runtime.currentRecomposeScope
import com.tkachenko.unscramblegame.data.MAX_NO_OF_WORDS
import com.tkachenko.unscramblegame.data.SCORE_INCREASE
import com.tkachenko.unscramblegame.ui_model.GameViewModel
import org.junit.Assert.*
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset(){
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord  = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value
        assertEquals(SCORE_INCREASE, currentGameUiState.score)
        assertFalse(currentGameUiState.isGuessedWordWrong)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet(){
        val incorrectPlayerWord = "incorrect"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()
        val currentGameUiState = viewModel.uiState.value
        assertEquals(0, currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded(){
        val gameUiState = viewModel.uiState.value
        val unscrambleWord = getUnscrambledWord(gameUiState.currentScrambledWord)
        assertNotEquals(unscrambleWord, gameUiState.currentScrambledWord)
        assertTrue(gameUiState.currentWordCount == 1)
        assertTrue(gameUiState.score==0)
        assertFalse(gameUiState.isGameOver)
    }
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly(){
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPLayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        repeat(
            MAX_NO_OF_WORDS){
            expectedScore== SCORE_INCREASE
            viewModel.updateUserGuess(correctPLayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPLayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            assertEquals(expectedScore, currentGameUiState.score)
            assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
            assertTrue(currentGameUiState.isGameOver)

        }
    }
    private fun getUnscrambledWord(scrambledWord: String): String{
        return com.tkachenko.unscramblegame.data.allWords.firstOrNull(){word ->
            scrambledWord.toSet() == word.toSet()
        } ?: ""
    }
}