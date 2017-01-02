package io.github.ajoz.workshop.day2.solution

import io.github.ajoz.workshop.sequences.scan

/**
 * You arrive at Easter Bunny Headquarters under cover of darkness. However, you left in such a rush that you forgot to
 * use the bathroom! Fancy office buildings like this one usually have keypad locks on their bathrooms, so you search
 * the front desk for the code.
 *
 * "In order to improve security," the document you find says, "bathroom codes will no longer be written down. Instead,
 * please memorize and follow the procedure below to access the bathrooms."
 *
 * The document goes on to explain that each button to be pressed can be found by starting on the previous button and
 * moving to adjacent buttons on the keypad: U moves up, D moves down, L moves left, and R moves right. Each line of
 * instructions corresponds to one button, starting at the previous button (or, for the first line, the "5" button);
 * press whatever button you're on at the end of each line. If a move doesn't lead to a button, ignore it.
 *
 * You can't hold it much longer, so you decide to figure out the code as you walk to the bathroom. You picture a keypad
 * like this:
 *
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * Suppose your instructions are:
 *
 * ULL
 * RRDDD
 * LURDL
 * UUUUD
 *
 * - You start at "5" and move up (to "2"), left (to "1"), and left (you can't, and stay on "1"), so the first button is
 * 1.
 * - Starting from the previous button ("1"), you move right twice (to "3") and then down three times (stopping at "9"
 * after two moves and ignoring the third), ending up with 9.
 * - Continuing from "9", you move left, up, right, down, and left, ending with 8.
 * - Finally, you move up four times (stopping at "2"), then down once, ending with 5.
 *
 * So, in this example, the bathroom code is 1985.
 *
 * Your puzzle input is the instructions from the document you found at the front desk. What is the bathroom code?
 */


fun main(args: Array<String>) {
    val test = """
                |ULL
                |RRDDD
                |LURDL
                |UUUUD
               """.trimMargin()

    val test2 = """
|DLDRDDDLULDRRLUDDLDUURDRDUULDRDDRRLDLLUUDDLLRLRDRUURLUDURDDRURLUDDUULUURLLRRRRUDULUDLULLUURRLLRRURRUDUUURRLUUUDURDLLLDULDRLRDDDUDDUURLRRRURULLUDDUULDRRRDDLRLUDDRRDLRDURLRURUDDUULDDUUDDURRLUURRULRRLDLULLRLRUULDUDDLLLRDDULRUDURRDUUDUUDDUULULURDLUDRURDLUUDRDUURDDDRDRLDLDRURRLLRURURLLULLRRUULRRRRDLDULDDLRRRULRURRDURUDUUULDUUDRLDDLDUDDRULLUDUULRRRDRRDRDULDLURDDURLRUDLURLUDDDRLLURUUUUUUURUULDUUDDRLULRUDURRDLDUULLRLULLURDDDDDLRRDLRLLDDUDRRRDDURDLRRUDDUDLRRRDDURULRURRRLDRDUDLD
|LRRDUDUUUDRRURRDUUULULUDDLLDRRRUDDUULRRDRUDRLLRLRULRRDUUDRLDURUDLLLDRRDLRLUUDRUDRRRUDRRRULDRRLLRDDDLLRDDRULRLLRUDRLLLULDLDDRDRUUUUUULURLLRUDRDRLLULLRUUURRDRULULUDLDURRUUDURLLUDRDLDDULUDLRDDRLRLURULDRURRRRURRDDUDRULUUUDDDRULRULDLLURUUULRDDLRUURLRLDLUULLURDRDDDUDDDRLDRDLLDRDDDDURLUUULDDRURULUDDURDRDRLULDULURDUURDRLLUUUULRULUUDRLLDDRRURUURLDLLRRRDLRURDDLDLDRLRRDLDURULDDLULRRRUUDLRDUURDURLURDDLDLRURLLLDRDULDDRUDDULDDRRLDLRDRDLDUUDLUULRLUDUUDUUUULDURULRRUDULURLRLDRLULLLDUDLLLRUDURDDDURLDDLRLRRDLUDLDDDDLULDRLDUUULDRRDDLRUULDLULUUURUDDRLDDDULRUDRURUURUUURRULRURDURLLRLLUULUULURDRLLUDDLU
|LLDURDUDRLURUDRLRLUDDRRURDULULDDUDUULRRLRLRRDRDRDURRLRLURRLRUDULLUULLURUDDRLDDDRURLUUDLDURRDURDDLUULRDURRUUURLRRURRDRDRDURRRLULLDRUDLRUDURDRDDLLULLULRRUDULDDRDRRDLLLDLURLRDRDLUDDRLDDLDRULDURLLRLDRDLUDDDDLDUUDRLLRRRRLDDRRLRLURLLRLLUULLDUUDLRDRRRDRDLLDULLDRLDDUDRDDRURRDDLRDLRRUUDRRRRDURUULDRDDURLURRRRURRDRRULULURULUUUDRRRLDLLLDDRULRUDDURDRLDDRDLULLLRURUDRLRDDLDLRRRUURDURLDURRUUDDLRDRUUUURDLRLULRUUDRLDLULLULUURURDULUDUDRRRLLRLURLLDLRRURURRUDLUDDDDRDUDUDUUUULLDRDLLLLUUUUDRLRLUDURLLUDRUUDLLURUULDDDDULUUURLLDL
|DLULLRDLRRLLLDLRRURRDRURDRUUULDDRLURURRDLRRULUUDDRLRRLDULRRUUDUULDDDUDLLDLURDRLLULLUUULLDURDRRRDDLRDUDRRRLRLDRRLRLULDDUDURRRLDLRULDULDDUDDRULDLDRDRDDRUDRUDURRRRUUDUDRLDURLDLRRUURRDDUDLLDUDRRURRLRRRRRLDUDDRLLLURUDRRUDRLRDUDUUUUUDURULLDUUDLRUUULDUUURURLUUDULDURUDDDLRRRDDRRDLRULLLRDDRLRLUULDUUULLLLDLRURLRRDURRLDLLLDURDLLUDDDLLDDURDDULURDRRRDDDLDDURRULUUDDLULLURULUULDLDDLUDRURURULUDDULRDRLDRRRUUUURUULDRLRRURRLULULURLLDRLRLURULRDDDULRDDLUR
|RURRULLRRDLDUDDRRULUDLURLRRDDRDULLLUUDDDRDDRRULLLDRLRUULRRUDLDLLLRLLULDRLDDDLLDDULLDRLULUUUURRRLLDRLDLDLDDLUDULRDDLLRLLLULLUDDRDDUUUUDLDLRRDDRDLUDURRUURUURDULLLLLULRRLDRLRDLUURDUUDLDRURURLLDRRRLLLLRDLDURRLRRLLRUUDDUULLRLUDLRRRRRURUDDURULURRUULRDDULUUDUUDDRDDDDDUUUDDDRRLDDRRDDUUULDURLDULURDRDLLURDULRUDRUULUULLRRRRLRUUDDUDLDURURLRRRULRDRRUDDRDDRLRRRLRURRRUULULLLUULLLULLUDLRDLDURRURDLDLRDUULDRLLRRLDUDDUULULR
""".trimMargin()


    val seq = test2.splitToSequence(delimiters = "\n")
    var startingKey = 5
    for (item in seq) {
//        println(item.toList())
        startingKey = item.asSequence()
                .scan(startingKey) {key, instr -> key next instr}
                .last()
        print(startingKey)
    }
}

val Int.down: Int
    get() = when (this) {
        in 1..6 -> this + 3
        in 7..9 -> this
        else -> throw IllegalArgumentException("Down - value not in range 1 - 9: ${this}")
    }

val Int.up: Int
    get() = when (this) {
        in 1..3 -> this
        in 4..9 -> this - 3
        else -> throw IllegalArgumentException("Up - value not in range 1 - 9: ${this}")
    }

val Int.right: Int
    get() = when (this) {
        1, 2, 4, 5, 7, 8 -> this + 1
        3, 6, 9 -> this
        else -> throw IllegalArgumentException("Right - value not in range 1 - 9: ${this}")
    }

val Int.left: Int
    get() = when (this) {
        1, 4, 7 -> this
        2, 3, 5, 6, 8, 9 -> this - 1
        else -> throw IllegalArgumentException("Left - value not in range 1 - 9: ${this}")
    }

infix fun Int.next(instruction: Char) = when (instruction) {
    'U' -> this.up
    'D' -> this.down
    'L' -> this.left
    'R' -> this.right
    else -> throw IllegalArgumentException("Wrong instruction char: $instruction")
}