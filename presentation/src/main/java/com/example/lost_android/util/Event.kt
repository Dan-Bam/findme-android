package com.example.lost_android.util

import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton


fun setNextBtn(
    p0: CharSequence?,
    button: AppCompatButton,
    type: Keyboard = Keyboard.DEFAULT,
    pw: EditText? = null,
    pwRe: EditText? = null,
    id: EditText? = null
) = button.apply {
    this.isActivated = when (type) {
        Keyboard.ID -> isNextId(p0, pw!!, pwRe!!)
        Keyboard.PW -> isNextPw(p0, id!!, pw!!)
        Keyboard.DEFAULT -> isNext(p0)
    }
    this.isClickable = this.isActivated
}

private fun isNextId(p0: CharSequence?, pw: EditText, pwRe: EditText): Boolean =
    !p0.isNullOrBlank() && pw.text.toString() == pwRe.text.toString() && !pw.text.isNullOrBlank()

private fun isNextPw(p0: CharSequence?, id: EditText, pw: EditText): Boolean =
    !p0.isNullOrBlank() && pw.text.toString() == p0.toString() && !id.text.isNullOrBlank()

private fun isNext(p0: CharSequence?): Boolean =
    !p0.isNullOrBlank()