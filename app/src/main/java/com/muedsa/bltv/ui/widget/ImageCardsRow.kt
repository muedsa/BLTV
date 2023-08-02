package com.muedsa.bltv.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.muedsa.bltv.model.ContentModel
import com.muedsa.bltv.ui.theme.BLTVTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun <T> ImageCardsRow(
    title: String,
    modelList: List<T> = listOf(),
    imageFn: (model: T) -> String,
    contentFn: (model: T) -> ContentModel? = { _ -> null },
    onItemFocus: (index: Int, item: T) -> Unit = { _, _ -> },
    onItemClick: (index: Int, item: T) -> Unit = { _, _ -> }
) {
    Column(Modifier.height(150.dp)) {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1
        )
        TvLazyRow(
            contentPadding = PaddingValues(end = 100.dp)
        ) {
            item {
                modelList.forEachIndexed { index, it ->
                    ImageContentCard(
                        modifier = Modifier.padding(end = 12.dp),
                        url = imageFn(it),
                        type = CardType.COMPACT,
                        model = contentFn(it),
                        onItemFocus = { onItemFocus(index, it) },
                        onItemClick = { onItemClick(index, it) }
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun <T> StandardImageCardsRow(
    title: String,
    modelList: List<T> = listOf(),
    imageFn: (model: T) -> String,
    contentFn: (model: T) -> ContentModel? = { _ -> null },
    onItemFocus: (index: Int, item: T) -> Unit = { _, _ -> },
    onItemClick: (index: Int, item: T) -> Unit = { _, _ -> }
) {
    Column {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1
        )
        TvLazyRow(
            contentPadding = PaddingValues(end = 100.dp)
        ) {
            modelList.forEachIndexed { index, it ->
                item {
                    ImageContentCard(
                        modifier = Modifier
                            .width(215.dp)
                            .padding(end = 12.dp),
                        url = imageFn(it),
                        type = CardType.STANDARD,
                        model = contentFn(it),
                        onItemFocus = { onItemFocus(index, it) },
                        onItemClick = { onItemClick(index, it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ImageCardsRowPreview() {
    val modelList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5")
    BLTVTheme {
        ImageCardsRow(
            title = "Row Title",
            modelList = modelList,
            imageFn = { _ -> "" },
            contentFn = { ContentModel(it) }
        )
    }
}

@Preview
@Composable
fun StandardImageCardsRowPreview() {
    val modelList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 5")
    BLTVTheme {
        StandardImageCardsRow(
            title = "Standard Row Title",
            modelList = modelList,
            imageFn = { _ -> "" },
            contentFn = { ContentModel(it) }
        )
    }
}