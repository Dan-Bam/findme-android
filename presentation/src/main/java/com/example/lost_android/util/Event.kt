package com.example.lost_android.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import java.util.*


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
    this.isEnabled = this.isActivated
}

private fun isNextId(p0: CharSequence?, pw: EditText, pwRe: EditText): Boolean =
    !p0.isNullOrBlank() && pw.text.toString() == pwRe.text.toString() && !pw.text.isNullOrBlank()

private fun isNextPw(p0: CharSequence?, id: EditText, pw: EditText): Boolean =
    !p0.isNullOrBlank() && pw.text.toString() == p0.toString() && !id.text.isNullOrBlank()

private fun isNext(p0: CharSequence?): Boolean =
    !p0.isNullOrBlank()

fun keyboardHide(context: Activity) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        context.currentFocus?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}

fun keyboardShow(context: Activity) {
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
    var drawable = ContextCompat.getDrawable(context, drawableId)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable!!).mutate()
    }
    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable!!.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

fun checkPermission(context: Activity, permissions: List<String>): Boolean {
    permissions.forEach {
        val permission = ContextCompat.checkSelfPermission(context, it)
        if (permission == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(arrayOf(it), 1)
                return false
            }
            return false
        }
    }
    return true
}