package pa.ac.utp.components.listaestudiantes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pa.ac.utp.components.listaestudiantes.R
import pa.ac.utp.components.listaestudiantes.data.databasemodel.Student
import pa.ac.utp.components.listaestudiantes.ui.utils.recomposeHighlighter

@Composable
fun StudentRow(student: Student, index: Int, onClick: (Int) -> Unit, onClickDelete: (Student) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .recomposeHighlighter()
        .clickable {
            onClick(index)
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .size(56.dp),
                painter = painterResource(id = R.drawable.student2),
                contentDescription = student.name
            )
            Text(text = student.fullname, modifier = Modifier.padding(start = 10.dp))
            IconButton(onClick = { onClickDelete(student) }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
            }
        }
        Divider(thickness = 5.dp, modifier = Modifier.recomposeHighlighter())
    }
}
