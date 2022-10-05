package com.example.epli.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.epli.R
import com.example.epli.ui.theme.AppTheme

@Composable
fun RatingWidget(
    modifier: Modifier = Modifier,
    ratingValue: Int,
    onRatingClicked: (Int) -> Unit,
) {

    Row(
        modifier = modifier
    ) {
        repeat(5) {
            Icon(
                imageVector = ImageVector.vectorResource(
                    when {
                        it > ratingValue-1 -> R.drawable.ic_start_outlined
                        else -> R.drawable.ic_star_filled

                    }
                ),
                "",
                tint = AppTheme.colors.white,
                modifier = Modifier.clickable(
                    onClick = {
                        if (it != ratingValue - 1) {
                            onRatingClicked(it + 1)
                        } else {
                            onRatingClicked(0)
                        }
                    }
                )
            )
        }
    }
}