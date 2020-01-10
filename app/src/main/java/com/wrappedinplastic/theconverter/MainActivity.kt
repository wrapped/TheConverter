package com.wrappedinplastic.theconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var finalTemp : Float

        etCelsius.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (s.isNotEmpty() && etCelsius.isFocused && etCelsius.text.toString().trim().matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    var celsiusTemp = etCelsius.text.toString().toFloat()

                    finalTemp = (celsiusTemp * 9/5) + 32
                    finalTemp = String.format("%.2f", finalTemp).toFloat()
                    etFahrenheit.setText(finalTemp.toString())

                } else if(etFahrenheit.text.isNotEmpty() && etCelsius.text.isEmpty()){

                    etFahrenheit.setText("")

                } else {

                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        etFahrenheit.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (s.isNotEmpty() && etFahrenheit.isFocused && etFahrenheit.text.toString().trim().matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    var fahrenheitTemp = etFahrenheit.text.toString().toFloat()

                    finalTemp = (fahrenheitTemp - 32) * 5/9

                    finalTemp = String.format("%.2f", finalTemp).toFloat()

                    etCelsius.setText(finalTemp.toString())
                } else if(etFahrenheit.text.isEmpty() && etCelsius.text.isNotEmpty()){

                    etCelsius.setText("")

                } else {

                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

    }
/**
    fun convert (view: View) {

        var finalTemp : Int

        if (etCelsius.text.toString().isNotEmpty()){

            val celsiusTemp = etCelsius.text.toString().toInt()

            finalTemp = (celsiusTemp * 9/5) + 32

            etFahrenheit.setText(finalTemp.toString())

        } else if (etFahrenheit.text.toString().isNotEmpty()){

            val fahrenheitTemp = etFahrenheit.text.toString().toInt()

            finalTemp = (fahrenheitTemp - 32) * 5/9

            etCelsius.setText(finalTemp.toString())
        } else {

        }

    }
    **/
}
