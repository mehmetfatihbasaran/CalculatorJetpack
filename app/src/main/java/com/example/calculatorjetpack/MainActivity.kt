package com.example.calculatorjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.calculatorjetpack.ui.theme.CalculatorJetpackTheme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<CalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val infix = arrayListOf(
                "(2 + 3) * 4",
                "2 + 10 *4 /5 - (9 - 3)",
                "(5 - 2) / (1 + 3)",
                "6 * (8 - 2) + 4 / 2",
                "(10 + 5) / (2 - 1) * 3",
                "7 - (4 + 2) * 5"
            )
            CalculatorJetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {

                HomeCalculator(calculatorViewModel = viewModel)

                }


            }
        }
    }
}

