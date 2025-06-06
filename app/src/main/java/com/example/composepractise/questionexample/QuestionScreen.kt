package com.example.composepractise.questionexample

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composepractise.R
import com.example.composepractise.navigationdrawer.createAppBar
import com.example.composepractise.navigationdrawer.createMenuItems


var  questionViewModel: QuestionViewModel? = null

@Composable
fun mainViewQuestionScreen(drawerState: DrawerState, navController: NavController){
    var selectedOption = remember { mutableStateOf(0) }
    var questionCounter = remember { mutableStateOf(0) }
    questionViewModel = viewModel(QuestionViewModel::class.java)
    questionViewModel?.getQuestionsList()
        ?.let { showQuestionCounter(it,selectedOption,questionCounter,drawerState,navController) }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun showQuestionCounter(
    questionList: MutableList<QuestionDataModel>,
    selectedOption: MutableState<Int>,
    questionCounter: MutableState<Int>,
    drawerState: DrawerState,
    navController: NavController
){
    val context = LocalContext.current
    val showingQuestionCounter:Int = questionCounter.value
    Scaffold (modifier = Modifier.padding(10.dp), topBar = {createAppBar("Question",drawerState)}) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) { Text(
            buildAnnotatedString { withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black))
            {
                if (showingQuestionCounter < questionList.size) {
                    append(
                        stringResource(R.string.question_heading).plus(" ")
                            .plus(showingQuestionCounter + 1)
                    )

                withStyle(
                    style = SpanStyle(
                        fontStyle = FontStyle.Italic,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                )
                {
                    append("/${questionList.size}")
                }
            }
                else{
                    showQuestion("Questions are Done!")
                }

            }}
            , modifier = Modifier.fillMaxWidth().padding(0.dp,10.dp,0.dp,0.dp),textAlign = TextAlign.Center)

            Canvas(modifier = Modifier.fillMaxWidth()) {drawLine(color = Color.Black, start = Offset(0f,0f), end = Offset(size.width,0f), pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(10f,20f),10f)) }

if (questionCounter.value<questionList.size) {
    showQuestion(questionList.get(questionCounter.value).question)
    showRadioButton(questionList.get(questionCounter.value).optionsList) {
        selectedOption.value = it
        if (questionList.get(questionCounter.value).optionsList.get(selectedOption.value)==questionList.get(questionCounter.value).answer)
        {
Toast.makeText(context,"Answer is selected",Toast.LENGTH_SHORT).show()
            questionCounter.value = questionCounter.value.plus(1)
        }
        else{
            Toast.makeText(context,"Answer is Incorrect",Toast.LENGTH_SHORT).show()
        }
    }
}
        }
    }
}

@Preview
@Composable
fun showQuestion(question:String = "Your question is here")
{
Surface(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
    Text(text = question, fontWeight = FontWeight.Bold, fontSize = 15.sp)
}
}


@Composable
fun showRadioButton(list: List<String>, clickEvent:(Int)->Unit)
{
    Column {  list.forEachIndexed { index, data ->
        Row(modifier = Modifier.fillMaxWidth().height(50.dp),
            verticalAlignment = Alignment.CenterVertically)  {
            RadioButton(
                onClick = {
                          clickEvent(index)},
                selected = (8==90)
            )
            Text(text = data,
                modifier = Modifier.padding(start = 10.dp))
        } } }

}

@Preview
@Composable
fun showNextButton(/*clickEvent:(Int)->Unit*/)
{
Surface(modifier = Modifier.fillMaxWidth()) {
    Row (horizontalArrangement = Arrangement.Center){
    Button(
        onClick = {}, shape = RoundedCornerShape(10.dp),
    ) {
        Text(text = stringResource(R.string.next))
    }
}

}
}

