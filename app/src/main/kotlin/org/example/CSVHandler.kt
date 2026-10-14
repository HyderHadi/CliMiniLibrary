package org.example

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVParser
import java.io.File

val file = File("books.csv")

fun writeToFile(
    books: List<Book>
) {
    file.bufferedWriter().use { writer ->
        CSVPrinter(
            writer,
            CSVFormat.DEFAULT.builder()
                .setHeader("id", "name", "isBorrowed")
                .get()
        ).use { csvPrinter ->
            books.forEach {
                csvPrinter.printRecord(
                    it.id,
                    it.name,
                    it.isBorrowed
                )
            }
        }
    }
}


fun readFromFile(
    csvfile: File
): List<Book> {
    if (csvfile.length() == 0L) {
        return emptyList<Book>()
    }
    val tmpBooksList = mutableListOf<Book>()
    csvfile.bufferedReader().use { reader ->
        CSVParser(
            reader,
            CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .get()
        ).use() { parser ->
            tmpBooksList.addAll(
                parser.records.map {
                    Book(
                        id = it.get("id").toLong(),
                        name = it.get("name"),
                        isBorrowed = it.get("isBorrowed").toBoolean()
                    )
                }
            )
        }
    }
    Book.updateNextId(tmpBooksList.maxOfOrNull { it.id } ?: 0L)
    return tmpBooksList
}
