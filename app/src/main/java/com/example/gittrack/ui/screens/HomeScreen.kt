package com.example.gittrack.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gittrack.models.Repo
import com.example.gittrack.viewmodel.MainVModel
import androidx.compose.foundation.lazy.items
import com.example.gittrack.R
import com.example.gittrack.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    mainVModel: MainVModel
){
    Column {


        ActionBar(navController = navController)

        val repos by mainVModel.lRepo.observeAsState(initial = emptyList())

        if (repos.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){

                Icon(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(140.dp)
                        .clickable {
                            navController.navigate(route = Screen.AddRepo.route)
                        },

                    painter = painterResource(R.drawable.add),
                    contentDescription = "print"
                )

            }


        }else{
            DetailsContent(mainVModel = mainVModel)
        }

    }
}


@Composable
fun ActionBar(navController: NavController){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center,

        ) {
        Icon(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .clickable {
                    navController.navigate(route = Screen.AddRepo.route)
                },

            painter = painterResource(R.drawable.add),
            contentDescription = "print"
        )
    }
}

@Composable
fun DetailsContent(
    mainVModel: MainVModel
) {

    val repos by mainVModel.lRepo.observeAsState(initial = emptyList())

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
           items = repos
        ) {
            RepoCard(repo = it,mainVModel)
        }
    }
}



@Composable
fun RepoCard(repo: Repo, mainVModel: MainVModel) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                      mainVModel.itemClicked(repo)
            },
        elevation = 2.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {

        Row(modifier = Modifier.padding(20.dp)) {
            Column(modifier = Modifier.weight(1f),
                Arrangement.Center) {
                Text(
                    text = repo.name,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Text(
                    text = "Description :- "+repo.description,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )
                Text(
                    text = "Link :- "+repo.html_url,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 15.sp
                    )
                )


            }
        }
    }
}