package com.example.btt02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgeCheckerScreen()
        }
    }
}

@Composable
fun AgeCheckerScreen() {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("THỰC HÀNH 01", fontSize = 22.sp)

        // Khung nhập có nền xám và bo góc
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .background(Color(0xFFE0E0E0), RoundedCornerShape(12.dp))
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Họ và tên") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Tuổi") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                val ageNum = age.toIntOrNull()
                result = when {
                    ageNum == null -> "Tuổi không hợp lệ"
                    ageNum > 65 -> "$name là Người già"
                    ageNum in 6..65 -> "$name là Người lớn"
                    ageNum in 2..5 -> "$name là Trẻ em"
                    else -> "$name là Em bé"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))
        ) {
            Text("Kiểm tra", color = Color.White)
        }

        if (result.isNotEmpty()) {
            Text(result, fontSize = 18.sp, modifier = Modifier.padding(top = 12.dp))
        }
    }
}
