package com.example.gittrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gittrack.viewmodel.MainVModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddRepoScreen(
    navController: NavHostController,
    mainViewModel: MainVModel
) {

    Column(
        modifier = Modifier
            .padding(10.dp, 80.dp, 10.dp, 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val ownername = remember { mutableStateOf(TextFieldValue()) }
        val reponame = remember { mutableStateOf(TextFieldValue()) }
        var text by remember { mutableStateOf(TextFieldValue("Text")) }
        val keyboardController = LocalSoftwareKeyboardController.current

        Text(text = "Save Repository", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    focusedIndicatorColor = Color.Green,
                    cursorColor = Color.Black),
            label = {
                Text(color = Color.Black,
                    text = "Owner Name")
                    },
            value = ownername.value,
            onValueChange = { ownername.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()})
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.LightGray,
                    focusedIndicatorColor = Color.Green,
                    cursorColor = Color.Black),
            label = { Text(color = Color.Black,text = "Repository Name") },
            value = reponame.value,
            onValueChange = { reponame.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()})
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black,
                contentColor = Color.White),
                onClick = {
                    mainViewModel.buttonClick(ownername,reponame)
                    navController.popBackStack()
                          },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "ADD REPO")
            }
        }


    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    AddRepoScreen(rememberNavController(), viewModel())
}