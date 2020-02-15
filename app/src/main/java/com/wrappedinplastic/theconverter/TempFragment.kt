package com.wrappedinplastic.theconverter


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_temp.*
import java.text.DecimalFormat

class TempFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_temp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var finalTemp : Float
        val formatTemp = DecimalFormat("#.##")

        etCelsius.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
                if (s.isNotEmpty() && etCelsius.isFocused && etCelsius.text.toString().trim().matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    val celsiusTemp = etCelsius.text.toString().toFloat()

                    finalTemp = (celsiusTemp * 9/5) + 32
                    etFahrenheit.setText(formatTemp.format(finalTemp))

                } else if(etFahrenheit.text.toString() != "" && etCelsius.text.toString() == ""){

                    etFahrenheit.setText("")

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
                    val fahrenheitTemp = etFahrenheit.text.toString().toFloat()

                    finalTemp = (fahrenheitTemp - 32) * 5/9

                    etCelsius.setText(formatTemp.format(finalTemp))

                } else if(etFahrenheit.text.toString() == "" && etCelsius.text.toString() != ""){

                    etCelsius.setText("")

                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })
    }
}
