package com.compose.movie.screens

import SecurePreferences
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.movie.R

@Preview(showSystemUi = true)
@Composable
fun PreviewSS() {
//    SettingsScreen(null)
}

@Composable
fun SettingsScreen(securePreferences: SecurePreferences?) {
    Column {
        Appearance(securePreferences)
        FontSize(securePreferences)
        FontStyle(securePreferences)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FontSize(securePreferences: SecurePreferences?) {
    val labelFontSize = stringResource(R.string.label_font_size)
    var savedFontSize = securePreferences?.getDataInt(labelFontSize)

    val fontSizes = stringArrayResource(R.array.font_sizes)
    var fontSize by remember {
        mutableStateOf(fontSizes[1].toInt().sp)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    if (savedFontSize == 0) {
        savedFontSize = fontSizes[0].toInt()
    }
    var selectedText by remember {
        mutableStateOf(savedFontSize.toString())
    }
    Column {

        Text(
            text = stringResource(R.string.label_font_size),
            modifier = Modifier.padding(all = 16.dp),
            fontSize = 16.sp
        )
        HorizontalDivider(thickness = 2.dp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                expanded = !expanded
            }) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(expanded = expanded,
                    onDismissRequest = { expanded = false }) {

                    fontSizes.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                securePreferences?.saveData(labelFontSize, item.toInt())
                                println(":::  : " + securePreferences?.getDataInt(labelFontSize))
                                selectedText = item
                                expanded = false
                                fontSize = item.toInt().sp
                            }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FontStyle(securePreferences: SecurePreferences?) {
    val labelFontStyle = stringResource(R.string.label_font_style)
    val fontStyle = stringArrayResource(R.array.font_style)
    var expanded by remember {
        mutableStateOf(false)
    }
    var savedText = securePreferences?.getData(labelFontStyle) ?: fontStyle[0]
    var selectedText by remember {
        mutableStateOf(savedText)
    }

    Column {
        Text(
            text = stringResource(R.string.label_font_style),
            modifier = Modifier.padding(all = 16.dp),
            fontSize = 16.sp,
            fontWeight = getFontWeight(selectedText)
        )
        HorizontalDivider(thickness = 2.dp)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {
                expanded = !expanded
            }) {
                TextField(
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(expanded = expanded,
                    onDismissRequest = { expanded = false }) {

                    fontStyle.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                securePreferences?.saveData(labelFontStyle, item)
                                selectedText = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Appearance(securePreferences: SecurePreferences?) {
    val radioOptions =
        listOf(
            stringResource(R.string.label_dark),
            stringResource(R.string.label_light)
        )
    val keyAppearance = stringResource(R.string.label_appearance)
    var savedAppearance =
        securePreferences?.getData(keyAppearance) ?: radioOptions[1]

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(savedAppearance)
    }

    Column {
        Text(
            text = stringResource(R.string.label_appearance),
            modifier = Modifier.padding(all = 16.dp),
            fontSize = 16.sp
        )
        HorizontalDivider(thickness = 2.dp)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            radioOptions.forEach { text ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(selected = (text == selectedOption),
                            onClick = {
                                securePreferences?.saveData(keyAppearance, text)
                                onOptionSelected(text)
                            }),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            securePreferences?.saveData(keyAppearance, text)
                            onOptionSelected(text)
                        },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            Color.Green,
                            Color.Blue
                        ),
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    )
                    Text(
                        text = text,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}

private fun getFontWeight(font: String): FontWeight {
    return when (font) {
        "Thin" -> {
            FontWeight.Thin
        }

        "Normal" -> {
            FontWeight.Normal
        }

        "Medium" -> {
            FontWeight.Medium
        }

        "Bold" -> {
            FontWeight.Bold
        }

        else -> {
            FontWeight.ExtraBold
        }
    }
}
