package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.target.targetcasestudy.R
import android.widget.Toast
import android.text.Editable
import com.target.targetcasestudy.data.validateCreditCard


/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : DialogFragment() {

    private lateinit var submitButton: Button
    private lateinit var creditCardInput: EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.dialog_payment, container, false)

        submitButton = root.findViewById(R.id.submit)
        submitButton.isEnabled = false
        creditCardInput = root.findViewById(R.id.card_number)
        val cancelButton: Button = root.findViewById(R.id.cancel)

        cancelButton.setOnClickListener { dismiss() }
        submitButton.setOnClickListener { dismiss() }

        // TODO enable the submit button based on card number validity using Validators.validateCreditCard()
        creditCardInput.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                // Empty
            }

            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                //Empty
            }

            override fun afterTextChanged(s: Editable) {
                val userInput = s.toString().replace(nonDigits, "")
                submitButton.isEnabled = validateCreditCard(userInput)
            }
        })

        return root
    }

    companion object {
        private val nonDigits = Regex("[^\\d]")
    }
}