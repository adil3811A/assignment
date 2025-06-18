@file:OptIn(ExperimentalMaterial3Api::class)

package com.inovantsolutions.assignment.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.inovantsolutions.assignment.R
import com.inovantsolutions.assignment.domain.Response
import com.inovantsolutions.assignment.presentation.theme.AssignmentTheme

@Composable
fun ResponseUi(
    response: Response,
    modifier: Modifier = Modifier
) {
    val verticalScroll = rememberScrollState()
    AssignmentTheme {
        Scaffold(
            containerColor = Color.White,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors().copy(
                        containerColor = Color.White
                    ),
                    title = {
                        Text("Ones Collection Weekly", modifier = Modifier.padding(end = 30.dp))
                    },
                    actions = {
                        Icon(painter = painterResource(R.drawable.heart), contentDescription = "Hart", tint = Color.Black)
                        Icon(painter = painterResource(R.drawable.share), contentDescription = "Share", tint = Color.Black)
                        Icon(painter = painterResource(R.drawable.bag), contentDescription = "Bag", tint = Color.Black)
                    },
                    navigationIcon = {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back", tint = Color.Black)
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // This takes all available vertical space
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(verticalScroll)
                ) {
                    PagerImage(response.data.images)
                    Spacer(Modifier.height(12.dp))
                    TitleCompose(response.data)
                    Spacer(Modifier.height(12.dp))
                    ColorCompose(response.data.images)
                    PaymentCard()
                    QuentinCompose()
                    ExpandedDescription(response.data.description)
                }

                // This stays at the bottom and takes only required height
                Buttons()
            }
        }
    }
}
