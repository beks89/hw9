package ru.netology

object ChatService {
    private var lastChatId = 0
    private var lastMessageId = 0
    private var chats = mutableMapOf<Int, Chat>()

    fun addMessage(userId: Int, message: Message) {
        chats.getOrPut(userId) { Chat(chatId = ++lastChatId) }.messages += message.copy(messageId = ++lastMessageId)
    }

    fun deleteMessage(chatId: Int, messageId: Int): Boolean {
        chats[chatId]
            .let { it?.messages ?: throw NotFoundException("Нет чатов") }
            .find { it.messageId?.equals(messageId) == true }
            .also { it?.deleted = true }
        return true
    }

    fun deleteChat(chatId: Int): Boolean {
        chats.remove(chatId) ?: throw NotFoundException("Нет чатов")
        return true
    }

    fun lastMessages() = chats.values.asSequence()
        .map { it.messages.last() }
        .filter { !it.deleted }
        .joinToString(separator = "\n") { it.text }
        .ifEmpty { throw NotFoundException("Нет чатов") }


    fun getMessages(chatId: Int, count: Int): List<Message> {
        val chat = chats[chatId] ?: throw NotFoundException("Нет чатов")
        return chat.messages.takeLast(count).onEach { it.read = true }
    }

    fun getUnreadChatsCount() = chats.values
        .count { it -> it.messages.any { !it.read } }

    fun getChats(): Map<Int, Chat> {
        return chats
    }


    fun editMessage(chatId: Int, messageId: Int, newText: String): Boolean {
        chats[chatId]
            .let { it?.messages ?: throw NotFoundException("Нет чатов") }
            .find { it.messageId?.equals(messageId) == true }
            .also { it?.text = newText }
        return true
    }

    fun printChats() = println(chats)

    fun clear() {
        chats = mutableMapOf()
        lastChatId = 0
        lastMessageId = 0
    }
}
