package com.lex.flagsquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultPageActivity : AppCompatActivity() {

    private var mUserName: String? = null
    private var mCorrectAnswer: Int? = null
    private var mTotalQuestion: Int? = null

    private var tvName: TextView? = null
    private var tvScore: TextView? = null
    private var btnFinish: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        mUserName = intent?.getStringExtra(Constants.USER_NAME)
        mCorrectAnswer = intent?.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        mTotalQuestion = intent?.getIntExtra(Constants.TOTAL_QUESTION,0)

        tvName = findViewById(R.id.tvName)
        tvScore = findViewById(R.id.tvScore)
        btnFinish = findViewById(R.id.btnFinish)

        setDetails()

        btnFinish?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun setDetails(){
        tvName?.text = mUserName
        tvScore?.text = "Your Score is ${mCorrectAnswer.toString()} out of ${mTotalQuestion.toString()}"
    }
}