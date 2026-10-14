package org.example


val listOfBooks = mutableListOf<Book>()

fun main() {

    var command: String? = null

    file.createNewFile()

    listOfBooks.addAll(readFromFile(file))
    while (command?.lowercase() != "exit") {
        println("\n<<<< Library Management >>>>")
        println(
            """
            1 - add
            2 - list
        """.trimIndent()
        )
        print("Type a command: ")

        command = readln()

        when (command) {
            "add" -> {
                print("Type name of the book: ")
                val name = readln()
                listOfBooks.add(
                    Book(
                        name = name
                    )
                )
                writeToFile(listOfBooks)
                println("Book Added Successfully\n")
            }

            "list" -> {
                println()
                listOfBooks.forEach {
                    println("ID: ${it.id}||Name: ${it.name}||Is Borrowed: ${it.isBorrowed}")
                }
                println()
            }
        }
    }
}
