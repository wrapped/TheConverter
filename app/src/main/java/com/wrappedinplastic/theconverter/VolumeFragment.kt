package com.wrappedinplastic.theconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import kotlinx.android.synthetic.main.fragment_volume.*
import java.text.DecimalFormat

class VolumeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_volume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var finalVolume : Double
        val formatVolume = DecimalFormat("#.##")

        etVolumeA.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etVolumeA.isFocused && etVolumeA.text.toString().trim().matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    val volumeAInput = etVolumeA.text.toString().toDouble()

                    val convertedValue = convertFrom(volumeAInput)

                    finalVolume = convertTo(convertedValue)
                    etVolumeB.setText(formatVolume.format(finalVolume))

                } else if(etVolumeB.text.toString() !="" && etVolumeA.text.toString() == ""){

                    etVolumeB.setText("")

                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        etVolumeB.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etVolumeB.isFocused && etVolumeB.text.toString().trim().matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    val volumeBInput = etVolumeB.text.toString().toDouble()

                    val convertedValue = convertFrom(volumeBInput)

                    finalVolume = convertTo(convertedValue)
                    etVolumeA.setText(formatVolume.format(finalVolume))

                } else if(etVolumeA.text.toString() !="" && etVolumeB.text.toString() == ""){

                    etVolumeA.setText("")

                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

        spVolumeA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etVolumeA.setText("")
                tilVolumeA.hint = spVolumeA.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spVolumeB.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                etVolumeB.setText("")
                tilVolumeB.hint = spVolumeB.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun convertFrom(fromIn: Double) : Double {

        var inputVolume = ""
        var outputVolume : Double = fromIn

        when {
            etVolumeA.isFocused -> inputVolume = spVolumeA.selectedItem.toString()
            etVolumeB.isFocused -> inputVolume = spVolumeB.selectedItem.toString()
        }

        when (inputVolume) {
            "Milliliter" -> outputVolume = (fromIn)
            "Centiliter" -> outputVolume = (fromIn * 10)
            "Deciliter" -> outputVolume = (fromIn * 100)
            "Liter" -> outputVolume = (fromIn * 1000)
            "US Teaspoon" -> outputVolume = (fromIn * 4.92892)
            "US Tablespoon" -> outputVolume = (fromIn * 14.787)
            "US Fluid Ounce" -> outputVolume = (fromIn * 29.574)
            "US Cup" -> outputVolume = (fromIn * 236.588)
            "US Pint" -> outputVolume = (fromIn * 473.176)
            "US Quart" -> outputVolume = (fromIn * 946.353)
            "US Gallon" -> outputVolume = (fromIn * 3785.41)
        }

        return outputVolume

    }

    fun convertTo(toOut: Double) : Double {

        var outputVolume = ""
        var convertedValue : Double = toOut

        when {
            etVolumeA.isFocused -> outputVolume = spVolumeB.selectedItem.toString()
            etVolumeB.isFocused -> outputVolume = spVolumeA.selectedItem.toString()
        }

        when (outputVolume) {
            "Milliliter" -> convertedValue = toOut
            "Centiliter" -> convertedValue = (toOut / 10)
            "Deciliter" -> convertedValue = (toOut / 100)
            "Liter" -> convertedValue = (toOut / 1000)
            "US Teaspoon" -> convertedValue = (toOut / 4.92892)
            "US Tablespoon" -> convertedValue = (toOut / 14.787)
            "US Fluid Ounce" -> convertedValue = (toOut / 29.574)
            "US Cup" -> convertedValue = (toOut / 236.588)
            "US Pint" -> convertedValue = (toOut / 473.176)
            "US Quart" -> convertedValue = (toOut / 946.353)
            "US Gallon" -> convertedValue = (toOut / 3785.41)
        }

        return convertedValue
    }
}