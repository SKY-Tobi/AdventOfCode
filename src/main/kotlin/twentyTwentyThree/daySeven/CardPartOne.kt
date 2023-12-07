package twentyTwentyThree.daySeven

enum class CardPartOne (val value: Char, val strength: Int) {
    A('A', 13),
    K('K', 12),
    Q('Q', 11),
    J('J', 10),
    T('T', 9),
    NINE('9', 8),
    EIGHT('8', 7),
    SEVEN('7', 6),
    SIX('6', 5),
    FIVE('5', 4),
    FOUR('4', 3),
    THREE('3', 2),
    TWO('2', 1);

    companion object {
        infix fun from(value: Char): CardPartOne? = CardPartOne.values().firstOrNull { it.value == value }
    }
}