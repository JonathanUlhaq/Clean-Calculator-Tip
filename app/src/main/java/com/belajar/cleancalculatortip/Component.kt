package com.belajar.cleancalculatortip

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputKeyboard(
    value:MutableState<String>,
    label:String,
    isSingleLine:Boolean,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions
) {
    OutlinedTextField(value = value.value ,
        onValueChange = {value.value = it},
        label = { Text(text = "$label")},
        singleLine = isSingleLine,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney,
            contentDescription = null
        )})
        
}

@Composable
fun RoundedButton(
    icon:ImageVector,
    color:Color = Color.White,
    modifier:Modifier = Modifier,
    onCLick:() -> Unit
) {
        IconButton(onClick = {onCLick.invoke()}) {
            Card(
                backgroundColor = color,
                shape = CircleShape
            ) {
              Box(
                  contentAlignment = Alignment.Center
              ) {
                  Icon(imageVector = icon,
                      contentDescription = null,
                      modifier = modifier
                          .padding(10.dp))
              }
            }
        }
}