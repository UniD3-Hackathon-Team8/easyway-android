@file:Suppress("DEPRECATION")

package com.example.easyway.ui.road

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.easyway.R

@Composable
fun RoadView(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        //웹사이트
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()

                    settings.javaScriptEnabled = true

                    loadUrl("http://merseong.iptime.org:3000/main")
                }
            },
            update = {
                it.loadUrl("http://merseong.iptime.org:3000/main")
            }
        )
    }
}


@Composable
private fun RoadSearchBar(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onKeyboardSearch: KeyboardActionScope.() -> Unit,
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = onKeyboardSearch
        ),
        singleLine = true,
        cursorBrush = SolidColor(colorResource(id = R.color.black)),
        decorationBox = { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(3.dp)
                    )
                    .padding(horizontal = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_search_24),
                    contentDescription = null,
                    tint = colorResource(id = R.color.main_0)
                )

                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun RoadViewPreview() {
    RoadView()
}