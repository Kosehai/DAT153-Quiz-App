package com.example.quizz_app

import QuizViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.quizz_app.ui.theme.Quizz_appTheme

class QuizActivity : ComponentActivity() {
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Quizz_appTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    QuizScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun QuizScreen(viewModel: QuizViewModel) {
    val score by viewModel.score.collectAsState()
    val currentQuestionIndex by viewModel.currentIndex.collectAsState()
    val currentImageItem = viewModel.imageList[currentQuestionIndex]

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        ShowScore(score)
        ImageCard(imageItem = ImageItem("???????", currentImageItem.imageResId))

        LazyColumn {
            items(viewModel.getAllImagesRandom()) { item ->
                Button(onClick = {
                    if (item.imageResId == currentImageItem.imageResId) {
                        Toast.makeText(context, "Correct!", Toast.LENGTH_LONG).show()
                        viewModel.incrementScore()
                    } else {
                        Toast.makeText(context, "Try again :(", Toast.LENGTH_LONG).show()
                    }
                    viewModel.moveToNextQuestion()
                }) {
                    Text(item.title)
                }
            }
        }
    }
}


@Composable
fun ShowScore(score: Int) {
    Text("Score: $score", style = TextStyle(fontSize = 20.sp))
}
