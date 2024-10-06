package com.example.partyapp.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.partyapp.R
import com.example.partyapp.data.entity.User
import com.example.partyapp.ui.theme.Indigo
import com.example.partyapp.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    onSuccessfulLogin: () -> Unit,
    onRegisterButtonClicked: () -> Unit,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    //val users by userViewModel.users.collectAsState(initial = listOf())
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        logo(Color.White)
        loginForm(onSuccessfulLogin, userViewModel)
        elseRegister(onRegisterButtonClicked)
    }
}

sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    object Name : InputType(
        label = "Username",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password : InputType(
        label = "Password",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun logo(color: Color) {
    Icon(
        painter = painterResource(id = R.drawable.logo_icon),
        contentDescription = "Logo",
        tint = color,
        modifier = Modifier.size(80.dp)
    )
    Text(
        text = stringResource(id = R.string.app_name),
        style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive),
        color = color
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginForm(
    onSuccessfulLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    val users by userViewModel.users.collectAsState(initial = listOf())
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val inputType1 = InputType.Name
    val inputType2 = InputType.Password
    val inputColors = TextFieldDefaults.textFieldColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
    TextField(
        value = username,
        onValueChange = { username = it },
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15)),
        leadingIcon = { Icon(imageVector = inputType1.icon, null) },
        label = { Text(text = inputType1.label) },
        shape = MaterialTheme.shapes.small,
        colors = inputColors,
        singleLine = true,
        keyboardOptions = inputType1.keyboardOptions,
        visualTransformation = inputType1.visualTransformation,
        keyboardActions = KeyboardActions(onNext = {
            passwordFocusRequester.requestFocus()
        })
    )

    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(passwordFocusRequester)
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(15)),
        leadingIcon = { Icon(imageVector = inputType2.icon, null) },
        label = { Text(text = inputType2.label) },
        shape = MaterialTheme.shapes.small,
        colors = inputColors,
        singleLine = true,
        keyboardOptions = inputType2.keyboardOptions,
        visualTransformation = inputType2.visualTransformation,
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
        })
    )

    Button(
        onClick = {
            val toast = Toast.makeText(context, "Wrong credentials", Toast.LENGTH_SHORT)
            var flag: User? = null
            userViewModel.viewModelScope.launch(Dispatchers.IO) {
                flag = userViewModel.checkLoginCredentials(username, password)
            }.invokeOnCompletion {
                if(flag != null) {
                    Log.d("LOGIN_TAG " + "LoginScreen.kt","successful Login ")
                    userViewModel.startSession(flag!!)
                    userViewModel.selectUser(flag!!)
                    userViewModel.viewModelScope.launch(Dispatchers.Main) { onSuccessfulLogin() }
                }
                else {
                    toast.show()
                }
            }
            //val loggedUser = users.find { it.username == username && it.password == password }
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "LOGIN", Modifier.padding(vertical = 8.dp), color = Indigo)
    }
}

@Composable
fun elseRegister(onRegisterButtonClicked: () -> Unit?) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("Don't have an account?", color = Color.Gray)
        TextButton(onClick = { onRegisterButtonClicked() }) {
            Text(text = "REGISTER")
        }
    }
}