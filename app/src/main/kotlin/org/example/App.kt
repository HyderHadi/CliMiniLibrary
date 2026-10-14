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
            3 - search
            4 - borrow
            5 - return
            5 - remove
            6 - exit
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
                    println("ID: ${it.id}||Name: ${it.name}||Is Borrowed: ${if (it.isBorrowed) "Yes" else "No"}")
                }
                println()
            }

            "search" -> {
                print("Search by book name: ")
                val searchName = readln()
                val tmpList = listOfBooks.filter { it.name.contains(searchName, ignoreCase = true) }
                if (tmpList.isEmpty()) {
                    println("No Matching Result\n")
                    continue
                }
                println("SEARCH RESULT")
                println()
                tmpList.forEach {
                    println("ID: ${it.id}||Name: ${it.name}||Is Borrowed: ${if (it.isBorrowed) "Yes" else "No"}")
                }
                println()
            }

            "borrow" -> {
                print("Type the ID of a book to borrow: ")
                val tmpId = readln().toLongOrNull()
                val tmpBookIndex = listOfBooks.indexOfFirst { it.id == tmpId }
                if (tmpBookIndex == -1) {
                    println("Book Doesn't Exist\n")
                    continue
                }
                if (listOfBooks[tmpBookIndex].isBorrowed == true) {
                    println("Book Is Already Borrowed\n")
                    continue
                }
                listOfBooks[tmpBookIndex] = listOfBooks[tmpBookIndex].copy(
                    isBorrowed = true
                )
                writeToFile(listOfBooks)
                println("Successful Borrow\n")
            }

            "return" -> {
                print("Type the ID of a borrowed book to return: ")
                val tmpId = readln().toLongOrNull()
                val tmpBookIndex = listOfBooks.indexOfFirst { it.id == tmpId }
                if (tmpBookIndex == -1) {
                    println("Book Doesn't Exist\n")
                    continue
                }
                if (listOfBooks[tmpBookIndex].isBorrowed == false) {
                    println("Book Is Already within the library\n")
                    continue
                }
                listOfBooks[tmpBookIndex] = listOfBooks[tmpBookIndex].copy(
                    isBorrowed = false
                )
                writeToFile(listOfBooks)
                println("Successful Return\n")
            }

            "remove" -> {
                print("Type ID of book to delete: ")
                val tmpId = readln().toLongOrNull()
                if (!listOfBooks.removeIf { it.id == tmpId }) {
                    println("Book Doesn't Exist")
                    continue
                }
                writeToFile(listOfBooks)
                println("Successful Deletion")
            }
        }
    }
}
