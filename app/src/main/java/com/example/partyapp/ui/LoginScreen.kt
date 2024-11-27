package com.example.partyapp.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.partyapp.data.entity.User
import com.example.partyapp.ui.components.PartyTextField
import com.example.partyapp.ui.components.TextFieldType
import com.example.partyapp.ui.components.partyAppLogo
import com.example.partyapp.ui.theme.Indigo
import com.example.partyapp.ui.theme.Salmon
import com.example.partyapp.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    onSuccessfulLogin: () -> Unit, // redirects to home
    onRegisterButtonClicked: () -> Unit,
    userViewModel: UserViewModel
) {
    var isLogin: Boolean by remember { mutableStateOf(value = true) }
    val scroll = rememberScrollState(0)
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        partyAppLogo(Color.White)
        if (isLogin) {
            LoginForm(onSuccessfulLogin, userViewModel)
            SwitchMode(true, onSwitch = { isLogin = !isLogin })
        } else {
            RegistrationForm(onSuccessfulLogin, userViewModel)
            SwitchMode(false, onSwitch = { isLogin = !isLogin })
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    onSuccessfulLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    val users by userViewModel.users.collectAsState(initial = listOf())
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    PartyTextField(
        value = username, onValueChange = { username = it },
        placeholder = "Username",
        leadingIcon = { Icon(imageVector = Icons.Default.Person, "User icon", tint = Color.White) },
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {passwordFocusRequester.requestFocus()}),
        modifier = Modifier.fillMaxWidth()
    )

    PartyTextField(
        value = password, onValueChange = { password = it },
        textType = TextFieldType.PASSWORD,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, "Locket icon", tint = Color.White) },
        placeholder = "Password",
        keyboardImeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(passwordFocusRequester)
    )

    Button(
        onClick = {
            var userFound: User? = null
            userViewModel.viewModelScope.launch(Dispatchers.IO) {
                userFound = userViewModel.checkLoginCredentials(username, password)
            }.invokeOnCompletion {
                if(userFound != null) {
                    Log.d("LOGIN_TAG " + "LoginScreen.kt","Successful Login ")
                    userViewModel.startSession(userFound!!)
                    userViewModel.selectUser(userFound!!)
                    userViewModel.viewModelScope.launch(Dispatchers.Main) { onSuccessfulLogin() }
                }
                else {
                    Toast.makeText(context, "Wrong credentials", Toast.LENGTH_SHORT).show()
                }
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "LOGIN", Modifier.padding(vertical = 8.dp), color = Indigo)
    }
}

@Composable
fun RegistrationForm(
    onSuccessfulLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age: Int? by remember { mutableStateOf(null) }

    val passwordFocusRequester = FocusRequester()
    val emailFocusRequester = FocusRequester()
    val ageFocusRequester = FocusRequester()
    val surnameFocusRequester = FocusRequester()
    val usernameFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    PartyTextField(
        value = name, onValueChange = { name = it },
        placeholder = "Name",
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {surnameFocusRequester.requestFocus()}),
        modifier = Modifier.fillMaxWidth()
    )
    PartyTextField(
        value = surname, onValueChange = { surname = it },
        placeholder = "Surname",
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {ageFocusRequester.requestFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(surnameFocusRequester)
    )
    PartyTextField(
        value = if (age != null) age.toString() else "",
        onValueChange = { age = if (it == "") null else (it).toInt() },
        textType = TextFieldType.NUMBER,
        placeholder = "Age",
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {usernameFocusRequester.requestFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(ageFocusRequester)
    )
    PartyTextField(
        value = username, onValueChange = { username = it },
        placeholder = "Username",
        leadingIcon = { Icon(imageVector = Icons.Default.Person, "Username", tint = Color.White) },
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {emailFocusRequester.requestFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(usernameFocusRequester)
    )
    PartyTextField(
        textType = TextFieldType.EMAIL,
        value = email, onValueChange = { email = it },
        placeholder = "Email",
        leadingIcon = { Icon(imageVector = Icons.Default.Email, "Email", tint = Color.White) },
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {passwordFocusRequester.requestFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(emailFocusRequester)
    )
    PartyTextField(
        textType = TextFieldType.PASSWORD,
        value = password, onValueChange = { password = it },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, "Password", tint = Color.White) },
        placeholder = "Password",
        keyboardImeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(passwordFocusRequester)
    )

    Button(
        onClick = {
            val user = User(
                name = name, surname = surname,
                username = username, email = email, password = password,
                exp = 0, age = age ?: -1, pfp = ""
            )
            var userFound: User? = null
            userViewModel.viewModelScope.launch(Dispatchers.IO) {
                userFound = userViewModel.getUserFromUsername(username)
                if (userFound == null) {
                    userViewModel.insertNewUser(user)
                }
            }.invokeOnCompletion {
                if (userFound == null) {
                    userViewModel.startSession(user)
                    userViewModel.selectUser(user)
                    userViewModel.viewModelScope.launch(Dispatchers.Main) { onSuccessfulLogin() }
                } else {
                    userViewModel.viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, "Username already taken", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "REGISTER", Modifier.padding(vertical = 8.dp), color = Indigo)
    }
}

@Composable
fun SwitchMode(
    isLogin: Boolean,
    onSwitch: () -> Unit
) {
    val variableText = if (isLogin) "Don't" else "Already"
    val switchText = if (isLogin) "REGISTER" else "LOGIN"
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$variableText have an account?",
            color = Color.Gray
        )
        TextButton(onClick = onSwitch) {
            Text(text = switchText, color = Salmon, fontWeight = FontWeight.Bold)
        }
    }
}
