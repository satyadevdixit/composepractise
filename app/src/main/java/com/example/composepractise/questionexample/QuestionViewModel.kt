package com.example.composepractise.questionexample

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel

class QuestionViewModel :ViewModel(){

    @SuppressLint("SuspiciousIndentation")
    fun getQuestionsList():MutableList<QuestionDataModel>
    {
        val questionList = mutableListOf<QuestionDataModel>()
            val questionDataModel = QuestionDataModel("Whats is your name","dixit", mutableListOf("dixit","sahab","dev"))
            val questionDataModel2 = QuestionDataModel("Whats is your address","palwal", mutableListOf("palwal","haryana","india"))
            val questionDataModel3 = QuestionDataModel("Whats is your age","30", mutableListOf("30","32","35","40"))
            val questionDataModel4 = QuestionDataModel("Whats is your job","IT", mutableListOf("IT","ME"))
            val questionDataModel5 = QuestionDataModel("Whats is your salary","1L", mutableListOf("1L","2L","3L","4L","5L"))

              questionList.add(questionDataModel)
              questionList.add(questionDataModel2)
              questionList.add(questionDataModel3)
              questionList.add(questionDataModel4)
              questionList.add(questionDataModel5)

        return questionList
    }
}