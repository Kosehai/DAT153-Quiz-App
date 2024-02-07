import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizz_app.ImageItem
import com.example.quizz_app.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class QuizViewModel(private val state: SavedStateHandle) : ViewModel() {
    val imageList = listOf(
        ImageItem("Space Cat", R.drawable.spacecat),
        ImageItem("3D Cat", R.drawable.dcat),
        ImageItem("Cool Cat", R.drawable.coolcat)
    ).shuffled()

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex.asStateFlow()

    private var _score = MutableStateFlow(state.get<Int>("quiz_score") ?: 0)
    val score: StateFlow<Int> = _score.asStateFlow()

    fun incrementScore() {
        _score.value += 1
        state.set("quiz_score", _score.value)
    }

    fun getAllImagesRandom(): List<ImageItem> {
        return imageList.shuffled()
    }

    fun moveToNextQuestion() {
        _currentIndex.value = (_currentIndex.value + 1) % imageList.size
    }

    fun resetQuiz() {
        _currentIndex.value = 0
        _score.value = 0
    }
}
