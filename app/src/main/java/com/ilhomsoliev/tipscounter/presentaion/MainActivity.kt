package com.ilhomsoliev.tipscounter.presentaion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.tipscounter.presentaion.homeScreen.HomeScreen
import com.ilhomsoliev.tipscounter.presentaion.theme.TipsCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            Scaffold(topBar = {
                TopAppBar(backgroundColor = Color(0xFF3F6699)) {
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "Tips Calculator",
                        fontSize = 26.sp,
                        color = Color.White
                    )
                }
            }) {
                TipsCounterTheme() {
                    HomeScreen(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}