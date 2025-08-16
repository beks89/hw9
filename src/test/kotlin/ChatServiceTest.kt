import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import ru.netology.ChatService
import ru.netology.Message
import ru.netology.NotFoundException
import kotlin.test.assertEquals

class ChatServiceTest {

    @Before
    fun clearBeforeTest() {
        ChatService.clear()
    }

    @Test
    fun addMessage() {
        ChatService.addMessage(1, Message(1, "Hi!"))
        ChatService.addMessage(1, Message(1, "Hello!"))

        val result = ChatService.getChats().size
        assertEquals(1, result)
    }

    @Test
    fun lastMessages() {
        val message1 = Message(1, "Hi!")
        ChatService.addMessage(1, message1)
        val expected = message1.text
        val result = ChatService.lastMessages()
        assertEquals(expected, result)
    }

    @Test(expected = NotFoundException::class)
    fun lastMessages_exception() {
        val message1 = Message(1, "Hi!", true,true)
        ChatService.addMessage(1, message1)
        ChatService.lastMessages()
    }

    @Test
    fun getMessages() {
        val message1 = Message(1, "Hi!", false, true)
        val message2 = Message(2, "Hello!", false, true)
        ChatService.addMessage(1, message1)
        ChatService.addMessage(1, message2)
        val expected = listOf(message2)
        val result = ChatService.getMessages(1, 1)
        assertEquals(expected, result)
    }

    @Test(expected = NotFoundException::class)
    fun getMessages_exception() {
        ChatService.getMessages(5,1)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.addMessage(1, Message(1, "Hi!"))
        val result = ChatService.getUnreadChatsCount()
        assertEquals(1, result)
    }

    @Test
    fun getChats() {
        ChatService.addMessage(1, Message(1, "Hi!"))
        val result = ChatService.getChats().size
        assertEquals(1, result)
    }

    @Test
    fun deleteMessage() {
        ChatService.addMessage(1, Message(1, "Hi!"))
        val result = ChatService.deleteMessage(1, 1)
        assertEquals(true, result)
    }

        @Test
        fun deleteChat() {
            ChatService.addMessage(1, Message(1, "Hi!"))
            val result = ChatService.deleteChat(1)
            assertEquals(true, result)
        }

        @Test(expected = NotFoundException::class)
        fun deleteChat_exception() {
            ChatService.deleteChat(5)
        }

        @Test
        fun editMessage() {
            ChatService.addMessage(1, Message(1, "Hi!"))
            val result = ChatService.editMessage(1, 1, "new text")
            assertEquals(true, result)
        }

        @Test(expected = NotFoundException::class)
        fun editMessage_exception() {
            ChatService.editMessage(1, 1, "new text")
        }
    }