package com.ajibsbaba.composables.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.ajibsbaba.composables.R

@Composable
fun Greeting(name: String) {
    Text(text = stringResource(id = R.string.hello, name),
        textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
}


class HelloProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = listOf("PreviewParameterProvider").asSequence()
}

@Composable
@Preview
fun GreetingPreview(@PreviewParameter(HelloProvider::class) name: String) {
    Greeting(name = "Samuel")
}

@Composable
@Preview(group = "greeting-group", showBackground = true, backgroundColor = 0xffff0000)
fun GreetingPreviewGroup() {
    Greeting(name = "Testing")
}


@Composable
fun TextInput(name: MutableState<String>, namesEntered: MutableState<Boolean>) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        TextField(value = name.value, onValueChange = {
            name.value = it
        }, placeholder = {
            Text(text = stringResource(id = R.string.placeholder))
        }, modifier = Modifier
            .alignByBaseline()
            .weight(1.0F), singleLine = true, keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            capitalization = KeyboardCapitalization.Words
        ), keyboardActions = KeyboardActions(onAny = {
            namesEntered.value = true
        })  )
    }

}


@Composable
fun GreetingMessage() {
    val name = remember { mutableStateOf("") }
    val nameEntered = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (nameEntered.value) {
            Greeting(name = name.value)
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                TextInput(name = name, namesEntered = nameEntered)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MessagePreview() {
    GreetingMessage()
}