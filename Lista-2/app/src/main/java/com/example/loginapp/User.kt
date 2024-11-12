package com.example.loginapp

data class User(val username: String, val password: String)

object UserDatabase {

    val users = mutableListOf(
        User("user1", "password1"),
        User("user2", "password2"),
        User("user3", "password3"),
        User("user4", "password4"),
        User("test", "123")
    )

    fun login(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }

    fun register(username: String, password: String, password2: String): Boolean {
        if (users.any { it.username == username }) {
            return false
        }
        if (password!=password2) {
            return false
        }
        users.add(User(username, password))
        return true
    }
}
