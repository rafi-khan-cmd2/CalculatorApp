package com.example.calculatorapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapplication.ui.theme.CalculatorApplicationTheme
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorApplicationTheme {
                Interface()
            }
        }
    }
}

@Composable
fun Interface(modifier: Modifier = Modifier){
    var expression by remember{
        mutableStateOf("")
    }
    var result by remember{
        mutableStateOf("")
    }

    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column(modifier = modifier
            .weight(1f)
            .align(Alignment.End)) {
            Text(text = expression,
                color = Color.White,
                style = TextStyle(fontSize = 54.sp),
                modifier = modifier.padding(10.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = modifier
            .weight(4f)
            .align(Alignment.End)) {
            Text(text = result,
                color = Color.White,
                style = TextStyle(fontSize = 54.sp),
                modifier = modifier.padding(10.dp),
                fontWeight = FontWeight.Bold
            )
        }


        Row(){
            SecondButton(modifier = modifier.weight(2f),Info="C", onClick = {
                expression = ""
                result = ""
            })
            SecondButton(Info="Del", onClick = {
                expression = Delete(expression)
            })
            //SecondButton(Info="%", onClick = {
            //   expression = "$expression$it"
            //})

            SecondButton(Info="/", onClick = {
                expression = "$expression$it"
            })
        }
        Row(){
            FirstButton(Info="7", onClick = {
                expression = "$expression$it"
            })
            FirstButton(Info="8", onClick = {
                expression = "$expression$it"
            })
            FirstButton(Info="9", onClick = {
                expression = "$expression$it"
            })
            SecondButton(Info="*", onClick = {
                expression = "$expression$it"
            })
        }
        Row(){
            FirstButton(Info="4", onClick = {
                expression = "$expression$it"
            })
            FirstButton(Info="5", onClick = {
                expression = "$expression$it"
            })
            FirstButton(Info="6", onClick = {
                expression = "$expression$it"
            })
            SecondButton(Info="-", onClick = {
                expression = "$expression$it"
            })
        }
        Row(){
            FirstButton(Info="1", onClick = {
                expression = "$expression$it"
            })
            FirstButton(Info="2", onClick = {
                expression = "$expression$it"
            })
            FirstButton(Info="3", onClick = {
                expression = "$expression$it"
            })
            SecondButton(Info="+", onClick = {
                expression = "$expression$it"
            })
        }
        Row(){
            FirstButton(modifier = modifier.weight(2f),Info="0", onClick = {
                expression = "$expression$it"
            })
            SecondButton(Info=".", onClick = {
                expression = "$expression$it"
            })
            SecondButton(Info="=", onClick = {
                result = Solve(expression)
            })
        }
    }

}



@Composable
fun FirstButton(modifier: Modifier = Modifier, Info:String, onClick: (String) -> Unit = {}){
    Button(modifier = modifier
        .size(97.dp)
        .clip(CircleShape)
        .padding(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF80AB))
        ,onClick = {onClick(Info)}){
        Text(text = Info, style = TextStyle(fontSize = 26.sp, color = Color.Black), fontWeight = FontWeight.Bold
            ,maxLines = 1)
    }
}

@Composable
fun SecondButton(modifier: Modifier = Modifier, Info:String, onClick: (String) -> Unit = {}){
    Button(modifier = modifier
        .size(97.dp)
        .clip(CircleShape)
        .padding(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF82B1FF))
        ,onClick = {onClick(Info)}){
        Text(text = Info, style = TextStyle(fontSize = 26.sp, color = Color.Black), fontWeight = FontWeight.Bold,
            maxLines = 1)
    }
}

fun Delete(exp : String): String{
    if (exp.length <= 1) {
        return ""
    }
    else{
        return exp.substring(0,(exp.length - 1))
    }
}
fun Solve(expression: String):String{
    val answer = Expression(expression).calculate().toString()
    return answer
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorApplicationTheme {
        Interface()
    }
}