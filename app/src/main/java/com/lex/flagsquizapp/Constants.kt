package com.lex.flagsquizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answer"

    fun getQuestion():ArrayList<Question>{
        val questionList = ArrayList<Question>()

        val que1 = Question(
            1, "What country does this flag belong to", R.drawable.ic_flag_of_argentina,
            "Argentina", "Morocco", "Netherlands", "Chile",1
        )

        questionList.add(que1)

        val que2 = Question(
            2, "What country does this flag belong to", R.drawable.ic_flag_of_australia,
            "Luxembourg", "Gambia", "Australia", "New Zealand",3
        )

        questionList.add(que2)

        val que3 = Question(
            3, "What country does this flag belong to", R.drawable.ic_flag_of_belgium,
            "Qatar", "Mexico", "Netherlands", "Belgium",4
        )

        questionList.add(que3)

        val que4 = Question(
            4, "What country does this flag belong to", R.drawable.ic_flag_of_brazil,
            "Brazil", "Madagascar", "Finland", "Uruguay",1
        )

        questionList.add(que4)

        val que5 = Question(
            5, "What country does this flag belong to", R.drawable.ic_flag_of_denmark,
            "Croatia", "Denmark", "Saudi Arabia", "England",2
        )

        questionList.add(que5)

        val que6 = Question(
            6, "What country does this flag belong to", R.drawable.ic_flag_of_fiji,
            "Latvia", "Estonia", "Fiji", "Bulgaria",3
        )

        questionList.add(que6)

        val que7 = Question(
            7, "What country does this flag belong to", R.drawable.ic_flag_of_germany,
            "Germany", "Lithuania", "Sweden", "Slovakia",1
        )

        questionList.add(que7)

        val que8 = Question(
            8, "What country does this flag belong to", R.drawable.ic_flag_of_india,
            "Croatia", "India", "Hungary", "Romania",2
        )

        questionList.add(que8)

        val que9 = Question(
            9, "What country does this flag belong to", R.drawable.ic_flag_of_kuwait,
            "Luxembourg", "Slovakia", "Slovenia", "Kuwait",4
        )

        questionList.add(que9)

        val que10 = Question(
            10, "What country does this flag belong to", R.drawable.ic_flag_of_new_zealand,
            "New Zealand", "Sweden", "Netherlands", "Lithuania",1
        )

        questionList.add(que10)

        return questionList
    }

}