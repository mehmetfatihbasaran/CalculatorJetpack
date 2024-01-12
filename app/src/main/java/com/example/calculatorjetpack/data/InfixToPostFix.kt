package com.example.calculatorjetpack.data

class InfixToPostFix {

    private fun notNumeric(ch: Char): Boolean = when (ch) {
        '+', '-', '*', '/', '^', '(', ')' -> true
        else -> false
    }

    private fun operatorPrecedence(ch: Char): Int = when (ch) {
        '+' -> 1
        '-' -> 1
        '*' -> 2
        '/' -> 2
        '^' -> 3
        else -> -1
    }

    fun infixToPostFix(infix: String): String {
        val stack = mutableListOf<Char>()
        infix.forEach { ch ->
            when {
                ch.isDigit() -> stack.add(ch)
                notNumeric(ch) -> {
                    while (stack.isNotEmpty() && operatorPrecedence(stack.last()) >= operatorPrecedence(
                            ch
                        )
                    ) {
                        stack.removeLast()

                    }
                    stack.add(ch)
                }

                else -> {
                    while (stack.isNotEmpty() && operatorPrecedence(stack.last()) > 0) {
                        stack.removeLast()
                    }
                    stack.add(ch)
                }
            }
        }
        return stack.joinToString("")
    }

    fun postFixConversion(infix: String): String {
        var result = ""
        val stack = ArrayDeque<Char>()

        for (s in infix) {
            if (!notNumeric(s)) {
                result += s
            } else if (s == '(') {
                stack.push(s)
            } else if (s == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += " " + stack.pop()
                }
                stack.pop()
            }else {
                while (!stack.isEmpty() && operatorPrecedence(stack.peek()!!) >= operatorPrecedence(s)) {
                    result += " " + stack.pop() + " "
                }
                stack.push(s)
                result += " "
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid expression"
            }
            result += " " + stack.pop() + " "
        }

        return result.trim()
    }

}

fun <T> ArrayDeque<T>.push(element: T)  = addLast(element)

fun <T> ArrayDeque<T>.pop() = removeLastOrNull()

fun <T> ArrayDeque<T>.peek() = lastOrNull()










