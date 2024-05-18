package com.example.ppk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val salaryEditText: EditText = findViewById(R.id.salary)
        val employeeContributionEditText: EditText = findViewById(R.id.employeeContribution)
        val employerContributionEditText: EditText = findViewById(R.id.employerContribution)
        val yearsToRetirementEditText: EditText = findViewById(R.id.yearsToRetirement)
        val governmentContributionCheckBox: CheckBox = findViewById(R.id.governmentContribution)
        val expectedReturnRateEditText: EditText = findViewById(R.id.expectedReturnRate)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val themeSwitch: Switch = findViewById(R.id.themeSwitch)

        val nightModeFlags = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK
        themeSwitch.isChecked = nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        calculateButton.setOnClickListener {
            val salaryStr = salaryEditText.text.toString()
            val employeeContributionStr = employeeContributionEditText.text.toString()
            val employerContributionStr = employerContributionEditText.text.toString()
            val yearsToRetirementStr = yearsToRetirementEditText.text.toString()
            val expectedReturnRateStr = expectedReturnRateEditText.text.toString()

            if (salaryStr.isNotEmpty() && employeeContributionStr.isNotEmpty() &&
                employerContributionStr.isNotEmpty() && yearsToRetirementStr.isNotEmpty() &&
                expectedReturnRateStr.isNotEmpty()) {

                val salary = salaryStr.toDouble()
                val employeeContribution = employeeContributionStr.toDouble()
                val employerContribution = employerContributionStr.toDouble()
                val yearsToRetirement = yearsToRetirementStr.toInt()
                val expectedReturnRate = expectedReturnRateStr.toDouble() / 100


                val governmentContribution = if (governmentContributionCheckBox.isChecked) {
                    250.0 // Załóżmy, że dopłata państwa wynosi stałe 250 zł rocznie
                } else {
                    0.0
                }

                val totalContribution = (employeeContribution + employerContribution) * salary / 100
                val totalYears = yearsToRetirement.toDouble()
                val futureValue = totalContribution * ((1 + expectedReturnRate).pow(totalYears) - 1) / expectedReturnRate

                val monthlyPayment = futureValue / (yearsToRetirement * 12)

                resultTextView.text = "Zebrana kwota na emeryturę: ${futureValue.toInt()} zł\n" +
                        "Przewidywana miesięczna wypłata: ${monthlyPayment.toInt()} zł"
            } else {
                resultTextView.text = "Proszę wypełnić wszystkie pola."
            }
        }
    }
}