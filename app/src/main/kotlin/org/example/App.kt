package org.example


val listOfBooks = mutableListOf<Book>()

fun main() {
    
    var command: String? = null
    
    while(command?.lowercase() != "exit") {
        println("\n<<<< Library Management >>>>")
        println("""
            1 - add
        """.trimIndent())
        print("Type a command: ")
        
        command = readln()
        
        when(command) {
            "add" -> {
                print("Type name of the book: ")
                val name = readln()
                listOfBooks.add(
                    Book(
                        name = name
                    )
                )
                println("Book Added Successfully\n")
            }
        }
    }
}
