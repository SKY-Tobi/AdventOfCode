package twentyTwentyThree.daySeven

enum class CardPartTwo (val value: Char, val strength: Int) {
    A('A', 13),
    K('K', 12),
    Q('Q', 11),
    J('J', 1),
    T('T', 10),
    NINE('9', 9),
    EIGHT('8', 8),
    SEVEN('7', 7),
    SIX('6', 6),
    FIVE('5', 5),
    FOUR('4', 4),
    THREE('3', 3),
    TWO('2', 2);

    companion object {
        infix fun fromPartTwo(value: Char): CardPartTwo? = CardPartTwo.values().firstOrNull { it.value == value }
    }
}