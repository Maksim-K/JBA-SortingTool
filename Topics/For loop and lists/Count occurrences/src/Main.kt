fun main() {
    val n = readln().toInt()
    val numbers = MutableList(n) { readln() }
    val m = readln()

    var count = 0
    while (numbers.remove(m)) {
        count++
    }
    print(count)
}