package com.teachmeskills.kotlinsample.extensions


fun String.hasALetter(): Boolean {
    return this.contains("a", ignoreCase = true)
}