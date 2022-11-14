fun main() {
    val n = readln().toInt()
    val numbers = List(n) { readln().toInt() }
    val m = readln().toInt()

    if (m in numbers) {
        print("YES")
    } else print("NO")
}