package com.ekotyoo.composechampion.ui.mapper

import com.ekotyoo.composechampion.domain.model.Cast
import com.ekotyoo.composechampion.ui.screens.detail.model.CastUiModel

fun Cast.toUiModel(): CastUiModel {
    return CastUiModel(name = this.name, image = this.image)
}