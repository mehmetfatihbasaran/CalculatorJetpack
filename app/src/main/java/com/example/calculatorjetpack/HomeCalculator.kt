package com.example.calculatorjetpack

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun HomeCalculator(calculatorViewModel: CalculatorViewModel) {

    val uiState = calculatorViewModel.uiState

    val contentMargin = 16.dp

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.End)
            ) {
                Text(
                    text = uiState.infixExpression,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Black
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = uiState.result,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.End)
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
            Spacer(modifier = Modifier.size(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.size(16.dp))
                CharacterItem(
                    character = "(",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f, true)
                ) {
                    calculatorViewModel.onInfixChange("(")
                }
                Spacer(modifier = Modifier.size(16.dp))
                CharacterItem(
                    character = ")",
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f, true)
                ) {
                    calculatorViewModel.onInfixChange(")")
                }

            }
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                val numbers = listOf(
                    "7", "8", "9",
                    "4", "5", "6",
                    "1", "2", "3",
                    "0", ".", "C"
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.weight(1f)
                ) {
                    items(numbers) { number ->
                        CharacterItem(character = number, modifier = Modifier.padding(16.dp)) {
                            if (number != "C") {
                                calculatorViewModel.onInfixChange(number)
                            } else {
                                calculatorViewModel.clearInfixExpression()
                            }
                        }
                    }
                }
                ConstraintLayout(modifier = Modifier.weight(.8f)) {
                    val (addition, subtraction, multiplication, division, equal, power) = createRefs()

                    CharacterItem(
                        character = "-",
                        modifier = Modifier
                            .height(50.dp)
                            .constrainAs(subtraction) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(division.start)
                                bottom.linkTo(addition.top)
                            }
                            .aspectRatio(1f),
                        color = MaterialTheme.colors.secondary
                    ) {
                        calculatorViewModel.onInfixChange("-")
                    }
                    CharacterItem(
                        character = "/",
                        modifier = Modifier
                            .height(50.dp)
                            .constrainAs(division) {
                                top.linkTo(parent.top)
                                start.linkTo(subtraction.end, contentMargin)
                                end.linkTo(parent.end, contentMargin)
                            }
                            .aspectRatio(1f),
                        color = MaterialTheme.colors.secondary
                    ) {
                        calculatorViewModel.onInfixChange("/")
                    }

                    CharacterItem(
                        character = "*",
                        modifier = Modifier
                            .height(50.dp)
                            .constrainAs(multiplication) {
                                top.linkTo(division.bottom, contentMargin)
                                start.linkTo(addition.end)
                                end.linkTo(parent.end)
                                bottom.linkTo(power.top)
                            }
                            .aspectRatio(1f),
                        color = MaterialTheme.colors.secondary
                    ) {
                        calculatorViewModel.onInfixChange("*")
                    }
                    CharacterItem(
                        character = "^",
                        modifier = Modifier
                            .height(50.dp)
                            .constrainAs(power) {
                                top.linkTo(multiplication.bottom, contentMargin)
                                start.linkTo(addition.end, contentMargin)
                                end.linkTo(parent.end, contentMargin)
                                bottom.linkTo(equal.top)
                            }
                            .aspectRatio(1f),
                        color = MaterialTheme.colors.secondary
                    ) {
                        calculatorViewModel.onInfixChange("^")
                    }

                    CharacterItem(
                        character = "+",
                        modifier = Modifier
                            .width(50.dp)
                            .constrainAs(addition) {
                                top.linkTo(subtraction.bottom, contentMargin)
                                start.linkTo(parent.start)
                                end.linkTo(multiplication.start)
                                bottom.linkTo(equal.top, contentMargin)
                            }
                            .aspectRatio(1f / 2f),
                        color = MaterialTheme.colors.secondary
                    ) {
                        calculatorViewModel.onInfixChange("+")
                    }
                    CharacterItem(
                        character = "=",
                        modifier = Modifier
                            .height(50.dp)
                            .constrainAs(equal) {
                                top.linkTo(power.bottom, contentMargin)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end, contentMargin)
                                bottom.linkTo(parent.bottom)
                            }
                            .aspectRatio(2f / 1f),
                        color = MaterialTheme.colors.secondary
                    ) {
                        calculatorViewModel.evaluate()
                    }


                }
            }
        }
    }

}

@Composable
fun CharacterItem(
    character: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.surface,
    onClick: () -> Unit
) {
    Surface(
        shape = CircleShape,
        color = color,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick.invoke() },
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = character,
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.button,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

