package com.coby.happiness.domain.type

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.coby.cobylibrary.ui.theme.BlueNormal
import com.coby.cobylibrary.ui.theme.CyanNormal
import com.coby.cobylibrary.ui.theme.GreenNormal
import com.coby.cobylibrary.ui.theme.LightBlueNormal
import com.coby.cobylibrary.ui.theme.LimeNormal
import com.coby.cobylibrary.ui.theme.OrangeNormal
import com.coby.cobylibrary.ui.theme.PinkNormal
import com.coby.cobylibrary.ui.theme.PurpleNormal
import com.coby.cobylibrary.ui.theme.RedNormal
import com.coby.cobylibrary.ui.theme.RedOrangeNormal
import com.coby.cobylibrary.ui.theme.VioletNormal

enum class ColorType {
    BLUE,
    RED,
    GREEN,
    ORANGE,
    RED_ORANGE,
    LIME,
    CYAN,
    LIGHT_BLUE,
    VIOLET,
    PURPLE,
    PINK;

    @Composable
    fun color(): Color {
        return when (this) {
            BLUE -> Color.BlueNormal()
            RED -> Color.RedNormal()
            GREEN -> Color.GreenNormal()
            ORANGE -> Color.OrangeNormal()
            RED_ORANGE -> Color.RedOrangeNormal()
            LIME -> Color.LimeNormal()
            CYAN -> Color.CyanNormal()
            LIGHT_BLUE -> Color.LightBlueNormal()
            VIOLET -> Color.VioletNormal()
            PURPLE -> Color.PurpleNormal()
            PINK -> Color.PinkNormal()
        }
    }

    companion object {
        fun fromRawValue(value: String): ColorType? {
            return values().find { it.name.equals(value, ignoreCase = true) }
        }
    }
}