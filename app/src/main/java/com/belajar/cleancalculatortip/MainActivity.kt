package com.belajar.cleancalculatortip

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.belajar.cleancalculatortip.ui.theme.CleanCalculatorTipTheme
import com.belajar.cleancalculatortip.ui.theme.NoZeroValue
import com.belajar.cleancalculatortip.ui.theme.percentageSlider
import com.belajar.cleancalculatortip.ui.theme.jumlahTip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanCalculatorTipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalculatorTipApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorTipApp() {
    val nominalInput = remember {
        mutableStateOf("")
    }

    val jumlahOrang = remember {
        mutableStateOf(1)
    }

    val sliderValue = remember {
        mutableStateOf(0F)
    }
    NoZeroValue(jumlahOrang)
    val percentage = percentageSlider(sliderValue.value)

    val validateInput = remember(nominalInput.value) {
        nominalInput.value.trim().isNotEmpty()
    }

    var jumlahTip = 0
    var totalPerOrang = 0
    if (validateInput)
        jumlahTip = jumlahTip(value = sliderValue.value, nominal = nominalInput.value.toInt() )
        totalPerOrang = (jumlahTip / jumlahOrang.value)
    Scaffold(
        modifier = Modifier
            .padding(14.dp)
    ) {
        Column(
            Modifier.padding(it)
        ) {
            Header(nominal = totalPerOrang)
            Spacer(modifier = Modifier.height(14.dp))
            BodyCalculator(nominalInput = nominalInput,
                validasiInputan =  validateInput,
                jumlahOrang = jumlahOrang,
                sliderValue = sliderValue,
                percentage = percentage,
                jumlahTip = jumlahTip)

        }
    }
}

@Composable
fun BodyCalculator(
    modifier: Modifier = Modifier,
    nominalInput:MutableState<String>,
    validasiInputan:Boolean,
    sliderValue:MutableState<Float>,
    percentage:Int,
    jumlahTip: Int,
    jumlahOrang:MutableState<Int>
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        border = BorderStroke(2.dp, Color.LightGray)
    ) {
        Column(
            modifier
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputKeyboardNominal(nominalInput, validasiInputan)
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = validasiInputan ) {
                Column {
                    JumlahOrang(modifier, jumlahOrang)
                    Spacer(modifier = Modifier.height(12.dp))
                    TotalTip(
                        modifier,
                        jumlahTip = jumlahTip)
                    Column(
                        modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                            Text(text = "$percentage%")
                            Spacer(modifier = modifier.height(10.dp))
                            Slider(value = sliderValue.value ,
                                onValueChange = {sliderValue.value = it},
                                steps = 5)
                    }


                }

            }
        }
    }
}

@Composable
private fun TotalTip(
    modifier: Modifier,
    jumlahTip:Int) {
    Row(
        modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Tip")
        Spacer(modifier = modifier.weight(1F))
        Text(text = "$$jumlahTip")
    }
}

@Composable
private fun JumlahOrang(
    modifier: Modifier,
    jumlahOrang: MutableState<Int>
) {
    Row(
        modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Split")
        Spacer(modifier = modifier.weight(1F))
        RoundedButton(icon = Icons.Rounded.Remove) {
            jumlahOrang.value = jumlahOrang.value - 1
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = "${jumlahOrang.value}")
        Spacer(modifier = Modifier.width(6.dp))
        RoundedButton(icon = Icons.Rounded.Add) {
            jumlahOrang.value = jumlahOrang.value + 1
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun InputKeyboardNominal(
    nominalInput: MutableState<String>,
    validasiInputan: Boolean
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    InputKeyboard(value = nominalInput,
        label = "Masukkan Nominal",
        isSingleLine = true, keyboardActions = KeyboardActions {
            if (!validasiInputan) return@KeyboardActions
            keyboardController?.hide()
        })
}


@Composable
fun Header(
    nominal:Int,
    modifier:Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        color = colorResource(id = R.color.purple),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(top = 24.dp, bottom = 24.dp)
        ) {
                Text(text = "Total Tip Per Orang",
                    fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))
                Text(text = "$$nominal",
                    fontWeight = FontWeight.Bold)
        }
    }
}

