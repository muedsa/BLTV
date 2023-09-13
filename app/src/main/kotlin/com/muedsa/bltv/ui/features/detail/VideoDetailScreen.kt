package com.muedsa.bltv.ui.features.detail

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Border
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.OutlinedButtonDefaults
import androidx.tv.material3.Text
import com.muedsa.bltv.PlaybackActivity
import com.muedsa.bltv.ui.navigation.NavigationItems
import com.muedsa.compose.tv.model.ContentModel
import com.muedsa.compose.tv.widget.ContentBlock
import com.muedsa.compose.tv.widget.ContentBlockType
import com.muedsa.compose.tv.widget.ScreenBackground
import com.muedsa.compose.tv.widget.ScreenBackgroundType
import com.muedsa.compose.tv.widget.rememberScreenBackgroundState


@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun VideoDetailScreen(
    onNavigate: (NavigationItems, List<String>?) -> Unit = { _, _ -> }
) {
    val context = LocalContext.current

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val backgroundState = rememberScreenBackgroundState(
        initUrl = "https://i1.hdslb.com/bfs/archive/3f80e62fd5e026eb9fba85cf435200697afe43cc.jpg",
        initType = ScreenBackgroundType.SCRIM,
        initHeaders = mapOf("Referer" to "https://www.bilibili.com")
    )

    val playButtonFocusRequester = remember { FocusRequester() }

    var expanded by remember { mutableStateOf(false) }
    val videoPlayOptions = listOf("1080P", "720P", "480P", "360P")
    var selectedVideoPlayIndex by remember { mutableIntStateOf(0) }

    ScreenBackground(backgroundState)
    TvLazyColumn(Modifier.offset(x = 50.dp)) {
        item {
            ContentBlock(
                modifier = Modifier
                    .offset(x = 8.dp)
                    .width(screenWidth / 2)
                    .height(screenHeight - 150.dp - 75.dp - 48.dp),
                model = ContentModel(
                    title = "【某幻】国产悬疑《三伏》全流程实况 1P三眼神童",
                    subtitle = "某幻君",
                    description = "游戏：三伏\n结尾给我刀瞎了"
                ),
                type = ContentBlockType.CAROUSEL
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    modifier = Modifier.focusRequester(playButtonFocusRequester),
                    onClick = {
                        context.startActivity(Intent(context, PlaybackActivity::class.java))
                    },
                    contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                ) {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "播放"
                    )
                    Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                    Text(text = "播放")
                }
                Spacer(modifier = Modifier.width(20.dp))
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                ) {
                    val buttonContainerShape = RoundedCornerShape(15)
                    val buttonShape = ButtonDefaults.shape(buttonContainerShape)
                    OutlinedButton(
                        modifier = Modifier.menuAnchor(),
                        onClick = { expanded = true },
                        shape = buttonShape,
                        border = OutlinedButtonDefaults.border(
                            border = Border(
                                border = BorderStroke(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                                ),
                                shape = buttonContainerShape
                            ),
                            focusedBorder = Border(
                                border = BorderStroke(
                                    width = 1.65.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                shape = buttonContainerShape
                            ),
                            pressedBorder = Border(
                                border = BorderStroke(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                shape = buttonContainerShape
                            ),
                            disabledBorder = Border(
                                border = BorderStroke(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                                ),
                                shape = buttonContainerShape
                            ),
                            focusedDisabledBorder = Border(
                                border = BorderStroke(
                                    width = 1.5.dp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                                ),
                                shape = buttonContainerShape
                            )
                        ),
                        contentPadding = OutlinedButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Text(text = videoPlayOptions[selectedVideoPlayIndex])
                        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                        Icon(
                            Icons.Outlined.ArrowDropDown,
                            null,
                            Modifier.rotate(if (expanded) 180f else 0f)
                        )
                    }
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        videoPlayOptions.forEachIndexed { index, option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedVideoPlayIndex = index
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(onClick = {
                    onNavigate(NavigationItems.UpVideos, listOf("1"))
                }) {
                    Text(text = "近期投稿")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))

            LaunchedEffect(key1 = Unit) {
                playButtonFocusRequester.requestFocus()
            }
        }
    }
}