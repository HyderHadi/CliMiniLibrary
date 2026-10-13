package org.example

data class Book(
    val id: Long = nextId++,
    val name: String,
    val isBorrowed: Boolean = false
) {
    companion object {
        private var nextId = 1L
        
        fun updateNextId(currentId: Long) {
            if(currentId >= nextId) {
                nextId = currentId + 1
            }
        }
    }
}