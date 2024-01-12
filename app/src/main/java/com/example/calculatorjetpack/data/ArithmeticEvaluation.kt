package com.example.calculatorjetpack.data

import java.util.Stack
import kotlin.math.pow

class ArithmeticEvaluation {

    private fun notOperator(ch: Char): Boolean = when (ch) {
        '+', '-', '*', '/', '^', '(', ')' -> false
        else -> true
    }

    fun evaluation(str: String): Double {
        val stack = Stack<Double>()
        str.forEach { ch ->
            if (notOperator(ch)) {
                stack.push(ch.toString().toDouble())
            } else {
                val num2 = stack.pop()
                val num1 = stack.pop()
                when (ch) {
                    '+' -> stack.push(num1 + num2)
                    '-' -> stack.push(num1 - num2)
                    '*' -> stack.push(num1 * num2)
                    '/' -> stack.push(num1 / num2)
                    '^' -> stack.push(Math.pow(num1, num2))
                }
            }
        }
        return stack.pop()
    }

    fun evaluation2(str: String): Double {
        var dummyString = ""
        val stack = ArrayDeque<Double>()

        for (s in str) {
            if (notOperator(s) && s != ' ') {
                dummyString += s
            }
            else if (s == ' ' && dummyString != "") {
                stack.push(dummyString.replace('n', '-').toDouble())
                dummyString = ""
            }
            else if (!notOperator(s)) {
                val val1 = stack.pop()
                val val2 = stack.pop()
                when (s) {
                    '+' -> stack.push(val1!! + val2!!)
                    '-' -> stack.push(val2!! - val1!!)
                    '*' -> stack.push(val2!! * val1!!)
                    '/' -> stack.push(val2!! / val1!!)
                    '^' -> stack.push((val2!!).pow(val1!!))
                }

            }
        }
        return stack.pop()!!.toDouble()
    }

}