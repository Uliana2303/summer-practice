package com.example.epli.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QuestionType{
    @SerialName("multiple_choice")
    MultipleChoice,
    @SerialName("single_choice")
    SingleChoice,
    @SerialName("input")
    Input
}

@Serializable
data class QuestionRespondRemote(
    val id: Int?,
    val quizId: Int?,
    val type: QuestionType,
    val questionNumber: Int,
    val title: String
)
