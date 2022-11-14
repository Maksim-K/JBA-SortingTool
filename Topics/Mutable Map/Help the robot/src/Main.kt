fun helpingTheRobot(purchases: Map<String, Int>, addition: Map<String, Int>): MutableMap<String, Int> {
    val newMap: MutableMap<String, Int> = purchases.toMutableMap()
    for (item in addition) {
        newMap[item.key] = purchases.getOrDefault(item.key, 0) + item.value
    }
    return newMap
}
