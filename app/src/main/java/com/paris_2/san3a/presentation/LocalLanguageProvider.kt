package com.paris_2.san3a.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

val LocalLanguage = compositionLocalOf { "en" }

@Composable
fun LanguageProvider(
    content: @Composable () -> Unit
) {
    val currentLanguage by AppLocaleState.locale.collectAsState()
    val context = LocalContext.current
    val locale = Locale.forLanguageTag(currentLanguage)

    Locale.setDefault(locale)
    val config = context.resources.configuration

    config.setLocale(locale)

    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    CompositionLocalProvider(
        LocalLanguage provides currentLanguage
    ) {
        content()
    }
}