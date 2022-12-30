package polestar.codingtest.syntaxscoring

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

/**Method which will calculate error score for input text*/
fun main() {
    TODO()
}