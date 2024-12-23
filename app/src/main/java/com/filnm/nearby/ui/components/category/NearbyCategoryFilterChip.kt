package com.filnm.nearby.ui.components.category

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnm.nearby.data.model.NearbyCategory
import com.filnm.nearby.ui.theme.Gray400
import com.filnm.nearby.ui.theme.GreenBase
import com.filnm.nearby.ui.theme.Typography

@Composable
fun NearbyCategoryFilterChip( //Célula da listagem
    modifier: Modifier = Modifier,
    category: NearbyCategory,
    isSelected: Boolean,
    onClick: (isSelected: Boolean) -> Unit
) {

    FilterChip(
        modifier = modifier
            .padding(2.dp)
            .heightIn(min= 36.dp),
        elevation = FilterChipDefaults.filterChipElevation(
            elevation = 8.dp
        ),
        leadingIcon = {
            category.icon?.let {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(it),
                    contentDescription = "Icone do filtro da catégoria"
                )
            }
        },
        border = FilterChipDefaults.filterChipBorder(
            enabled = false,
            selected = isSelected,
            disabledBorderColor = Gray400,
            borderWidth = 0.dp,
            borderColor = Color.Transparent
        ),
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = GreenBase
        ),
        selected = isSelected,
        onClick = { onClick(!isSelected)},
        label = {
            Text(
                text = category.name,
                style = Typography.bodyMedium,
                color = if(isSelected) Color.White else Gray400
            )
        },
    )
    
}

@Preview
@Composable
private fun NearbyCategoryFilterChipPrev() {
    NearbyCategoryFilterChip(
        category = NearbyCategory(id = "1", name = "Pão"),
        isSelected = false,
        onClick = {}
    )
}

@Preview
@Composable
private fun NearbyCategoryFilterChipPrev2() {
    NearbyCategoryFilterChip(
        category = NearbyCategory(id = "2", name = "Leite"),
        isSelected = true,
        onClick = {}
    )
}