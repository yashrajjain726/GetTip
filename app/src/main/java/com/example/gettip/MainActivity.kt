package com.example.gettip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gettip.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{calculateTip()}

    }

     fun calculateTip() {
         val stringInTextField = binding.costOfServiceEditText.text.toString()
         val cost = stringInTextField.toDoubleOrNull()
         if(cost==null)
         {
             binding.tipResult.text = ""
             return
         }
         val tipPercentage = when(binding.tipOption.checkedRadioButtonId){
             R.id.option_eighteen_percent->0.18
             R.id.option_fifteen_percent->0.15
             else -> 0.20
         }
         var tip = tipPercentage * cost
         val roundUp = binding.roundUpSwitch.isChecked
         if(roundUp)
         {
             tip = kotlin.math.ceil(tip)
         }
         val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
         binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }
}