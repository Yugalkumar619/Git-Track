package com.example.gittrack

import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gittrack.models.Repo
import com.example.gittrack.navigation.SetupNavGraph
import com.example.gittrack.ui.theme.GitTrackTheme
import com.example.gittrack.viewmodel.MainVModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    private val mainViewModel : MainVModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // Show the different toast messages while there is any change in the data
        mainViewModel.repo.observe(this, Observer{
            Toast.makeText(this, mainViewModel.repository.repo.value?.name, Toast.LENGTH_LONG).show()

        })

        // Share the repository between different apps while click the share button
        mainViewModel.shareItem.observe(this, Observer {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, mainViewModel.shareItem.value!!.html_url)
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Share repository via")
                startActivity(shareIntent)
        })

        // Redirect the user to the github repository page
        mainViewModel.item.observe(this, Observer {
            Toast.makeText(this, mainViewModel.item.value?.name, Toast.LENGTH_SHORT).show()
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mainViewModel.item.value?.html_url))
            startActivity(browserIntent)
        })

        // here we use navController to navigate between different screens
        setContent {
            GitTrackTheme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController, mainViewModel = mainViewModel)


            }
        }
    }
}

