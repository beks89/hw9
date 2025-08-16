package ru.netology

data class Chat(
    var chatId: Int? = null,
    var messages: MutableList<Message> = mutableListOf()
)

data class Message(
    var messageId: Int? = null,
    var text: String,
    var deleted: Boolean = false,
    var read: Boolean = false
)

class NotFoundException(message: String) : RuntimeException(message)
