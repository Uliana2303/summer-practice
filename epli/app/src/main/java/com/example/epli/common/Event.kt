package com.example.epli.common

interface EventHandler<E> {
    fun obtainEvent(event: E)
}