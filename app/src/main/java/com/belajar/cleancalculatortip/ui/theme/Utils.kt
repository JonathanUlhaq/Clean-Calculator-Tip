package com.belajar.cleancalculatortip.ui.theme

import androidx.compose.runtime.MutableState

fun NoZeroValue(
    jumlahOrang: MutableState<Int>
) {
    if (jumlahOrang.value < 1) jumlahOrang.value = 1
    if (jumlahOrang.value > 10) jumlahOrang.value = 10

}

fun percentageSlider(value:Float):Int {
    return (value * 100).toInt()
}

fun jumlahTip(value:Float,nominal:Int): Int {
    return (value*nominal).toInt()
}