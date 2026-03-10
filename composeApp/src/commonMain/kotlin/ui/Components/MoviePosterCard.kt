package ui.Components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import data.Movie
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MoviePosterCard(movie: Movie, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(2f / 3f)
            .clickable { onClick() },
        shape = RoundedCornerShape(4.dp)
    ) {
        KamelImage(
            resource = asyncPainterResource(movie.fullPosterUrl),
            contentDescription = movie.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}
