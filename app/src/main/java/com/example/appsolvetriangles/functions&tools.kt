package com.example.appsolvetriangles
import java.math.BigDecimal
import java.math.RoundingMode


fun degrees(rads: Double): Double {
    return rads * 57.29577951308232
}

fun radians(degs: Double): Double {
    return degs / 57.29577951308232
}

fun roundTo(value: Double, places: Int): Double {
//  ( value = 123.456789, place =2 ) => 123.46
    return BigDecimal(value.toString())
        .setScale(places, RoundingMode.HALF_UP)
        .toDouble()
}