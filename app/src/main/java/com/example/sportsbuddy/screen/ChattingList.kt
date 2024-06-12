
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportsbuddy.Screen

@Composable
fun ChattingList(navController: NavHostController,
                 modifier: Modifier,
                 listChatroom: MutableList<List<String>>) {

//    LazyColumn(state = scrollState) {
    LazyColumn(reverseLayout = true,
        modifier = Modifier
        .background(color = Color(0xFFD7E7F5))
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(horizontal = 15.dp)) {
        items(listChatroom.size) {
            if(it==0)
                Spacer(modifier = Modifier.size(70.dp))
            Box(modifier = Modifier
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .clickable() {// 0,1,2,3,4,5,6,7,8,9 (size==10)
                    navController.navigate(
                        if(it > 6)
                            "chatScreen"
                        else
                            Screen.A.route

                    )
                }
            )
            {
                Row(modifier = Modifier.padding(all = 12.dp) ){
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null,modifier = modifier.size(50.dp))
                    Spacer(modifier = Modifier.size(10.dp))
                    Column {
                        Spacer(modifier = Modifier.size(2.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "${listChatroom[it][0]}")
                            Text(text = "${listChatroom[it][2]}")
                        }
                        Spacer(modifier = Modifier.size(3.dp))
                        Text(text = "${listChatroom[it][1]}")
                    }
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

