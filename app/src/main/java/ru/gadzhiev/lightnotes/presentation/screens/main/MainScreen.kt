package ru.gadzhiev.lightnotes.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.gadzhiev.lightnotes.presentation.navigation.Screens
import ru.gadzhiev.lightnotes.presentation.ui.theme.LightNotesTheme
import ru.gadzhiev.lightnotes.presentation.ui.theme.components.NoteItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<MainViewModel>()
    val notes = viewModel.notes.observeAsState(listOf()).value
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screens.AddScreen.rout) }) {
                Icon(imageVector = Icons.Filled.Add, tint = Color.White, contentDescription = "add")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "LightNote",
                fontSize = 40.sp,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, bottom = 10.dp)
            )

            notes.forEach { note ->
                NoteItem(
                    title = note.title,
                    backgroundColor = Color(note.backgroundColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(horizontal = 16.dp)
                        .clickable { navController.navigate(Screens.DetailsScreen.rout + "/${note.id}") }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefPreview() {
    LightNotesTheme {
        MainScreen(rememberNavController())
    }
}