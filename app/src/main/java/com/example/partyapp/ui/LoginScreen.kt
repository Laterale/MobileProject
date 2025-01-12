package com.example.partyapp.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.partyapp.R
import com.example.partyapp.data.entity.User
import com.example.partyapp.ui.components.PartyAppLogo
import com.example.partyapp.ui.components.PartyTextField
import com.example.partyapp.ui.components.TextFieldType
import com.example.partyapp.ui.theme.Indigo
import com.example.partyapp.ui.theme.Salmon
import com.example.partyapp.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEmpty
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
        PartyAppLogo(Color.White)
        if (isLogin) {
            LoginForm(onSuccessfulLogin, userViewModel)
            SwitchMode(true, onSwitch = { isLogin = !isLogin })
        } else {
            RegistrationForm(onSuccessfulLogin, userViewModel)
            SwitchMode(false, onSwitch = { isLogin = !isLogin })
        }
    }
}

@Composable
private fun LoginForm(
    onSuccessfulLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    val errMsg = stringResource(id = R.string.err_wrong_credentials)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    PartyTextField(
        value = username, onValueChange = { username = it },
        placeholder = stringResource(id = R.string.username),
        leadingIcon = { Icon(imageVector = Icons.Default.Person, stringResource(id = R.string.icon_user), tint = Color.White) },
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {passwordFocusRequester.requestFocus()}),
        modifier = Modifier.fillMaxWidth()
    )

    PartyTextField(
        value = password, onValueChange = { password = it },
        textType = TextFieldType.PASSWORD,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, stringResource(id = R.string.icon_pwd), tint = Color.White) },
        placeholder = stringResource(id = R.string.password),
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
                    Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show()
                }
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.login).uppercase(),
            modifier = Modifier.padding(vertical = 8.dp),
            color = Indigo
        )
    }
}

@Composable
private fun RegistrationForm(
    onSuccessfulLogin: () -> Unit,
    userViewModel: UserViewModel
) {
    val context = LocalContext.current
    val errUsernameTaken = stringResource(id = R.string.err_username_taken)
    val errTryAgain = stringResource(id = R.string.err_try_again)
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }

    val emailFocusRequester = FocusRequester()
    val passwordFocusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current

    PartyTextField(
        value = username, onValueChange = { username = it },
        placeholder = stringResource(id = R.string.username),
        leadingIcon = { Icon(imageVector = Icons.Default.Person, stringResource(id = R.string.icon_user), tint = Color.White) },
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {emailFocusRequester.requestFocus()}),
        modifier = Modifier.fillMaxWidth()
    )
    PartyTextField(
        textType = TextFieldType.EMAIL,
        value = email, onValueChange = { email = it },
        placeholder = stringResource(id = R.string.email),
        leadingIcon = { Icon(imageVector = Icons.Default.Email, stringResource(id = R.string.icon_email), tint = Color.White) },
        keyboardImeAction = ImeAction.Next,
        keyboardActions = KeyboardActions(onNext = {passwordFocusRequester.requestFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(emailFocusRequester)
    )
    PartyTextField(
        textType = TextFieldType.PASSWORD,
        value = password, onValueChange = { password = it },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, stringResource(id = R.string.icon_pwd), tint = Color.White) },
        placeholder = stringResource(id = R.string.password),
        keyboardImeAction = ImeAction.Done,
        keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()}),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(passwordFocusRequester)
    )

    Button(
        onClick = {
            val user = User(
                username = username.trim(), email = email.trim(), password = password.trim(),
                exp = 0, pfp = ""
            )
            var userFound: User? = null
            userViewModel.viewModelScope.launch(Dispatchers.IO) {
                try {
                    userFound = userViewModel.getUserFromUsername(username)
                        .onEmpty {
                            Log.d("FETCH_USER", "No user found with username: $username")
                        }.firstOrNull() // Collects the first value or returns null if empty

                    if (userFound == null) {
                        userViewModel.viewModelScope.launch(Dispatchers.Main) {
                            userViewModel.insertNewUser(user)
                        }
                        Log.d("FETCH_USER", "Inserting new user")
                    } else {
                        Log.d("FETCH_USER", "User already exists: $userFound")
                        userViewModel.viewModelScope.launch(Dispatchers.Main) {
                            Toast.makeText(context, errUsernameTaken, Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("FETCH_USER", "Error fetching user: ${e.message}", e)
                    userViewModel.viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, errTryAgain, Toast.LENGTH_SHORT).show()
                    }
                }
            }.invokeOnCompletion {
                if (userFound == null) {
                    userViewModel.startSession(user)
                    userViewModel.selectUser(user)
                    userViewModel.viewModelScope.launch(Dispatchers.Main) { onSuccessfulLogin() }
                } else {
                    userViewModel.viewModelScope.launch(Dispatchers.Main) {
                        Toast.makeText(context, errUsernameTaken, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.register).uppercase(), Modifier.padding(vertical = 8.dp), color = Indigo)
    }
}

@Composable
private fun SwitchMode(
    isLogin: Boolean,
    onSwitch: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (isLogin) stringResource(id = R.string.lbl_need_register)
                   else stringResource(id = R.string.lbl_need_login),
            color = Color.Gray
        )
        TextButton(onClick = onSwitch) {
            Text(
                text = if (isLogin) stringResource(id = R.string.register).uppercase()
                       else stringResource(id = R.string.login).uppercase(),
                color = Salmon, fontWeight = FontWeight.Bold
            )
        }
    }
}
