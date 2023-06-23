package com.example.gittrack

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gittrack.viewmodel.MainVModel


@Composable
fun AddRepoScreen(
    navController: NavController
){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        
        Text(
            text = "Add Repo",
            color = Color.Black,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = {
                navController.popBackStack()

            }) {
            Text(
                color = Color.White,
                text = "Add Repo",
                fontSize = 20.sp)
        }
    }
}

@Composable
fun Login(
    navController: NavController
){
    Column (
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
            ){
        Icon(painter = painterResource(id = R.drawable.add),
            contentDescription = null,
            Modifier.size(80.dp),
            tint = Color.Blue
        )

        Button(onClick = {}, modifier = Modifier.fillMaxSize()) {
            Text(text = "Add Repo", Modifier.padding(vertical = 8.dp))
        }
        Divider(color = Color.White.copy(alpha = 0.3f),
        thickness = 1.dp,
        modifier = Modifier.padding(top = 48.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically){
            Text(text = "Add your favourite Repo", color = Color.Black)
        }
    }
}

@Composable
fun TextInput(){
     var value: String = ""
    TextField(value = value, onValueChange = { value = it })
}


@Composable
@Preview(showBackground = true)
fun AddRepoScreenPreview(){
    AddRepoScreen(navController = rememberNavController())
}

@Composable
fun LoginPage(
    navController: NavHostController,
    mainViewModel: MainVModel
) {

    Column(
        modifier = Modifier.padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val ownername = remember { mutableStateOf(TextFieldValue()) }
        val reponame = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Save Repository", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.background(Color.LightGray),
            label = {
                Text(color = Color.Black, text = "Owner Name")
                    },
            value = ownername.value,
            onValueChange = { ownername.value = it })

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.background(Color.LightGray),
            label = { Text(color = Color.Black,text = "Repo Name") },
            value = reponame.value,
            onValueChange = { reponame.value = it })

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