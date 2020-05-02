package com.wrappedinplastic.theconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_length.*
import java.text.DecimalFormat

class LengthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_length, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var finalLength: Double
        val formatOutput = DecimalFormat("#.##")

        etLengthA.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etLengthA.isFocused && etLengthA.text.toString().trim()
                        .matches("-?\\d+(\\.\\d+)?".toRegex())
                ) {
                    val lengthAInput = etLengthA.text.toString().toDouble()
                    val convertedValue = convertFrom(lengthAInput)

                    finalLength = convertTo(convertedValue)
                    if (finalLength > 0.001){
                        etLengthB.setText(formatOutput.format(finalLength))
                    }
                    else {
                        etLengthB.setText(finalLength.toString())
                    }

                } else if (etLengthB.text.toString() != "" && etLengthA.text.toString() == "") {

                    etLengthB.setText("")

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        etLengthB.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etLengthB.isFocused && etLengthB.text.toString().trim()
                        .matches("-?\\d+(\\.\\d+)?".toRegex())
                ) {
                    val lengthBInput = etLengthB.text.toString().toDouble()

                    val convertedValue = convertFrom(lengthBInput)

                    finalLength = convertTo(convertedValue)
                    if (finalLength > 0.001){
                        etLengthA.setText(formatOutput.format(finalLength))
                    }
                    else {
                        etLengthA.setText(finalLength.toString())
                    }

                } else if (etLengthA.text.toString() != "" && etLengthB.text.toString() == "") {

                    etLengthA.setText("")

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        spLengthA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etLengthA.setText("")
                tilLengthA.hint = spLengthA.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spLengthB.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etLengthB.setText("")
                tilLengthB.hint = spLengthB.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun convertFrom(fromIn: Double): Double {

        var inputLength = ""
        var outputLength: Double = fromIn

        when {
            etLengthA.isFocused -> inputLength = spLengthA.selectedItem.toString()
            etLengthB.isFocused -> inputLength = spLengthB.selectedItem.toString()
        }

        when (inputLength) {
            "Millimeter" -> outputLength = (fromIn)
            "Centimeter" -> outputLength = (fromIn * 10)
            "Decimeter" -> outputLength = (fromIn * 100)
            "Meter" -> outputLength = (fromIn * 1000)
            "Kilometer" -> outputLength = (fromIn * 1000000)
            "Inch" -> outputLength = (fromIn * 25.4)
            "Foot" -> outputLength = (fromIn * 304.8)
            "Yard" -> outputLength = (fromIn * 914.4)
            "Mile" -> outputLength = (fromIn * 1609344)
        }

        return outputLength

    }

    fun convertTo(toOut: Double): Double {

        var outputLength = ""
        var convertedValue: Double = toOut

        when {
            etLengthA.isFocused -> outputLength = spLengthB.selectedItem.toString()
            etLengthB.isFocused -> outputLength = spLengthA.selectedItem.toString()
        }

        when (outputLength) {
            "Millimeter" -> convertedValue = toOut
            "Centimeter" -> convertedValue = (toOut / 10)
            "Decimeter" -> convertedValue = (toOut / 100)
            "Meter" -> convertedValue = (toOut / 1000)
            "Kilometer" -> convertedValue = (toOut / 1000000)
            "Inch" -> convertedValue = (toOut / 25.4)
            "Foot" -> convertedValue = (toOut / 304.8)
            "Yard" -> convertedValue = (toOut / 914.4)
            "Mile" -> convertedValue = (toOut / 1609344)
        }

        return convertedValue
    }
}
