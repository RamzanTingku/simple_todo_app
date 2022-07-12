package com.example.simpletodoapp.util

class Validator {
    object NullChecker{
        fun isNonNullString(title: String?): Boolean {
            return title?.trim()?.isNotEmpty() == true
        }
    }
}