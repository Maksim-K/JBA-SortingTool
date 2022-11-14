package sorting

import java.io.File
import java.util.*

const val LONG = "long"
const val LINE = "line"
const val WORD = "word"
const val SORT = "sort"
const val SORT_NATURAL = "natural"
const val SORT_BY_COUNT = "byCount"

const val CONSOLE_INPUT_MODE = 0
const val FILE_INPUT_MODE = 1
const val CONSOLE_OUTPUT_MODE = 0
const val FILE_OUTPUT_MODE = 2

const val DEFAULT_MODE = WORD
val modes = listOf(LONG, LINE, WORD, SORT, SORT_NATURAL, SORT_BY_COUNT)

var programSortMode = SORT_NATURAL
var programDataType = WORD

const val ARG_DATATYPE = "-dataType"
const val ARG_SORT_INTEGERS = "-sortIntegers"
const val ARG_SORTING_TYPE = "-sortingType"
const val ARG_INPUT_FILE = "-inputFile"
const val ARG_OUTPUT_FILE = "-outputFile"

var programMode: String = ""
var ioMode = CONSOLE_INPUT_MODE + CONSOLE_OUTPUT_MODE
var inputFileName: String = ""
var outputFileName: String = ""

fun parseArgs(args: Array<String>): String {
    var mode = ""
    if (args.isNotEmpty()) {
        var i = 0
        while (i < args.size) {
            when (args[i]) {
                ARG_DATATYPE -> {
                    if (i != args.lastIndex) {
                        programDataType = args[++i]
                    } else {
                        println("No data type defined!")
                    }
                }

                ARG_SORT_INTEGERS -> {
                    mode = SORT
                    break
                }

                ARG_SORTING_TYPE -> {
                    if (i != args.lastIndex) {
                        programSortMode = args[++i]
                        mode = programSortMode
                    } else {
                        mode = SORT_NATURAL
                        println("No sorting type defined!")
                    }
                }

                ARG_INPUT_FILE -> {
                    if (i != args.lastIndex) {
                        inputFileName = args[++i]
                        ioMode += FILE_INPUT_MODE
                    }
                }

                ARG_OUTPUT_FILE -> {
                    if (i != args.lastIndex) {
                        outputFileName = args[++i]
                        ioMode += FILE_OUTPUT_MODE
                    }
                }

                else -> {
                    println("${args[i]} is not a valid parameter. It will be skipped.")

                }
            }
            i++
        }
    }
    if (mode !in modes) mode = DEFAULT_MODE
    return mode
}

fun sortNaturalModeRun(input: Scanner): String {

    var count = 0
    var sortedData = ""
    var result = ""

    when (programDataType) {
        LONG -> {
            val list = mutableListOf<Long>()

            while (input.hasNext()) {
                val inputValue = input.next()
                try {
                    list.add(inputValue.toLong())
                } catch (e: NumberFormatException) {
                    println("\"$inputValue\" is not a long. It will be skipped.")
                }
            }

            count = list.size
            sortedData = list.sorted().joinToString(" ")
            result = "numbers"
        }

        LINE -> {
            val list = mutableListOf<String>()
            while (input.hasNext()) {
                list.add(input.nextLine())
            }
            count = list.size
            sortedData = "\n" + list.sorted().joinToString("\n")
            result = "lines"
        }

        else -> {
            val list = mutableListOf<String>()
            while (input.hasNext()) {
                list.addAll(input.nextLine().split(" "))
            }
            count = list.size
            sortedData = list.sorted().joinToString(" ")
            result = "words"
        }
    }

    result = "Total $result: $count\n"
    result += "Sorted data: $sortedData"
    return result
}

fun sortByCountModeRun(input: Scanner): String {
    var count = 0
    var countsMap = mutableMapOf<String, Int>()
    var result = ""

    when (programDataType) {
        LONG -> {
            while (input.hasNext()) {
                val value = input.nextLong().toString()
                countsMap[value] = countsMap.getOrDefault(value, 0) + 1
                count++
            }
            result = "numbers"
        }

        LINE -> {
            while (input.hasNext()) {
                val value = input.nextLine()
                countsMap[value] = countsMap.getOrDefault(value, 0) + 1
                count++
            }
            result = "lines"
        }

        else -> {
            while (input.hasNext()) {
                val values = input.nextLine().split("\\s+".toRegex())
                for (value in values) {
                    countsMap[value] = countsMap.getOrDefault(value, 0) + 1
                    count++
                }
            }
            result = "words"
        }
    }

    result = "Total $result: $count\n"

    when (programDataType) {
        LONG -> countsMap = countsMap
            .toList()
            .sortedBy { (key, value) -> key.toInt() }
            .sortedBy { (key, value) -> value }
            .toMap().toMutableMap()

        LINE -> countsMap = countsMap
            .toList()
            .sortedBy { (key, value) -> key }
            .sortedBy { (key, value) -> value }
            .toMap().toMutableMap()

        else -> countsMap = countsMap
            .toList()
            .sortedBy { (key, value) -> key }
            .sortedBy { (key, value) -> value }
            .toMap().toMutableMap()
    }
    for (entry in countsMap) {
        result += "${entry.key}: ${entry.value} time(s), ${entry.value * 100 / count}%\n"
    }

    return result
}

fun longModeRun(input: Scanner): String {
    var count = 0
    var maxValue: Long = Long.MIN_VALUE
    var countMaxValues = 0

    while (input.hasNext()) {
        val value = input.nextLong()
        count++

        if (maxValue < value || countMaxValues == 0) {
            maxValue = value
            countMaxValues = 1
        } else if (value == maxValue) countMaxValues++
    }

    return "Total numbers: $count \n" +
            "The greatest number: $maxValue ($countMaxValues time(s)," +
            " ${(countMaxValues * 100 / count)}%)."

}

fun lineModeRun(input: Scanner): String {
    var count = 0
    var maxValue = ""
    var countMaxValues = 0

    while (input.hasNext()) {
        val value = input.nextLine()
        count++

        if (maxValue.length < value.length || countMaxValues == 0) {
            maxValue = value
            countMaxValues = 1
        } else if (value.length == maxValue.length) {
            if (value > maxValue) maxValue = value
            countMaxValues++
        }
    }

    return "Total numbers: $count \n" +
            "The longest line: \n$maxValue\n($countMaxValues time(s)," +
            " ${(countMaxValues * 100 / count)}%)."

}

fun wordModeRun(input: Scanner): String {
    var count = 0
    var maxValue = ""
    var countMaxValues = 0

    while (input.hasNext()) {
        val listOfWords = input.next().split(" ")
        for (value in listOfWords) {
            count++

            if (maxValue.length < value.length || countMaxValues == 0) {
                maxValue = value
                countMaxValues = 1
            } else if (value.length == maxValue.length) {
                if (value > maxValue) maxValue = value
                countMaxValues++
            }
        }

    }

    return "Total numbers: $count \n" +
            "The longest word: $maxValue ($countMaxValues time(s)," +
            " ${(countMaxValues * 100 / count)}%)."

}

fun sortModeRun(input: Scanner): String {
    var values: String = ""
    var count = 0

    if (input.hasNext()) {
        values = input.nextInt().toString()
        count++
    }
    while (input.hasNext()) {
        values += " ${input.nextInt()}"
        count++
    }

    val array: Array<Int> = values
        .split(" ")
        .map { it.toInt() }
        .toTypedArray()


    return "Total numbers: $count.\n" +
            "Sorted data: ${sortIntArray(array).joinToString(" ")}"
}

fun sortIntArray(array: Array<Int>): Array<Int> {
    return array.sorted().toTypedArray()
}

fun main(args: Array<String>) {
    programMode = parseArgs(args)

    val input = if (ioMode and FILE_INPUT_MODE == FILE_INPUT_MODE)
        Scanner(File(inputFileName)) else Scanner(System.`in`)

    val result: String =
        when (programMode) {
            // LONG -> longModeRun(input)
            // LINE -> lineModeRun(input)
            // SORT -> sortModeRun(input)
            SORT_NATURAL -> sortNaturalModeRun(input)
            SORT_BY_COUNT -> sortByCountModeRun(input)
            else -> sortNaturalModeRun(input) // wordModeRun(input)
        }

    if (ioMode and FILE_OUTPUT_MODE == FILE_OUTPUT_MODE) {
        File(outputFileName).writeText(result)
    } else print(result)

    input.close()

}
