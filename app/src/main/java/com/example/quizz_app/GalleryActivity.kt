package com.example.quizz_app

import QuizViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.quizz_app.ui.theme.Quizz_appTheme

data class ImageItem(var title: String, val imageResId: Int)

class GalleryActivity : ComponentActivity() {
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Quizz_appTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GalleryScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun GalleryScreen(viewModel: QuizViewModel) {
    var isSorted by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { isSorted = !isSorted }, modifier = Modifier.fillMaxWidth()) {
            Text(if (isSorted) "Sort by reverse order" else "Sort by title")
        }

        Spacer(modifier = Modifier.height(8.dp))

        val imageItems = if (isSorted) viewModel.imageList.sortedBy { it.title } else viewModel.imageList.sortedBy { it.title }.reversed()
        ImageGallery(imageItems = imageItems)
    }
}

@Composable
fun ImageGallery(imageItems: List<ImageItem>) {
    LazyColumn {
        items(imageItems) {
            item -> ImageCard(imageItem = item)
        }
    }
}

@Composable
fun ImageCard(imageItem: ImageItem) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(painter = painterResource(id = imageItem.imageResId),
                contentDescription = imageItem.title,
                Modifier.fillMaxWidth().height(200.dp))
            Text(text = imageItem.title, Modifier.align(Alignment.CenterHorizontally))
        }
    }
}