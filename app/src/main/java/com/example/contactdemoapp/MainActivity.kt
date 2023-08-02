package com.example.contactdemoapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editNumber: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnEdit: Button
    private lateinit var btnDelete: Button
    private lateinit var contactList: ListView
    private val contacts = mutableListOf<Contact>()
    private var selectedPosition: Int = ListView.INVALID_POSITION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editName = findViewById(R.id.editName)
        editNumber = findViewById(R.id.editNumber)
        btnAdd = findViewById(R.id.btnAdd)
        btnEdit = findViewById(R.id.btnEdit)
        btnDelete = findViewById(R.id.btnDelete)
        contactList = findViewById(R.id.contactList)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contacts)
        contactList.adapter = adapter

        contactList.setOnItemClickListener { parent, view, position, id ->
            selectedPosition = position
            val selectedContact = contacts[position]
            editName.setText(selectedContact.name)
            editNumber.setText(selectedContact.number)
        }

        btnAdd.setOnClickListener {
            val name = editName.text.toString()
            val number = editNumber.text.toString()
            addContact(name, number)
            adapter.notifyDataSetChanged()
        }

        btnEdit.setOnClickListener {
            if (selectedPosition != ListView.INVALID_POSITION) {
                val name = editName.text.toString()
                val number = editNumber.text.toString()
                editContact(selectedPosition, name, number)
                adapter.notifyDataSetChanged()
            }
        }

        btnDelete.setOnClickListener {
            if (selectedPosition != ListView.INVALID_POSITION) {
                deleteContact(selectedPosition)
                adapter.notifyDataSetChanged()
                selectedPosition = ListView.INVALID_POSITION
            }
        }
    }

    private fun addContact(name: String, number: String) {
        if (name.isNotEmpty() && number.isNotEmpty()) {
            val contact = Contact(name, number)
            contacts.add(contact)
            editName.text.clear()
            editNumber.text.clear()
        }
    }

    private fun editContact(position: Int, name: String, number: String) {
        if (position in 0 until contacts.size) {
            val contact = contacts[position]
            contact.name = name
            contact.number = number
            editName.text.clear()
            editNumber.text.clear()
            selectedPosition = ListView.INVALID_POSITION
        }
    }

    private fun deleteContact(position: Int) {
        if (position in 0 until contacts.size) {
            contacts.removeAt(position)
            editName.text.clear()
            editNumber.text.clear()
        }
    }
}
