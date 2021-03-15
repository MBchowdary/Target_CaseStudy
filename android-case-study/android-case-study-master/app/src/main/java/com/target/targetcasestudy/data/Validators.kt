package com.target.targetcasestudy.data

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */


fun validateCreditCard(creditCardNumber: String): Boolean {
    if (REGEX.listOfPattern.size == 0) {
        REGEX.createPatterns()
    }
    if (creditCardNumber.length in 13..19) {
        for (card in REGEX.listOfPattern) {
            // Compile the ReGex
            val pattern: Pattern = Pattern.compile(card)
            val m: Matcher = pattern.matcher(creditCardNumber)
            return m.matches()
        }
    }
    return false
}

class REGEX {
    companion object {
        val listOfPattern: ArrayList<String> = ArrayList()
        fun createPatterns() {
            listOfPattern.add("^4[0-9]{6,}$")
            listOfPattern.add("^5[1-5][0-9]{5,}$")
            listOfPattern.add("^3[47][0-9]{5,}$")
            listOfPattern.add("^3(?:0[0-5]|[68][0-9])[0-9]{4,}$")
            listOfPattern.add("^6(?:011|5[0-9]{2})[0-9]{3,}$")
            listOfPattern.add("^(?:2131|1800|35[0-9]{3})[0-9]{3,}$")
        }
    }
}