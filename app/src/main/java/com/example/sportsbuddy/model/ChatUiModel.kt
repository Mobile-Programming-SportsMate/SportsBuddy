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
                text = "여기 어차피 출력안되므로 무시. 그러나 코드 없으면 오류",
                author = Author.manager
            )
        }
    }

    data class Author(
        val id: String,
        val name: String
    ) {
        companion object {
            val manager = Author("2", "관리자")
            val bot = Author("1", "김민재")
            val me = Author(MY_ID, "박민규")
        }
    }

    companion object {
        const val MY_ID = "-1"
    }
}