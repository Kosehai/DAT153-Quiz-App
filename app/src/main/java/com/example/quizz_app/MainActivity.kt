package com.example.quizz_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.quizz_app.ui.theme.Quizz_appTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Quizz_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Quiz App!", style = TextStyle(fontSize = 20.sp))
                        ShowGallery()
                        ShowQuiz()
                    }
                }
            }
        }
    }
}


@Composable
fun ShowGallery(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, GalleryActivity::class.java)
            context.startActivity(intent)
        }
    ) {
        Text("Gallery")
    }

}

@Composable
fun ShowQuiz(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button (
        onClick = {
            val intent = Intent(context, QuizActivity::class.java)
            context.startActivity(intent)
        }
    ) {
        Text("Quiz")
    }
}