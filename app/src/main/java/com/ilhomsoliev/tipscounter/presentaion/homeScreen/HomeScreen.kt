package com.ilhomsoliev.tipscounter.presentaion.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.tipscounter.presentaion.theme.answerSize
import com.ilhomsoliev.tipscounter.presentaion.theme.labelSize
import com.ilhomsoliev.tipscounter.presentaion.theme.textFieldSize
import com.ilhomsoliev.tipscounter.strToTotalAmount
import kotlin.math.roundToInt

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var billAmount by remember { mutableStateOf("0") }
    var peopleAmount by remember { mutableStateOf("1") }
    var tipsPercentage by remember { mutableStateOf(20f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE8E9EB))
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {

        NumberRow(
            text = "Bill Amount",
            value = billAmount,
            onMinusClick = {
                var k = billAmount.toInt()
                k--;
                if (k >= 0) billAmount = k.toString()
            },
            onPlusClick = {
                var k = billAmount.toInt()
                k++
                if (k >= 0) billAmount = k.toString()
            },
            onValueChange = {
                it.removePrefix("0")
                if (it == "") billAmount = "0"
                if (it == "" || it.length > 4) {
                } else billAmount = it

            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        NumberRow(
            text = "People",
            value = peopleAmount,
            onMinusClick = {
                var k = peopleAmount.toInt()
                k--;
                if (k >= 1) peopleAmount = k.toString()
            },
            onPlusClick = {
                var k = peopleAmount.toInt()
                k++
                if (k >= 0) peopleAmount = k.toString()
            },
            onValueChange = {
                val k = it.removePrefix("0")
                if (k == "") peopleAmount = "1"
                if (k == "0" || k == "" || k.length > 4) {
                } else peopleAmount = k
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        PercentageRow(
            text = "Tips %",
            value = tipsPercentage,
            onValueChange = {
                tipsPercentage = it
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        AnswerRow(
            text = "Total Amount",
            value = (tipsPercentage / 100f * billAmount.toFloat() + billAmount.toFloat()).toString()
        )
        Spacer(modifier = Modifier.height(12.dp))
        AnswerRow(
            text = "For each people",
            value = ((tipsPercentage / 100f * billAmount.toFloat() + billAmount.toFloat()) / peopleAmount.toFloat()).toString()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = "Round Total", fontSize = labelSize)
        Spacer(modifier = Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier.padding(end = 6.dp), onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFACADA8))
            ) {
                Text(modifier = Modifier.padding(6.dp), text = "Down", fontSize = labelSize,color = Color.White)
            }
            Button(
                modifier = Modifier.padding(start = 6.dp), onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF746D69))
            ) {
                Text(modifier = Modifier.padding(6.dp), text = " Up ", fontSize = labelSize,color = Color.White)
            }
        }

    }
}

@Composable
fun NumberRow(
    text: String,
    value: String,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), text = text, fontSize = answerSize
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconBox(icon = Icons.Default.Refresh) {
                onMinusClick()
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    modifier = Modifier.width(100.dp), value = value, onValueChange = onValueChange,
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.End,
                        fontSize = textFieldSize
                    )
                )
                IconBox(icon = Icons.Default.Add) {
                    onPlusClick()
                }
            }
        }
    }
}

@Composable
fun IconBox(
    icon: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Black)
            .clickable {
                onClick()
            }
    ) {
        Icon(
            modifier = Modifier.padding(10.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun AnswerRow(
    text: String,
    value: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier

                .fillMaxWidth()
                .weight(5f), text = text, fontSize = answerSize
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            text = "$${strToTotalAmount(value)}",
            fontSize = answerSize,
            color = Color(0xFF85bb65)
        )
    }
}

@Composable
fun PercentageRow(
    text: String,
    value: Float,
    onValueChange: (Float) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(horizontal = 4.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(text = text, fontSize = labelSize)

        Row() {
            Slider(
                modifier = Modifier.width(240.dp),
                value = value,
                onValueChange = onValueChange,
                valueRange = 0f..100f,

                )
        }
        Text(text = "${value.roundToInt()}%", fontSize = labelSize)
    }
}