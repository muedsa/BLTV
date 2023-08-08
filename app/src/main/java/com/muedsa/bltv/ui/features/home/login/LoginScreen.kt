package com.muedsa.bltv.ui.features.home.login

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.QrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorColor
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorColors
import com.muedsa.bltv.model.login.LoginState
import com.muedsa.bltv.model.login.LoginViewModel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val imageSizePx = if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
        displayMetrics.heightPixels /  2
    } else {
        displayMetrics.widthPixels / 2
    }
    val loginState = loginViewModel.loginState

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(loginState == LoginState.UNKNOWN) {
            CircularProgressIndicator()
        }else{
            val imageModel: Any = if (loginState == LoginState.LOGIN) {
                "https://i2.hdslb.com/bfs/face/2dfca82d101d889c240c88d2187149240fdce65d.jpg"
            } else {
                generateQRCode("https://i2.hdslb.com/bfs/face/2dfca82d101d889c240c88d2187149240fdce65d.jpg",
                    imageSizePx,
                    MaterialTheme.colorScheme.onSurface.toArgb(),
                    context)
            }

            AsyncImage(
                modifier = Modifier.fillMaxSize(0.5f),
                model = imageModel,
                contentDescription = null
            )

            if(loginState == LoginState.LOGIN) {
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedButton(
                    onClick = {
                        //todo 注销动作
                        loginViewModel.logout()
                    }
                ) {
                    Text(text = "注销", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }



    }
}

fun generateQRCode(url: String, size: Int, color: Int, context: Context): Bitmap {
    val qrColor = QrVectorColor.Solid(color)
    val qrCodeView = QrCodeDrawable(
        context,
        QrData.Url(url), QrVectorOptions.Builder().setColors(
            colors = QrVectorColors(
                ball = qrColor,
                frame = qrColor,
                dark = qrColor
            )
        ).build()
    )
    return qrCodeView.toBitmap(size, size)
}