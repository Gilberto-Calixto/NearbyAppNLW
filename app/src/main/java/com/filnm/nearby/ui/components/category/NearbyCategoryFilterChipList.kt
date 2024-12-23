package com.filnm.nearby.ui.components.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.data.model.NearbyCategory
import com.filnm.nearby.data.model.mock.categories

@Composable
fun NearbyCategoryFilterChiplist(
    modifier: Modifier = Modifier,
    categories: List<NearbyCategory>,
    onSelectedCategoryChagend: (NearbyCategory) -> Unit
) { //Listagem!!

    var selectedCategoryId by remember { mutableStateOf(categories.firstOrNull()?.id.orEmpty()) }

    LaunchedEffect(key1 = selectedCategoryId) {
        val selectedCategoryOrNull = com.filnm.nearby.data.model.mock.categories.find {
            it.id == selectedCategoryId
        }

        selectedCategoryOrNull?.let { onSelectedCategoryChagend(it) }
    }

    LazyRow(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = categories, key = {it.id}) { category ->
            NearbyCategoryFilterChip(
                category = category,
                isSelected = category.id == selectedCategoryId,
                onClick = { isSelected ->
                    if(isSelected)
                        selectedCategoryId = category.id
                }
            )
        }
    }



}

@Preview
@Composable
private fun Lista() {
    NearbyCategoryFilterChiplist(
        modifier = Modifier.fillMaxWidth(),
        categories = categories,
        onSelectedCategoryChagend = {}
    )
}