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

class VolumeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_volume, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var finalVolume : Double

        etVolumeA.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isNotEmpty() && etVolumeA.isFocused && etVolumeA.text.toString().trim().matches("-?\\d+(\\.\\d+)?".toRegex())) {
                    val volumeAInput = etVolumeA.text.toString().toDouble()

                    val convertedValue = convertFrom(volumeAInput)

                    finalVolume = convertTo(convertedValue)
                    finalVolume = String.format("%.4f", finalVolume).toDouble()
                    etVolumeB.setText(finalVolume.toString())

                } else if(etVolumeB.text.isNotEmpty() && etVolumeA.text.isEmpty()){

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
                    finalVolume = String.format("%.2f", finalVolume).toDouble()
                    etVolumeA.setText(finalVolume.toString())

                } else if(etVolumeA.text.isNotEmpty() && etVolumeB.text.isEmpty()){

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
            "US Teaspoon" -> outputVolume = (fromIn * 4.929)
            "US Tablespoon" -> outputVolume = (fromIn * 14.787)
            "US Fluid Ounce" -> outputVolume = (fromIn * 29.574)
            "US Cup" -> outputVolume = (fromIn * 240)
            "US Pint" -> outputVolume = (fromIn * 473)
            "US Quart" -> outputVolume = (fromIn * 946)
            "US Gallon" -> outputVolume = (fromIn * 3785)
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
            "US Teaspoon" -> convertedValue = (toOut / 4.929)
            "US Tablespoon" -> convertedValue = (toOut / 14.787)
            "US Fluid Ounce" -> convertedValue = (toOut / 29.574)
            "US Cup" -> convertedValue = (toOut / 240)
            "US Pint" -> convertedValue = (toOut /473)
            "US Quart" -> convertedValue = (toOut / 946)
            "US Gallon" -> convertedValue = (toOut / 3785)
        }

        return convertedValue
    }
}