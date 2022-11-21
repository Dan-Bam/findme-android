package com.example.lost_android.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.ParseException
import java.util.regex.Pattern

fun String.convertNumberToPhoneNumber(): String {
    return try {
        val regexString = "(\\d{3})(\\d{3,4})(\\d{4})"
        return if (!Pattern.matches(regexString, this)) this else Regex(regexString).replace(
            this,
            "$1-$2-$3"
        )
    } catch (e: ParseException) {
        e.printStackTrace()
        this
    }
}

fun String.removeDot(): String {
    return this.replace("^\"|\"$".toRegex(), "")
}

fun EditText.setOnTextChanged(action: (p0: CharSequence?, p1: Int, p2: Int, p3: Int) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun afterTextChanged(p0: Editable?) = Unit

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            action(p0, p1, p2, p3)
        }
    })
}