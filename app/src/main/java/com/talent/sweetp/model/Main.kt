package com.talent.sweetp.model


data class Main(
    val temp: Float,
    val humidity: Int
) {
    companion object {
        const val DEFAULT_TEMP = 0f
        const val DEFAULT_HUMIDITY = 0

        fun createDefault(): Main {
            return Main(DEFAULT_TEMP, DEFAULT_HUMIDITY)
        }
    }
}