package polestar.codingtest.syntaxscoring

import java.util.Stack

//Problem: https://adventofcode.com/2021/day/10
/*
Solution Algorithm:

A -> LOOP @ each line of the input
    B -> LOOP @ each character of the current input line
        IF the most recently read character is from either of these character ( or [ or { or <
            Store the character in a stack and CONTINUE to LOOP-B
        ELSE
            Pop the top character from the stack
                IF the most recently read character is not a valid closing bracket for the character we popped
                    Add this closing bracket's error point to the total score variable and break the LOOP-B
                ELSE
                    CONTINUE to LOOP-B
    B -> LOOP ENDS
A -> LOOP ENDS

Show the Total Error Score now
*/

private const val input = "[({(<(())[]>[[{[]{<()<>>\n" + "[(()[<>])]({[<{<<[]>>(\n" + "{([(<{}[<>[]}>{[]{[(<()>\n" + "(((({<>}<{<{<>}{[]{[]{}\n" + "[[<[([]))<([[{}[[()]]]\n" + "[{[{({}]{}}([{[{{{}}([]\n" + "{<[[]]>}<{[{[{[]{()[[[]\n" + "[<(<(<(<{}))><([]([]()\n" + "<{([([[(<>()){}]>(<<{{\n" + "<{([{{}}[<[[[<>{}]]]>[]]"
val openingBracketsStack = Stack<Char>() //STACK to save opening brackets read from line
private var totalErrorScore = 0

/**Method which will calculate error score for input text*/
fun main() {

    val inputLines = input.split("\n") //CONVERT input text to a list of lines, with a single line on each index

    for (currentLine in inputLines) { //LOOP to iterate through each line one by one
        for (inputCharacter in currentLine) { //LOOP to iterate through each character of the line we're currently examining
            when (isOpeningBracket(inputCharacter)) { //CHECK IF the character we took from line is an opening bracket or not
                true -> openingBracketsStack.push(inputCharacter) //YES, then save it on stack for later use
                else -> { //NO
                    //Get the last saved character from stack
                    //IF the character we popped and the inputCharacter don't form a valid chunk
                    //Then modify the total error score accordingly and break the loop for validation of current line
                    if (!isValidChunk(openingBracketsStack.pop(), inputCharacter)) { //POP a character from stack and see if it forms a valid or invalid chunk with the input character
                        totalErrorScore += getErrorScoreOf(inputCharacter)
                        break //BREAK the LOOP at first error
                    }
                }
            }
        } //LOOP for validation of one single line ends here
    } //LOOP for validation of all lines ends here
}
/**Method which will if the character we read from input line is an opening bracket or not*/
private fun isOpeningBracket(inputCharacter: Char): Boolean {
    TODO()
}

/**Method which decides if the closing bracket is a valid bracket corresponding to the opening bracket or not*/
private fun isValidChunk(storedCharacter: Char, currentCharacter: Char): Boolean {
    TODO()
}

/**Method which will if the character we read from input line is an opening bracket or not*/
private fun getErrorScoreOf(inputCharacter: Char): Int {
    TODO()
}