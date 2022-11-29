package com.example.lost_android.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.ParseException
import java.util.regex.Pattern
import kotlin.math.roundToInt

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

fun Int.dp(context: Context): Int {
    val destiny = context.resources.displayMetrics.density
    return (this * destiny).roundToInt()
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

fun Uri.getPath(context: Context): String? {
    val cursor = context.contentResolver.query(this, arrayOf(MediaStore.Images.Media.DATA), null, null, null)
    cursor?.moveToNext()
    val path = cursor?.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
    cursor?.close()
    return path
}

fun File.toRequestBody(): MultipartBody.Part {
    val requestFile = RequestBody.create(MediaType.parse("image/*"), this)
    return MultipartBody.Part.createFormData("file", this.name, requestFile)
}