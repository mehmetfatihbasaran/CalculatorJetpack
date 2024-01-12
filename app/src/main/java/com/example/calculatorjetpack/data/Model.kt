package com.example.calculatorjetpack.data

class Model {

    private fun replaceNegative(str: String): String {
        val array = StringBuffer(str)
        if (array[0] == '-') {
            array.setCharAt(0, 'n')
        }

        var i = 0
        while (i < array.length) {
            if (array[i] == '-') {
                if (
                    array[i - 1] == '+' ||
                    array[i - 1] == '-' ||
                    array[i - 1] == '/' ||
                    array[i - 1] == '*' ||
                    array[i - 1] == '('
                ) {
                    array.setCharAt(i, 'n')
                }
            }
            i++
        }

        return array.toString()
    }

    fun result(str: String): String {

        val stringNegative = replaceNegative(str)
        val postFix = InfixToPostFix().postFixConversion(stringNegative)

        if (postFix == "Invalid expression") {
            return "Invalid expression"
        }

        return try {
            val evaluation = ArithmeticEvaluation().evaluation2(postFix)
            evaluation.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return "Invalid expression"
        }
    }

}