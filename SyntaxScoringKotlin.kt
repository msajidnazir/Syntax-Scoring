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
private var totalErrorScore = 0 //Represents the total error score of whole input text
private val openingBracketsMap = mapOf('(' to '(', '[' to '[', '{' to '{', '<' to '<')
private val validBracketChunksMap = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
private val invalidBracketScores = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)

/**Method which will calculate error score for input text*/
fun main() {

    val inputLines = input.split("\n") //CONVERT input text to a list of lines, with a single line on each index

    for (currentLine in inputLines) { //LOOP-A to iterate through each line one by one
        clearStack() //IF previous line was incomplete it'll leave some characters in stack, so trash them first
        for (inputCharacter in currentLine) { //LOOP-B to iterate through each character of the line we're currently examining
            when (isOpeningBracket(inputCharacter)) { //CHECK IF the character we took from line is an opening bracket or not
                true -> saveOnStack(inputCharacter) //YES, then save it on stack for later use
                else -> { //NO
                     if (isNotValidChunk(getLastSavedCharacter(), inputCharacter)) { //POP a character from stack and see if it forms a valid or invalid chunk with the input character
                        addErrorScoreOf(inputCharacter) //ADD the error score of invalid input character to the total score
                        clearStack() //Clean the STACK for validation of next line
                        break //BREAK the LOOP-B at first encounter of an error
                    }
                }
            }
        } //LOOP-B for validation of one single line ends here
    } //LOOP-A for validation of all lines ends here

    println("Total Error Score: $totalErrorScore")
}

/**Method which will if the character we read from input line is an opening bracket or not*/
private fun isOpeningBracket(inputCharacter: Char): Boolean {
    return openingBracketsMap.contains(inputCharacter)
}

/**Method which decides if the closing bracket is a valid bracket corresponding to the opening bracket or not*/
private fun isNotValidChunk(storedCharacter: Char, currentCharacter: Char): Boolean {
    return validBracketChunksMap.getValue(storedCharacter) != currentCharacter
}

/**Method to obtain error score of the invalid character*/
private fun getErrorScoreOf(inputCharacter: Char): Int {
    return invalidBracketScores.getValue(inputCharacter)
}

/**Method to save a character on top of Stack*/
private fun saveOnStack(inputCharacter: Char) {
    openingBracketsStack.push(inputCharacter)
}

/**Method to save a character on top of Stack*/
private fun getLastSavedCharacter(): Char {
    return openingBracketsStack.pop()
}

/**Method to modify error score of invalid character*/
private fun addErrorScoreOf(inputCharacter: Char) {
    totalErrorScore += getErrorScoreOf(inputCharacter)
}

/**Method to clean the stack for validation of next line*/
private fun clearStack() {
    openingBracketsStack.clear()
}