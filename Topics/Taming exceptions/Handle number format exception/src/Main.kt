const val FOURDIGITS = 4
fun parseCardNumber(cardNumber: String): Long {
    val digits = cardNumber.split(" ")
    val exception = Exception("Incorrect card number")
    var digitsInLong = 0L
    if (digits.size != FOURDIGITS) throw exception
    for (i in 0..FOURDIGITS - 1) {
        if (digits[i].length != FOURDIGITS) throw exception
        digitsInLong = digitsInLong * 10000 + digits[i].toLong()
    }
    return digitsInLong
}
