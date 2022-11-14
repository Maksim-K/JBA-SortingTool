fun main() {
    val size = readln().toInt()
    val listElements: MutableList<String> = MutableList(size) { readln() }
    val (p, m) = readln().split(" ")

    val pmString = "$p $m"
    val mpString = "$m $p"
    val listElementsString = listElements.joinToString(" ")

    if (pmString in listElementsString || mpString in listElementsString) {
        print("NO")
    } else print("YES")
}
