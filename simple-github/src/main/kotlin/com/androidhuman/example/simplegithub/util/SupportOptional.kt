package com.androidhuman.example.simplegithub.util

sealed class SupportOptional<out T : Any>(private val _value: T?) {

    val isEmpty: Boolean
        get() = null == _value

    val value: T
        get() = checkNotNull(_value)
}

class Empty<out T : Any> : SupportOptional<T>(null)

class Some<out T : Any>(value: T) : SupportOptional<T>(value)

inline fun <reified T : Any> optionalOf(value: T?)
        = if (null != value) Some(value) else Empty<T>()

inline fun <reified T : Any> emptyOptional() = Empty<T>()
