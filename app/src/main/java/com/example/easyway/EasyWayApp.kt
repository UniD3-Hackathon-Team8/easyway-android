package com.example.easyway

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.easyway.ui.theme.EasyWayTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EasyWayApp() {
    EasyWayTheme {
        Scaffold(
            topBar = { EasyWayAppbar() }
        ) {
            MainTab(
                modifier = Modifier
                    .padding(it)
            )
        }
    }
}

@Composable
private fun EasyWayAppbar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = Color.White
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = colorResource(id = R.color.main_0)
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_menu_24),
                contentDescription = null
            )
        }

        Text(
            text = "EasyWay"
        )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = colorResource(id = R.color.main_0)
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.outline_account_circle_24),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun MainTab(
    modifier: Modifier = Modifier
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("길찾기", "경로 추가")

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        //탭 표시
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = (tabIndex == index),
                    onClick = {
                        tabIndex = index
                    },
                    text = { Text(text = tab) },
                    icon = {
                        when (index) {
                            0 -> Icon(
                                painter = painterResource(id = R.drawable.round_search_24),
                                contentDescription = null
                            )

                            1 -> Icon(
                                painter = painterResource(id = R.drawable.outline_add_location_alt_24),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }

        //내용물
        when(tabIndex) {
            0 -> Text(text = "0")
            1 -> Text(text = "1")
        }
    }
}

@Preview
@Composable
private fun EasyWayAppbarPreview() {
    EasyWayAppbar()
}

@Preview
@Composable
private fun EasyWayAppPreview() {
    EasyWayApp()
}