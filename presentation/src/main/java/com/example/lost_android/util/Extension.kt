package com.example.lost_android.util

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