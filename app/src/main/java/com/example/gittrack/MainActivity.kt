package com.example.gittrack

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gittrack.data.remote.RepoService
import com.example.gittrack.data.remote.RetrofitHelper
import com.example.gittrack.data.repository.RepoRepository
import com.example.gittrack.models.Repo
import com.example.gittrack.ui.theme.GitTrackTheme
import com.example.gittrack.viewmodel.MainVModel
import com.example.gittrack.viewmodel.MainVModelFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    private val mainViewModel : MainVModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






        Log.d("YUGALKUMAR","BEFORE")

        mainViewModel.repo.observe(this, Observer{
            Log.d("YUGALKUMAR", it.name)
            Log.d("YUGALKUMAR", it.description)
            Log.d("YUGALKUMAR", it.url)
            Toast.makeText(this, mainViewModel.repository.repo.value?.name, Toast.LENGTH_SHORT).show()

        })
        Log.d("YUGALKUMAR","AFTER")


        setContent {
            GitTrackTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController, mainViewModel = mainViewModel)



//                // A surface container using the 'background' color from the theme
//                val viewModel = hiltViewModel<MainViewModel>()
//                val context = LocalContext.current
//
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//
//
//                    val gitState by viewModel.userState.collectAsState()
//                    when(gitState){
//                        is GitState.LoadState->{
//                            Box(
//                                modifier = Modifier.fillMaxSize(),
//                            contentAlignment = Alignment.Center
//                            ){
//                                CircularProgressIndicator()
//                            }
//                        }
//                        is GitState.Success->{
//                            val repo = (gitState as GitState.Success).users
//                            RepoListView(repo)
//                        }
//                        is GitState.Error->{
//                            val message = (gitState as GitState.Error).errorMessage
//                            Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
//                        }
//                        else -> {}
//                    }
//                }
            }
        }
    }
}

@Composable
fun RepoListView(user: Repo){
//LazyColumn(){
//    items(user){ repo->
//        UserItem(user = repo)
//
//    }
//}

    UserItem(repo = user)
}

@Composable
fun UserItem(repo: Repo){

    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(modifier = Modifier
            .size(50.dp)
            .background(Color.White, CircleShape)
        ){
            Text(modifier = Modifier.padding(10.dp),

                text = repo.name.substring(0, 1),
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
        }
    }

    Column(modifier = Modifier.padding(start = 6.dp),
    verticalArrangement = Arrangement.Center) {

        Text(
            text = repo.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(top = 4.dp))
        Text(
            text = repo.description,
            fontSize = 16.sp,
            color = Color.White
        )

    }
}
