package com.ekotyoo.composechampion.data.mapper

import com.ekotyoo.composechampion.domain.model.Cast
import com.ekotyoo.composechampion.ui.screens.detail.model.CastUiModel

fun CastUiModel.fromDomain(cast: Cast): CastUiModel {
    return CastUiModel(name = cast.name, image = cast.image)
}