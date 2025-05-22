package com.example.btth02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btth02.ui.theme.BTTH02Theme
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BTTH02Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EmailCheckScreen()
                }
            }
        }
    }
}
@Composable
fun EmailCheckScreen() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Thực hành 02", fontSize = 22.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                error = "" // Xóa lỗi khi người dùng đang nhập
            },
            label = { Text("Email") },
            isError = error.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (!isValidEmail(email.text)) {
                    error = "Email không đúng định dạng"
                } else {
                    error = "" // hoặc xử lý tiếp
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Kiểm tra", fontSize = 16.sp, color = Color.White)
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val pattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    return pattern.matcher(email).matches()
}
