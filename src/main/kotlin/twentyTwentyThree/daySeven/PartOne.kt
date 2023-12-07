package twentyTwentyThree.daySeven

class PartOne {
    companion object {
        fun execute(lines: List<String>) {
            var hands = evaluateHands(lines)
            val cardStrengthComparator = object : Comparator<Hand> {
                override fun compare(hand1: Hand, hand2: Hand): Int {
                    if (hand1.type.strength == hand2.type.strength) {
                        val hand1Cards = hand1.cards.toCharArray()
                        val hand2Cards = hand2.cards.toCharArray()
                        repeat(hand1.cards.length) { index ->
                            val hand1Strength = (CardPartOne from hand1Cards[index])?.strength
                            val hand2Strength = (CardPartOne from hand2Cards[index])?.strength
                            if (hand1Strength != null && hand2Strength != null && hand1Strength != hand2Strength) {
                                return hand1Strength - hand2Strength
                            }
                        }
                        return 0
                    } else {
                        return hand1.type.strength - hand2.type.strength
                    }
                }
            }

            hands = hands.sortedWith(cardStrengthComparator)
            var totalWinnings = 0
            repeat(hands.size) { index ->
                totalWinnings += hands[index].bid * (index + 1)
            }
            println("Total Winnings: $totalWinnings")
        }

        private fun evaluateHands(lines: List<String>): List<Hand> {
            val hands = mutableListOf<Hand>()
            lines.forEach { line ->
                val input = line.split(" ")
                hands.add(Hand(input[0], input[1].toInt(), evaluateHandType(input[0])))
            }
            return hands
        }

        private fun evaluateHandType(cards: String): HandType {
            val card = mutableMapOf<Char, Int>()
            cards.forEach {
                val foundCard = card.getOrElse(it) { 0 }
                card[it] = foundCard + 1
            }
            if (card.size == 1) {
                return HandType.FIVE_OF_A_KIND
            }
            if (card.size == 2) {
                if (card.containsValue(4)) {
                    return HandType.FOUR_OF_A_KIND
                }
                if (card.containsValue(2) && card.containsValue(3)) {
                    return HandType.FULL_HOUSE
                }
            }
            if (card.containsValue(3)) {
                return HandType.THREE_OF_A_KIND
            }
            val amountOfPairs = card.filter { it.value == 2 }.size
            if (amountOfPairs == 2) {
                return HandType.TWO_PAIR
            }
            if (amountOfPairs == 1) {
                return HandType.ONE_PAIR
            }
            return HandType.HIGH_CARD
        }
    }
}
