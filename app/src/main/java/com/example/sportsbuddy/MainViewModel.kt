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
    //원래는 상대가 질문을 먼저 하는 것으로 해서 questions 였는데
    //우리가 먼저 채팅을 거는 것으로 하였으므로 questions와 의미는 맞지 않지만
    //그냥 그대로 뒀음. 뜻은 그냥 bot이 답변하는 리스트임.
    private val questions = mutableListOf(
        "넵, 안녕하세요!!", // Q0.안녕하세요! 헬스파트너 구인글 보고 연락드립니다!
        "아뇨! 저는 구의에 살고있어요!", //Q1.화양동 거주하시나요?
        "구의역 에이블짐 이용중입니다!", //Q2.헬스장은 어디 다니고 계신가요?
        "아 그러시군요! 안그래도 이번에 만기되면 옮기\n려고 했는데 잘 됐네요!", //Q3.저는 건입에 맥스토탈 다니고 있어요!
        "저는 현재 3분할 하고 있습니다!", //Q4.분할은 어떻게 진행하시나요?
        "네네! 가능합니다! 내일 뵙겠습니다!" //Q5.내일 오후 3시에 건대 정문에서 뵙는거 어떠신가요?
    )

    fun sendChat(msg: String) {

        val myChat = ChatUiModel.Message(msg, ChatUiModel.Author.me)
        viewModelScope.launch {
            _conversation.emit(_conversation.value + myChat)

            delay(1000)
            _conversation.emit(_conversation.value + getRandomQuestion())
//            _conversation.emit(_conversation.value +
//                    ChatUiModel.Message(text = "test", author = ChatUiModel.Author.bot))
        }
    }

    //처음에 랜덤으로 문장 출력하는 함수여서 Random이라는 단어가 함수에 내포돼있는데,
    //지금은 순서대로 출력하는 함수로 바꿨지만, 함수 이름 바꾸면 위에도 다 바꿔줘야해서
    //그냥 귀찮아서 안바꾼거입니다. random은 아무의미없어요.
    private fun getRandomQuestion(): ChatUiModel.Message {
        val question = if (questions.isEmpty()) {
            "알겠습니다!"
        } else {
            //questions.random()
            questions[0]
        }

        if (questions.isNotEmpty()) questions.remove(question)

        return ChatUiModel.Message(
            text = question,
            author = ChatUiModel.Author.bot
        )
    }
}