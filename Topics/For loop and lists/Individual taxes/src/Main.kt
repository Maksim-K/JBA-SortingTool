const val HUNDRED_PERCENT = 100
fun main() {
    val countCompanies = readln().toInt()
    val annualIncomeOfCompanies: MutableList<Int> = MutableList(countCompanies) { readln().toInt() }
    val taxesOfCompanies: MutableList<Int> = MutableList(countCompanies) { readln().toInt() }
    
    var companyMaxPaysIndex = 0
    var companyMaxPaysAnnual = 0.0
    for (index in 0 until countCompanies) {
        val currentCompanyPays = annualIncomeOfCompanies[index].toDouble() * 
            taxesOfCompanies[index].toDouble() / HUNDRED_PERCENT
        
        if (companyMaxPaysAnnual < currentCompanyPays) {
            companyMaxPaysIndex = index
            companyMaxPaysAnnual = currentCompanyPays
        }
    }
    print(companyMaxPaysIndex + 1)
}
