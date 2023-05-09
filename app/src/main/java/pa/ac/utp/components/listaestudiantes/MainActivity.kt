package pa.ac.utp.components.listaestudiantes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pa.ac.utp.components.listaestudiantes.ui.components.StudentRow
import pa.ac.utp.components.listaestudiantes.ui.theme.ListaEstudiantesTheme
import pa.ac.utp.components.listaestudiantes.ui.utils.recomposeHighlighter
import pa.ac.utp.components.listaestudiantes.ui.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onClick = { index: Int ->
            Toast.makeText(this, "Click on $index", Toast.LENGTH_SHORT).show()
            //viewModel.onClick(index)
        }

        setContent {
            val uiState = viewModel.uiState.collectAsState(StudentViewModel.UiState.Loading)

            ListaEstudiantesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    StudentsNavHost(studentViewModel = viewModel, navController = navController)
                }
            }
        }
    }
}

@Composable
fun StudentListScreen(viewModel: StudentViewModel, onClick: (Int) -> Unit) {
    val uiState = viewModel.uiState.collectAsState(StudentViewModel.UiState.Loading)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 30.dp)
                .recomposeHighlighter(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            when (val state = uiState.value) {
                StudentViewModel.UiState.Loading -> {
                    item {
                        Text(text = "Loading")
                    }
                }

                is StudentViewModel.UiState.StudentList -> {
                    state.students.forEachIndexed { index, student ->
                        item {
                            StudentRow(student = student, state.selected, index, onClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentsNavHost(
    studentViewModel: StudentViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "list-students"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("list-students") {
            StudentListScreen(
                studentViewModel,
                onClick = { index: Int -> navController.navigate("profile/$index") },
            )
        }
        composable("profile/{userId}") { backStackEntry ->
            ProfileScreen(
                userId = backStackEntry.arguments?.getString("userId"),
                studentViewModel = studentViewModel
            ) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun ProfileScreen(
    userId: String?,
    studentViewModel: StudentViewModel,
    onNavigateToFriends: () -> Unit,
    /*...*/
) {
    /*...*/
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "See profile of user ID $userId")
        Button(onClick = onNavigateToFriends) {
            Text(text = "Back")
        }
    }
}
