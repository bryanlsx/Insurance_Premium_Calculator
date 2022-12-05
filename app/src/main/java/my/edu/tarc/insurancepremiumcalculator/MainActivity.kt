package my.edu.tarc.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.insurancepremiumcalculator.databinding.ActivityMainBinding
import java.util.*


//AdapterView.OnclickListener
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //important to set

        binding.buttonReset.setOnClickListener {
            binding.textViewPremium.setText("")
            binding.spinnerAge.setSelection(0)
            if(binding.checkBoxSmoker.isChecked){
                binding.checkBoxSmoker.setChecked(false)
            }
            binding.radioGroupGender.clearCheck()
        }

        //binding.spinnerAge.setOnItemClickListener(this)
        binding.buttonCalculate.setOnClickListener {
            val age = binding.spinnerAge.selectedItemPosition
            val gender = binding.radioGroupGender.checkedRadioButtonId
            val smoker = binding.checkBoxSmoker.isChecked
            val myLocale = Locale.getDefault()
            val myCurrency = Currency.getInstance(myLocale)
            var premium = 0
            var amount = 0

            when (age) { //when condition then result
                0 -> {
                    premium = 60
                }
                1 -> {
                    premium = 70
                }
                2 -> {
                    premium = 90
                }
                3 -> {
                    premium = 120
                }
                in 4..5 -> {
                    premium = 150
                }
            }
            if (gender == R.id.radioButtonMale) {
                when (age) {
                    0 -> {
                        premium
                    }
                    1 -> {
                        premium += 50
                    }
                    2 -> {
                        premium += 100
                    }
                    3 -> {
                        premium += 150
                    }
                    in 4..5 -> {
                        premium += 150
                    }
                }
            }
            if (smoker) {
                //Calculate premium for a smoker
                when (age) {
                    0 -> {
                        premium
                    }
                    1 -> {
                        premium += 100
                    }
                    2 -> {
                        premium += 150
                    }
                    3 -> {
                        premium += 200
                    }
                    4 -> {
                        premium += 250
                    }
                    5 -> {
                        premium += 300
                    }
                }
            }
//            display the final premium value
            binding.textViewPremium.text = String.format("%s %d", myCurrency.currencyCode.toString(), premium)
        }
    }
}
