package com.example.lost_android.util

sealed class Keyboard {
    object ID: Keyboard()
    object PW: Keyboard()
    object DEFAULT: Keyboard()
}