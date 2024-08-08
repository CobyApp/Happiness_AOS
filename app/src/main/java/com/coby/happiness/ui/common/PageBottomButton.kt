package com.coby.happiness.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coby.happiness.domain.type.PageType

@Composable
fun PageBottomButton(
    selection: PageType,
    isDisabled: Boolean,
    buttonAction: () -> Unit,
    onSelectionChange: (PageType) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        when (selection) {
            PageType.FIRST -> {
                Button(
                    onClick = {
                        if (!isDisabled) {
                            buttonAction()
                        }
                    },
                    enabled = !isDisabled,
                    modifier = Modifier.animateContentSize()
                ) {
                    Text("다음")
                }
            }
            PageType.SECOND -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {
                            onSelectionChange(PageType.FIRST)
                        },
                        modifier = Modifier.width(100.dp)
                    ) {
                        Text("이전")
                    }
                    Button(
                        onClick = {
                            if (!isDisabled) {
                                buttonAction()
                            }
                        },
                        enabled = !isDisabled
                    ) {
                        Text("완료")
                    }
                }
            }
        }
    }
}