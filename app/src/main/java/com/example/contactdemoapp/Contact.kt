package com.example.contactdemoapp

data class Contact(var name: String, var number: String) {
    override fun toString(): String {
        return "$name - $number"
    }
}
