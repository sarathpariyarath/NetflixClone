package ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.Movie
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieDetailScreen(movie: Movie, onBack: () -> Unit) {
    val painterResource = asyncPainterResource(movie.fullPosterUrl)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Image Section
        Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
            // 2. The updated Kamel usage
            KamelImage(
                resource = painterResource,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                // 3. Add a placeholder (Like SwiftUI's ProgressView in AsyncImage)
                onLoading = { progress ->
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Red)
                },
                onFailure = { exception ->
                    // This will print the error to your Logcat/Console
                    println("❌ Kamel Error: ${exception.message}")
                    Text("Error: ${exception.message}", color = Color.Red)
                }
            )

            // Back Button
            IconButton(onClick = onBack, modifier = Modifier.padding(16.dp)) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
        }

        // Details Section
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Play logic */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("▶ Play", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.LightGray
            )
        }
    }
}