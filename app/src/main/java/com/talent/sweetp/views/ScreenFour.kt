package com.talent.sweetp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.talent.sweetp.viewmodel.SharedViewModel

@Composable
fun ScreenFour(viewModel: SharedViewModel) {
    val question = viewModel.triviaQuestions.value.getOrNull(viewModel.currentQuestionIndex.value)
    val selectedAnswer = viewModel.selectedAnswer.value
    val isAnswerCorrect = viewModel.isAnswerCorrect.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        question?.let {
            Text(
                text = it.question,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            it.incorrect_answers.plus(it.correct_answer).shuffled().forEach { answer ->
                Button(
                    onClick = {
                        viewModel.selectedAnswer.value = answer
                        viewModel.checkAnswer(answer)
                    },
                    enabled = selectedAnswer == null,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(text = answer)
                }
            }

            isAnswerCorrect?.let { correct ->
                Text(
                    text = if (correct) "Correct!" else "Incorrect!",
                    fontSize = 18.sp,
                    color = if (correct) Color.Green else Color.Red,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            Button(
                onClick = { viewModel.nextQuestion() },
                enabled = selectedAnswer != null,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Next Question")
            }
        }
    }
}
