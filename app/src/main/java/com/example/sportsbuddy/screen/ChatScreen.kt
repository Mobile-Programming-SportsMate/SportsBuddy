//package com.example.sportsbuddy.screen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material.icons.filled.Notifications
//import androidx.compose.material.icons.filled.Send
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.dp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.compose.Dimension
//import com.pandaways.chatz.model.ChatUiModel
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ChatScreen(
//    model: ChatUiModel,
//    onSendChatClickListener: (String) -> Unit,
//    modifier: Modifier,
//    interlocutor: String //'대화상대'라는 뜻
//) {
//    ConstraintLayout(modifier = modifier.fillMaxSize()) {
//        val (messages, chatBox, topAppBar) = createRefs()
//
//        val listState = rememberLazyListState()
//        LaunchedEffect(model.messages.size) {
//            listState.animateScrollToItem(model.messages.size)
//        }
//        TopAppBar(modifier = Modifier
//            .fillMaxWidth()
//            .constrainAs(topAppBar) {
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//            }
//            ,title = { Text(text = interlocutor) }
//        , actions = {
//                IconButton(onClick = {  }) {
//                    Icon(
//                        imageVector = Icons.Filled.Notifications,
//                        contentDescription = "알림/알림 종"
//                    )
//                }
//                IconButton(onClick = {  }) {
//                    Icon(
//                        imageVector = Icons.Filled.MoreVert,
//                        contentDescription = "메뉴/점 3개"
//                    )
//                }
//            })
//        LazyColumn(
//            state = listState,
//            modifier = Modifier
//                .background(color = Color(0xFFD7E7F5))
//                .fillMaxWidth()
//                .constrainAs(messages) {
//                    top.linkTo(topAppBar.bottom)
//                    bottom.linkTo(chatBox.top)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                    height = Dimension.fillToConstraints
//                },
//            contentPadding = PaddingValues(16.dp)
//        ) {
//            items(model.messages) { item ->
//                ChatItem(item)
//            }
//        }
//        ChatBox(
//            onSendChatClickListener,
//            modifier = Modifier
//                .background(color = Color(0xFFD7E7F5))
//                .fillMaxWidth()
//                .constrainAs(chatBox) {
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                }
//        )
//    }
//}
//
//@Composable
//fun ChatItem(message: ChatUiModel.Message) {
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(4.dp)) {
//        Box(
//            modifier = Modifier
//                .align(if (message.isFromMe) Alignment.End else Alignment.Start)
//                .clip(
//                    RoundedCornerShape(
//                        topStart = 48f,
//                        topEnd = 48f,
//                        bottomStart = if (message.isFromMe) 48f else 0f,
//                        bottomEnd = if (message.isFromMe) 0f else 48f
//                    )
//                )
//                //.background(PurpleGrey80)
//                .background(Color.White)
//                .padding(16.dp)
//        ) {
//            Text(text = message.text)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ChatBox(
//    onSendChatClickListener: (String) -> Unit,
//    modifier: Modifier
//) {
//    var chatBoxValue by remember { mutableStateOf(TextFieldValue("")) }
//    Row(modifier = modifier.padding(16.dp)) {
//        TextField(
//            value = chatBoxValue,
//            onValueChange = { newText ->
//                chatBoxValue = newText
//            },
//            modifier = Modifier
//                .weight(1f)
//                .padding(4.dp),
//            shape = RoundedCornerShape(24.dp),
//            colors = TextFieldDefaults.textFieldColors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                disabledIndicatorColor = Color.Transparent
//            ),
//            placeholder = {
//                Text(text = "내용을 입력하세요.")
//            }
//        )
//        IconButton(
//            onClick = {
//                val msg = chatBoxValue.text
//                if (msg.isBlank()) return@IconButton
//                onSendChatClickListener(chatBoxValue.text)
//                chatBoxValue = TextFieldValue("")
//            },
//            modifier = Modifier
//                .clip(CircleShape)
//                //.background(color = PurpleGrey80)
//                .background(color = Color(0xFF98BDE0))
//                .align(Alignment.CenterVertically)
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Send,
//                contentDescription = "Send",
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(8.dp)
//            )
//        }
//    }
//}
//
