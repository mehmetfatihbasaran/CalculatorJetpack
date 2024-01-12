package com.example.calculatorjetpack

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.calculatorjetpack.data.Model

class CalculatorViewModel : ViewModel() {

    var uiState by mutableStateOf(CalculatorState())

    fun onInfixChange( infix: String) {
        uiState.apply {
            uiState = copy(infixExpression = this.infixExpression + infix)
        }
    }

    fun clearInfixExpression() {
        uiState = uiState.copy(infixExpression = "")
        uiState = uiState.copy(result = "")
    }

    private fun onResultChange(result: String) {
        uiState = uiState.copy(result = result)
    }

    fun evaluate() {
        if (uiState.infixExpression.isNotBlank()) {
            onResultChange(
                Model().result(
                    uiState.infixExpression
                )
            )
        }
    }

}

data class CalculatorState(
    val infixExpression: String = "",
    val result: String = ""
)