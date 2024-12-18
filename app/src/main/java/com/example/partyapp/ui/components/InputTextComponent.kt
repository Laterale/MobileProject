package com.example.partyapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.partyapp.ui.theme.Glass20

enum class TextFieldType {
    TEXT,
    PASSWORD,
    EMAIL,
    NUMBER
}

/*
* TextField(
        value = username,
        onValueChange = { username = it },
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15)),
        leadingIcon = { Icon(imageVector = inputType1.icon, null) },
        label = { Text(text = inputType1.label) },
        shape = MaterialTheme.shapes.small,
        keyboardOptions = inputType1.keyboardOptions,
        visualTransformation = inputType1.visualTransformation,
        keyboardActions = KeyboardActions(onNext = {
            passwordFocusRequester.requestFocus()
        })
    )
* */

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PartyTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
    placeholder: String = "",
    singleLine: Boolean = true,
    leadingIcon: @Composable (() -> Unit)? = null,
    textType: TextFieldType = TextFieldType.TEXT,
    keyboardImeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions()
) {
    val visualTrans = if (textType == TextFieldType.PASSWORD) PasswordVisualTransformation()
        else VisualTransformation.None
    var kType = KeyboardType.Text
    if (textType == TextFieldType.PASSWORD) {
        kType = KeyboardType.Password
    } else if (textType == TextFieldType.EMAIL) {
        kType = KeyboardType.Email
    } else if (textType == TextFieldType.NUMBER) {
        kType = KeyboardType.Number
    }


    TextField(
        value = value,
        onValueChange = { newValue ->
            if (textType == TextFieldType.NUMBER) {
                if (newValue.isDigitsOnly()) {
                    onValueChange(newValue)
                }
            } else {
                onValueChange(newValue)
            }
        },
        label = { Text(placeholder, color = Color.White) },
        leadingIcon = leadingIcon,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.hsl(0f, 0f, 1f, 0.15f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            textColor = Color.White
        ),
        shape = MaterialTheme.shapes.small,
        singleLine = singleLine,
        visualTransformation = visualTrans,
        keyboardOptions = KeyboardOptions(
            keyboardType = kType,
            imeAction = keyboardImeAction
        ),
        keyboardActions = keyboardActions,
        modifier = modifier.border(
            border = BorderStroke(1.dp, Glass20),
            shape = RoundedCornerShape(15)
        )
    )
}

//border = BorderStroke(1.dp, Glass20),