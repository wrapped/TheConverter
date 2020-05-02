package com.wrappedinplastic.theconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_weight.*
import java.text.DecimalFormat

class WeightFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var finalWeight : Double
        val formatOutput = DecimalFormat("#.##")

        etWeightA.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etWeightA.isFocused && etWeightA.text.toString().trim()
                        .matches("-?\\d+(\\.\\d+)?".toRegex())
                ) {
                    val lengthAInput = etWeightA.text.toString().toDouble()

                    val convertedValue = convertFrom(lengthAInput)

                    finalWeight = convertTo(convertedValue)

                    if (finalWeight > 0.001){
                        etWeightB.setText(formatOutput.format(finalWeight))
                    }
                    else {
                        etWeightB.setText(finalWeight.toString())
                    }

                } else if (etWeightB.text.toString() != "" && etWeightA.text.toString() == "") {

                    etWeightB.setText("")
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        etWeightB.addTextChangedListener(object : TextWatcher{
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etWeightB.isFocused && etWeightB.text.toString().trim()
                        .matches("-?\\d+(\\.\\d+)?".toRegex())
                ) {
                    val lengthBInput = etWeightB.text.toString().toDouble()

                    val convertedValue = convertFrom(lengthBInput)

                    finalWeight = convertTo(convertedValue)
                    if (finalWeight > 0.01){
                        etWeightA.setText(formatOutput.format(finalWeight))
                    }
                    else {
                        etWeightA.setText(finalWeight.toString())
                    }

                } else if (etWeightA.text.toString() != "" && etWeightB.text.toString() == "") {

                    etWeightA.setText("")
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        spWeightA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etWeightA.setText("")
                tilWeightA.hint = spWeightA.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spWeightB.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etWeightB.setText("")
                tilWeightB.hint = spWeightB.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun convertFrom(fromIn: Double) : Double {

        var inputWeight = ""
        var outputWeight : Double = fromIn

        when {
            etWeightA.isFocused -> inputWeight = spWeightA.selectedItem.toString()
            etWeightB.isFocused -> inputWeight = spWeightB.selectedItem.toString()
        }

        when (inputWeight) {
            "Milligram" -> outputWeight = (fromIn)
            "Gram" -> outputWeight = (fromIn * 1000)
            "Kilogram" -> outputWeight = (fromIn * 1000000)
            "Metric Ton" -> outputWeight = (fromIn * 1000000000)
            "Ounce" -> outputWeight = (fromIn * 28349.5)
            "Pound" -> outputWeight = (fromIn * 453592)
            "US Ton" -> outputWeight = (fromIn * 907184740)
        }

        return outputWeight

    }

    fun convertTo(toOut: Double) : Double {

        var outputWeight = ""
        var convertedValue : Double = toOut

        when {
            etWeightA.isFocused -> outputWeight = spWeightB.selectedItem.toString()
            etWeightB.isFocused -> outputWeight = spWeightA.selectedItem.toString()
        }

        when (outputWeight) {
            "Milligram" -> convertedValue = toOut
            "Gram" -> convertedValue = (toOut / 1000)
            "Kilogram" -> convertedValue = (toOut / 1000000)
            "Metric Ton" -> convertedValue = (toOut / 1000000000)
            "Ounce" -> convertedValue = (toOut / 28349.5)
            "Pound" -> convertedValue = (toOut / 453592)
            "US Ton" -> convertedValue = (toOut / 907184740)
        }

        return convertedValue
    }

}
