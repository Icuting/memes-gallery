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
    val listImg = listOf(R.drawable.meme_1, R.drawable.meme_2, R.drawable.meme_3, R.drawable.meme_4, R.drawable.meme_5)
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
            // TODO: refactor function onClick [1]
            EditImageButton("Next", onClick = {
                if(currentIndex == listImg.size - 1) {
                    currentIndex = 0
                }else{
                    currentIndex += 1
                }
                currentImg = listImg[currentIndex]
            })
            EditImageButton("Prev", onClick = {
                if(currentIndex == 0) {
                    currentIndex = listImg.size - 1
                }else{
                    currentIndex -=1
                }
                currentImg = listImg[currentIndex]
            })
        }
    }
}

@Composable
fun ImagePreview(
    img:Int = R.drawable.meme_1
) {
    Column {
        Image(
            painter = painterResource(img),
            contentDescription = "a",
            Modifier
                .border(3.dp, Color.DarkGray)
                .padding(20.dp)
                .height(500.dp)
                .fillMaxWidth(),

        )
        Spacer(Modifier.height(16.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 4.dp,
                )
                .padding(15.dp)

            ) {
            Text(
                text = "title photo",
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                fontWeight = FontWeight(700)
            )
            Text(
                text = "description photo",
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
// TODO: refactor function onClick [1]
//fun nextImg(
//    currentIndex: Int,
//    listImg: List<Int>,
//    currentImage: Int,
//) {
//    if(currentIndex == listImg.size) {
//        val currentIndex = 0
//    }
//
//}
//
//fun prevImg(
//    currentIndex: Int,
//    listImg: List<Int>,
//    currentImage: Int,
//) {
//
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MemesGalleryTheme {
        MemesGalleryScreen()
    }
}