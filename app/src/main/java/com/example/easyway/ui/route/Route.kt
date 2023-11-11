package com.example.easyway.ui.route

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easyway.R

@Composable
fun RouteView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        //리워드
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 30.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "리워드",
                color = colorResource(id = R.color.main_0),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Text(
                text = "12,345 원",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        //내가 추가한 경로
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 30.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "내가 추가한 경로",
                color = colorResource(id = R.color.main_0),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_content_paste_search_24),
                    contentDescription = null,
                    modifier = modifier
                        .size(48.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("2023-11-11")
                        }
                        append("\n")
                        append("17시 43분")
                    },
                    fontSize = 16.sp
                )
            }

            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_content_paste_search_24),
                    contentDescription = null,
                    modifier = modifier
                        .size(48.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("2023-11-11")
                        }
                        append("\n")
                        append("17시 43분")
                    },
                    fontSize = 16.sp
                )
            }

            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_content_paste_search_24),
                    contentDescription = null,
                    modifier = modifier
                        .size(48.dp)
                )

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("2023-11-11")
                        }
                        append("\n")
                        append("17시 43분")
                    },
                    fontSize = 16.sp
                )
            }

        }
    }
}

@Preview
@Composable
private fun RouteViewPreview() {
    RouteView()
}