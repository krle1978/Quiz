package com.atvss.quizz20

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.atvss.quizz20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar
    private var correctAnswers = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var index = 0
        val questionList = arrayOf(
            Question(R.string.question01_txt, true),
            Question(R.string.question02_txt, true),
            Question(R.string.question03_txt, true),
            Question(R.string.question04_txt, true),
            Question(R.string.question05_txt, true),
            Question(R.string.question06_txt, false),
            Question(R.string.question07_txt, true),
            Question(R.string.question08_txt, false),
            Question(R.string.question09_txt, true),
            Question(R.string.question10_txt, true),
            Question(R.string.question11_txt, false),
            Question(R.string.question12_txt, false),
            Question(R.string.question13_txt, true))

        progressBar = findViewById(R.id.progress_bar)
        progressBar.max = questionList.size
        val PROGRESS_BAR_INCREMENT = Math.ceil(100.0 / questionList.size).toInt()
        binding.apply {
            tvQuestion.setText(questionList[index].question)
            btnTrue.setOnClickListener {
                if (questionList[index].correct){
                    correctAnswers++
                }
                updateProgressBar()
                index++
                //validatingQuestions(index,correctAnswers,questionList)
                if (index<12) {
                    tvQuestion.setText(questionList[index].question)
                    Log.d("ActivityMainTAG", "Question Nr: $index\ntxt: ${questionList[index]}")
                }else{
                    alertWindow()
                    /*btnTrue.isVisible = false
                    btnFalse.isVisible = false

                    val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                    alert.setTitle(getString(R.string.gameover_textbox_txt))
                    alert.setCancelable(false)
                    alert.setMessage("${getString(R.string.gameover_txt)} $correctAnswer !")
                    alert.setPositiveButton(getString(R.string.closeapp_button_txt), { dialog, witch-> finish()})
                    alert.show()*/
                }

                score.setText("$correctAnswers/13")
            }
            btnFalse.setOnClickListener {

                if (!questionList[index].correct){
                    correctAnswers++
                }
                updateProgressBar()
                index++
                if (index<12) {
                    tvQuestion.setText(questionList[index].question)
                    Log.d("ActivityMainTAG", "Question Nr: $index\ntxt: ${questionList[index]}")
                }else{
                    alertWindow()
                    /*btnTrue.isVisible = false
                    btnFalse.isVisible = false
                    //btnKill.isVisible = true

                    val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                    alert.setTitle(getString(R.string.gameover_textbox_txt))
                    alert.setCancelable(false)
                    alert.setMessage("${getString(R.string.gameover_txt)} $correctAnswers !")
                    alert.setPositiveButton(getString(R.string.closeapp_button_txt), { dialog, witch-> finish()})
                    alert.show()*/

                }
                score.setText("$correctAnswers/13")
            }

        }
    }

    private fun updateProgressBar() {
        progressBar.progress = correctAnswers
    }

    private fun alertWindow() {
        val bind = ActivityMainBinding.inflate(layoutInflater)
        bind.apply {
            btnTrue.isVisible = false
            btnFalse.isVisible = false
            val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
            alert.setTitle(getString(R.string.gameover_textbox_txt))
            alert.setCancelable(false)
            alert.setMessage("${getString(R.string.gameover_txt)} $correctAnswers !")
            alert.setPositiveButton(
                getString(R.string.closeapp_button_txt),
                { dialog, witch -> finish() })
            alert.show()
        }
    }

    private fun validatingQuestions(index: Int, correctAnswers: Int, questionList: Array<Question>) {
        val bind = ActivityMainBinding.inflate(layoutInflater)
        bind.apply {
            if (index<12) {
                tvQuestion.text = getString(questionList[index].question)
                Log.d("ActivityMainTAG", "Question Nr: $index\ntxt: ${questionList[index]}")
            }else{
                btnTrue.isVisible = false
                btnFalse.isVisible = false
                val alert: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                alert.setTitle(getString(R.string.gameover_textbox_txt))
                alert.setCancelable(false)
                alert.setMessage("${getString(R.string.gameover_txt)} $correctAnswers !")
                alert.setPositiveButton(getString(R.string.closeapp_button_txt), { dialog, witch-> finish()})
                alert.show()
            }
            score.setText("$correctAnswers/13")
        }
    }


    private fun showQuestionFromList(index: Int, questionList: ArrayList<Question>) {
        val bind = ActivityMainBinding.inflate(layoutInflater)
        Log.d("ATVSS123", "Index pre klika: ${index}")
        bind.apply {
            tvQuestion.text = getString(questionList[index].question)
            Log.d("ATVSS123", "QuestionFromList: ${getString(questionList[index].question)}")
        }
    }


}