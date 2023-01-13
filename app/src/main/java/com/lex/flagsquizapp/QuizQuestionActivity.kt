package com.lex.flagsquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var mUserName: String? = null
    private var mCorrectAnswer: Int = 0
    private var mTotalQuestionAnswered = 1
    private var set :Int? = null
    private lateinit var question : Question

    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null

    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var btnSubmit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(Constants.USER_NAME)

        findViews()

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestion()

        setQuestion()
    }

    private fun setQuestion() {
            set = Random.nextInt(0, mQuestionsList!!.size)
            defaultOptionView()
            question = mQuestionsList!![set!!]
        if (question.id != -1) {
            tvQuestion?.text = question.question
            ivImage?.setImageResource(question.image)
            progressBar?.progress = mTotalQuestionAnswered
            progressBar?.max = mQuestionsList!!.size
            tvProgress?.text = "${mTotalQuestionAnswered}/${progressBar?.max}"

            tvOptionOne?.text = question.optionOne
            tvOptionTwo?.text = question.optionTwo
            tvOptionThree?.text = question.optionThree
            tvOptionFour?.text = question.optionFour

            if (mTotalQuestionAnswered == mQuestionsList!!.size) {
                btnSubmit?.text = "FINISH"
            } else {
                btnSubmit?.text = "SUBMIT"
                question.id = -1
            }
        }else{setQuestion()}
    }

    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0, it)
        }

        tvOptionTwo?.let {
            options.add(1, it)
        }

        tvOptionThree?.let {
            options.add(2, it)
        }

        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )

    }

    private fun findViews() {
        tvQuestion = findViewById(R.id.tvQuestion)
        ivImage = findViewById(R.id.ivImage)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)

        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvOptionOne -> {
                tvOptionOne?.let {
                    selectedOptionView(it,1)
                }
            }

            R.id.tvOptionTwo -> {
                tvOptionTwo?.let {
                    selectedOptionView(it,2)
                }
            }

            R.id.tvOptionThree ->{
                tvOptionThree?.let {
                    selectedOptionView(it,3)
                }
            }

            R.id.tvOptionFour ->{
                tvOptionFour?.let {
                    selectedOptionView(it,4)
                }
            }

            R.id.btnSubmit ->{
                if (mSelectedOptionPosition == 0){
                    mTotalQuestionAnswered++
                    enableTvOptionsClick()

                    when{
                        mTotalQuestionAnswered <= mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            val intent = Intent(this, ResultPageActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.TOTAL_QUESTION, mQuestionsList!!.size)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswer)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    disableTvOptionsClick()

                    question = set?.let { mQuestionsList?.get(it) }!!
                    if (question?.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswer++
                    }
                        answerView(question!!.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mTotalQuestionAnswered == mQuestionsList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                    question.id = -1
                }
            }
        }
    }

    private fun disableTvOptionsClick() {
        tvOptionOne?.isClickable = false
        tvOptionTwo?.isClickable = false
        tvOptionThree?.isClickable = false
        tvOptionFour?.isClickable = false
    }

    private fun enableTvOptionsClick() {
        tvOptionOne?.isClickable = true
        tvOptionTwo?.isClickable = true
        tvOptionThree?.isClickable = true
        tvOptionFour?.isClickable = true
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1 -> {tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)}

            2 -> {tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)}

            3 -> {tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)}

            4 -> {tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)}
        }
    }

}