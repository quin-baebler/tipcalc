package edu.uw.ischool.qbaebler.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sCharge = findViewById<EditText>(R.id.amount_edit)
        var tipButton = findViewById<Button>(R.id.tip_button)

        sCharge.addTextChangedListener(object : TextWatcher {
             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
             override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Enable tip button only if there's valid input
                val text = s.toString()
                if (text.isNotBlank()) {
                    tipButton.isEnabled = true
                } else {
                    tipButton.isEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        tipButton.setOnClickListener {
            val amount = sCharge.text.toString().toDouble()
            val tip = amount * 0.15
            val formattedTip = formatCurrency(tip.toString())

            Toast.makeText(this, "Tip: $$formattedTip", Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatCurrency(value: String): String {
        val number = value.toDoubleOrNull() ?: return ""
        return String.format("%.2f", number)
    }

}