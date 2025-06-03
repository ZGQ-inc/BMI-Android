package com.zgqinc.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.zgqinc.bmi.ui.theme.BMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMITheme {
                BMIScreen()
            }
        }
    }
}

@Composable
fun BMIScreen() {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculateBMI() {
        val heightVal = height.toFloatOrNull()
        val weightVal = weight.toFloatOrNull()

        if (height.isBlank() || weight.isBlank()) {
            result = "请输入有效数值"
            return
        }

        if (heightVal == null || weightVal == null || heightVal <= 0 || weightVal <= 0) {
            result = "数值不能小于等于0"
            return
        }

        val heightInMeters = heightVal / 100
        val bmi = weightVal / (heightInMeters * heightInMeters)
        val category = when {
            bmi < 18.5 -> "过低体重"
            bmi < 24.9 -> "正常体重"
            bmi < 29.9 -> "超重"
            else -> "肥胖"
        }

        result = "BMI：%.2f\n%s".format(bmi, category)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(WindowInsets.safeDrawing.asPaddingValues())
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "BMI 计算器",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("身高（cm）") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("体重（kg）") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { calculateBMI() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("计算 BMI")
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (result.isNotBlank()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        text = result,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "BMI 分类标准",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "过低体重：BMI < 18.5\n" +
                                "正常体重：18.5 ≤ BMI < 24.9\n" +
                                "超重：25 ≤ BMI < 29.9\n" +
                                "肥胖：BMI ≥ 30",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}