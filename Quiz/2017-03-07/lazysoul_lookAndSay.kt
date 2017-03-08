object LookAndSay {
    @JvmStatic
    fun main(args: Array<String>) {
        println(lookAndSay(10))
    }

    fun lookAndSay(value: Int): String {
        fun getValue(curr: Int, limit: Int, acc: String = "1"): String {
            return if (curr == limit) {
                acc
            } else {
                getValue(curr + 1, limit, acc.parse())
            }
        }
        return getValue(1, value)
    }

    fun String.parse(): String {
        return if (this.isEmpty()) {
            ""
        } else {
            val header = this.takeWhile { this[0] == it }
            "${header[0]}${header.length}${this.drop(header.length).parse()}"
        }
    }

}
