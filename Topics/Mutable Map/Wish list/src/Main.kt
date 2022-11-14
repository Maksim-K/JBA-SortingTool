fun makeMyWishList(wishList: Map<String, Int>, limit: Int): MutableMap<String, Int> {
    val myWishList = emptyMap<String, Int>().toMutableMap()
    for (wish in wishList) {
        if (wish.value <= limit) myWishList.put(wish.key, wish.value)
    }
    return myWishList
}
