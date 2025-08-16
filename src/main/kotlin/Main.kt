package ru.netology


fun main() {

    val chatService = ChatService
    chatService.addMessage(1,Message(1, "Hi!"))
    chatService.addMessage(2,Message(1, "Hello!"))
    chatService.addMessage(3,Message(4,"What's up?"))
    chatService.addMessage(2,Message(6,"How are you?"))
    chatService.addMessage(3,Message(4,"I'm going to..."))
    chatService.printChats()
    chatService.deleteMessage(3,5)
    chatService.printChats()
    chatService.deleteChat(1)
    chatService.printChats()
    println(ChatService.lastMessages())
    println(ChatService.getChats())
    println(ChatService.getUnreadChatsCount())
    println(ChatService.getMessages(2, 2))

}