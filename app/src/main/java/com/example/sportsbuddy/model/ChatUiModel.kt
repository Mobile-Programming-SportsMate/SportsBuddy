package com.pandaways.chatz.model

data class ChatUiModel(
    val messages: List<Message>,
    val addressee: Author,
) {
    data class Message(
        val text: String,
        val author: Author,
    ) {
        val isFromMe: Boolean
            get() = author.id == MY_ID

        companion object {
            val initConv = Message(
                text = "안녕하세요!\n헬스 파트너 구인글 보고 연락드렸습니다.",
                author = Author.bot
            )
        }
    }

    data class Author(
        val id: String,
        val name: String
    ) {
        companion object {
            val bot = Author("1", "김민재")
            val me = Author(MY_ID, "박민규")
        }
    }

    companion object {
        const val MY_ID = "-1"
    }
}