package com.filnm.nearby.ui.components.market_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.filnm.nearby.data.model.Rule
import com.filnm.nearby.data.model.mock.mockRules
import com.filnm.nearby.ui.theme.Gray400
import com.filnm.nearby.ui.theme.Gray500
import com.filnm.nearby.ui.theme.Typography

@Composable
fun MarketDetailsRules(modifier: Modifier = Modifier, rules: List<Rule>) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Regulamento", style = Typography.headlineSmall, color = Gray400)

        /*rules.forEach { rule ->
            Text(text = rule.description, style = Typography.bodyMedium)
        }*/

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = rules.joinToString("\n", transform = {
                "- ${it.description}"
            }),
            style = Typography.labelMedium,
            color = Gray500,
            lineHeight = 24.sp
        )
    }
}

@Preview
@Composable
private fun MarketDetailsRulesPreview() {
    MarketDetailsRules(
        modifier = Modifier.fillMaxWidth(),
        rules = mockRules
    )
}