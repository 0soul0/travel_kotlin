package com.sideproject.travel.hilt

import com.sideproject.travel.model.Language


class Data {
    companion object {

        val language: List<Language> = listOf(
            Language("正體中文", "zh-tw"),
            Language("簡體中文", "zh-cn"),
            Language("英文", "en")
        )
    }
}