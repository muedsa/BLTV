package com.muedsa.bltv.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.muedsa.bltv.ui.theme.BLTVTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ScreenBackground(
    url: String?,
    type: ScreenBackgroundType = ScreenBackgroundType.FULL_SCREEN
) {
    val configuration = LocalConfiguration.current
    val screenWidth  = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val immersiveImageHeight = screenHeight * 8 / 10
    val immersiveImageWidth = immersiveImageHeight * 16 / 9
    val immersiveImageOffsetX = screenWidth - immersiveImageWidth
    val imageModifier = if(type == ScreenBackgroundType.SCRIM) {
        Modifier
            .size(immersiveImageWidth, immersiveImageHeight)
            .offset(x = immersiveImageOffsetX)
    }else{
        Modifier.size(screenWidth, screenHeight)
    }

    if(!url.isNullOrEmpty()) {
        Box(Modifier.fillMaxSize()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.FillBounds
            )

            if(type == ScreenBackgroundType.SCRIM) {
                Box(
                    Modifier
                        .size(immersiveImageWidth, immersiveImageHeight)
                        .offset(x = immersiveImageOffsetX)
                        .background(
                            Brush.horizontalGradient(
                                0.0f to MaterialTheme.colorScheme.background,
                                0.8f to Color.Transparent,
                                1.0f to Color.Transparent,
                                startX = 0.0f
                            )
                        )
                )
                Box(
                    Modifier
                        .size(immersiveImageWidth, immersiveImageHeight)
                        .offset(x = immersiveImageOffsetX)
                        .background(
                            Brush.verticalGradient(
                                0.0f to Color.Transparent,
                                0.5f to MaterialTheme.colorScheme.background.copy(alpha = 0.3f),
                                1.0f to MaterialTheme.colorScheme.background,
                                startY = 0.0f
                            )
                        )
                )
            }
        }
    }
}

enum class ScreenBackgroundType{
    FULL_SCREEN,
    SCRIM
}

@Preview
@Composable
fun ScreenBackgroundPreview() {
    BLTVTheme {
        ScreenBackground("https://i1.hdslb.com/bfs/archive/3f9a95620b6a1ea1be04e3a001081a3f544dbd18.jpg")
    }
}