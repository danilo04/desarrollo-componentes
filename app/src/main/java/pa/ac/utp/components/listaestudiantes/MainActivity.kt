package pa.ac.utp.components.listaestudiantes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pa.ac.utp.components.listaestudiantes.data.databasemodel.Student
import pa.ac.utp.components.listaestudiantes.ui.components.StudentRow
import pa.ac.utp.components.listaestudiantes.ui.theme.ListaEstudiantesTheme
import pa.ac.utp.components.listaestudiantes.ui.utils.recomposeHighlighter
import pa.ac.utp.components.listaestudiantes.ui.viewmodel.PublicApiViewModel
import pa.ac.utp.components.listaestudiantes.ui.viewmodel.StudentViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListaEstudiantesTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    StudentsNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun StudentListScreen(onClick: (Int) -> Unit) {
    val viewModel = hiltViewModel<StudentViewModel>()
    val uiState = viewModel.uiState.collectAsState(StudentViewModel.UiState.Loading)
    val onClickDelete = { student: Student ->
        viewModel.deleteStudent(student)
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()) {

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
                                StudentRow(student = student, index, onClick, onClickDelete)
                            }
                        }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center,
                                ) {
                                    ProvideTextStyle(value = MaterialTheme.typography.subtitle1) {
                                        Text("Change can eat in class")
                                    }
                                    Switch(
                                        checked = state.canEatInClass,
                                        onCheckedChange = { checkValue -> viewModel.changeEatInClass(checkValue) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Button(onClick = { viewModel.addStudent(Student(0, "Danilo", "Dominguez", "7-xx-xx")) }) {
                Text("Add Student")
            }
        }
    }
}

@Composable
fun StudentsNavHost(
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
                onClick = { index: Int -> navController.navigate("profile/$index") },
            )
        }
        composable("profile/{userId}") {
            ProfileScreen {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun ProfileScreen(
    onNavigateToFriends: () -> Unit,
    /*...*/
) {
    val viewModel = hiltViewModel<PublicApiViewModel>()
    val uiState = viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState.value) {
            PublicApiViewModel.UiState.Loading -> {
                Text("Loading")
            }
            is PublicApiViewModel.UiState.PublicApiState -> {
                Text("Name: ${state.publicApi?.api}")
                Text("Description: ${state.publicApi?.description}")
                Text("Link: ${state.publicApi?.link}")
            }
        }
        Button(onClick = onNavigateToFriends) {
            Text(text = "Back")
        }
    }
}
