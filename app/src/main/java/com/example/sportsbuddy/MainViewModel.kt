package com.example.sportsbuddy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandaways.chatz.model.ChatUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val conversation: StateFlow<List<ChatUiModel.Message>>
        get() = _conversation
    private val _conversation = MutableStateFlow(
        listOf(ChatUiModel.Message.initConv)
    )

    private val questions = mutableListOf(
        "저는 헬스 3개월차 헬린이입니다.",
        "저는 구의역 근처 에이블짐을 다닙니다.",
        "주중 저녁에 같이 운동하고 싶습니다.",
        "분할은 어떻게 진행하시나요?",
        "운동 구력이 얼마나 되시나요?"
    )

    fun sendChat(msg: String) {

        val myChat = ChatUiModel.Message(msg, ChatUiModel.Author.me)
        viewModelScope.launch {
            _conversation.emit(_conversation.value + myChat)

            delay(1000)
            _conversation.emit(_conversation.value + getRandomQuestion())
        }
    }

    private fun getRandomQuestion(): ChatUiModel.Message {
        val question = if (questions.isEmpty()) {
            "알겠습니다!"
        } else {
            questions.random()
        }

        if (questions.isNotEmpty()) questions.remove(question)

        return ChatUiModel.Message(
            text = question,
            author = ChatUiModel.Author.bot
        )
    }
}