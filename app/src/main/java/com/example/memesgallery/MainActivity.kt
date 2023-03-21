package com.example.memesgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.ui.Alignment
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.memesgallery.ui.theme.MemesGalleryTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class Meme(
    val src: Int,
    val title: String,
    val description: String
)

val listImg = listOf<Meme>(
    Meme(
        src = R.drawable.meme_1,
        title = "SpongeBob SquarePants",
        description = "SpongeBob SquarePants shows a rainbow"
    ),
    Meme(
        R.drawable.meme_2,
        title = "Pepe the Frog",
        description = "Pepe the Frog is a cartoon character and Internet meme created by cartoonist Matt Furie."
    ),
    Meme(
        R.drawable.meme_3,
        title = "untitled meme",
        description = "Just a funny meme"
    ),
    Meme(
        R.drawable.meme_4,
        title = "untitled meme",
        description = "Just a funny meme"
    ),
    Meme(
        R.drawable.meme_5,
        title = "untitled meme",
        description = "Just a funny meme"
    ),
    Meme(
        R.drawable.meme_6,
        title = "Philip J. Fry - Futurama",
        description = "Shut up and take my money"
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemesGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MemesGalleryScreen()
                }
            }
        }
    }
}

@Composable
fun MemesGalleryScreen() {
    var currentIndex by remember { mutableStateOf(0) }
    var currentImg by remember { mutableStateOf(listImg[currentIndex]) }
    Column(
         Modifier
            .padding(32.dp)
    ) {
        ImagePreview(img = currentImg)
        Spacer(Modifier.height(16.dp))
        Row(
             Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            EditImageButton("Prev", onClick = {
                if(currentIndex == 0) {
                    currentIndex = listImg.size - 1
                }else{
                    currentIndex -=1
                }
                currentImg = listImg[currentIndex]
            })
            EditImageButton("Next", onClick = {
                if(currentIndex == listImg.size - 1) {
                    currentIndex = 0
                }else{
                    currentIndex += 1
                }
                currentImg = listImg[currentIndex]
            })
        }
    }
}

@Composable
fun ImagePreview(
    img: Meme
) {
    Column {
        Image(
            painter = painterResource(img.src),
            contentDescription = img.title,
            Modifier
                .border(3.dp, Color.DarkGray)
                .padding(20.dp)
                .height(400.dp)
                .fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .shadow(
                    elevation = 4.dp,
                )
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
            ) {
            Text(
                text = img.title,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                fontWeight = FontWeight(700)
            )
            Text(
                text = img.description,
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)
            )
        }

    }
}

@Composable
fun EditImageButton(
    name: String,
    onClick: () -> Unit = {},
) {
    Column {
        Button(onClick = onClick) {
            Text(text = name)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MemesGalleryTheme {
        MemesGalleryScreen()
    }
}

