package com.example.calculator

import android.graphics.Color
import android.graphics.Color.WHITE
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.statusBarColor = Color.parseColor("#000000") // change status bar color

        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val navBarColor = Color.parseColor("#FFA500") // Replace with your desired color
            val decorView = window.decorView
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR // Light navigation bar (optional)
            window.navigationBarColor = navBarColor
        } */

    }
    var expression: String = ""
    var isEqualClicked: Boolean = false
    var isOperatorClicked: Boolean = false
    fun clickNumber(view: View) {
        var chooseBtn = view as Button
        var result = findViewById<TextView>(R.id.result)
        var btnValue = ""

        if (result.text.equals("Cannot divide by zero")) {
            result.textSize = 75F
            result.text = ""
            expression = ""
        }
        else if (isEqualClicked) {
            result.text = ""
            expression = ""
        }
        else if (result.text.toString() == "0")  {
            result.text = ""
        }


        when(chooseBtn.id) {
            R.id.btnZero -> {
                btnValue = "0"
            }
            R.id.btnOne -> {
                btnValue = "1"
            }
            R.id.btnTwo -> {
                btnValue = "2"
            }
            R.id.btnThree -> {
                btnValue = "3"
            }
            R.id.btnFour -> {
                btnValue = "4"
            }
            R.id.btnFive -> {
                btnValue = "5"
            }
            R.id.btnSix -> {
                btnValue = "6"
            }
            R.id.btnSeven -> {
                btnValue = "7"
            }
            R.id.btnEight -> {
                btnValue = "8"
            }
            R.id.btnNine -> {
                btnValue = "9"
            }
            /*R.id.btnDot -> {
                btnValue += "."
            }*/
        }
//  5
        if (expression.endsWith("/") && btnValue == "0") {
            result.text = "Cannot divide by zero"
            result.textSize = 30F
            expression = ""
        }
        else {
            result.textSize = 75F
            result.text = result.text.toString() + btnValue
            expression += btnValue
        }
        isOperatorClicked = false
        isEqualClicked = false
    }

    fun clickOperator(view: View) {
        var chooseBtn = view as Button
        var result = findViewById<TextView>(R.id.result)
        var btnValue = ""

        if (result.text.equals("Cannot divide by zero")) {
            result.textSize = 75F
            result.text = "0"
            expression = "0"
        }
        when(chooseBtn.id) {
            R.id.btnAdd -> {
                btnValue = "+"
            }
            R.id.btnSub -> {
                btnValue = "-"
            }
            R.id.btnMul -> {
                btnValue = "*"
            }
            R.id.btnDiv -> {
                btnValue = "/"
            }
        }
        if (isOperatorClicked) {
            var size = result.text.length
            result.text = result.text.subSequence(0, size - 1).toString()
            expression = expression.subSequence(0, expression.length - 1).toString()
        }
        result.text = result.text.toString() + btnValue
        expression += btnValue

        isOperatorClicked = true
        isEqualClicked = false
    }

    fun clickEqual(view: View) {
        var result = findViewById<TextView>(R.id.result)
        if (!result.text.equals("Cannot divide by zero")) {
//        result.text = expression
            if( expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*") || expression.endsWith("/") ){
                expression = expression.subSequence(0, expression.length - 1).toString()
            }

            var finalValue = ExpressionBuilder(expression).build().evaluate()
            if(finalValue % 1 == 0.0)
                result.text = finalValue.toInt().toString()

            else
                result.text = ExpressionBuilder(expression).build().evaluate().toString()

            expression = result.text.toString()

            isEqualClicked = true
            isOperatorClicked = false
        }
    }

    fun clickAC(view: View) {
        var result = findViewById<TextView>(R.id.result)
        result.text = "0"
        result.textSize = 75F
        expression = "0"
        isEqualClicked = false
        isOperatorClicked = false
    }
}