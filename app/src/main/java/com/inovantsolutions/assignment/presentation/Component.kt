package com.inovantsolutions.assignment.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.inovantsolutions.assignment.R
import com.inovantsolutions.assignment.domain.Data

@Composable
fun PagerImage(
    images:List<String>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState { images.size }
    HorizontalPager(state = pagerState) {page->
        Column {
            AsyncImage(
                images[page],
                contentDescription = "Product image",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
    Spacer(Modifier.height(20.dp))
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.Black else colorResource(R.color.skinColor)
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}


@Composable
fun TitleCompose(
    data: Data,
    modifier: Modifier = Modifier
) {
    val str = data.final_price
    val double = str.toDoubleOrNull() ?: 0.0
    val amount = String.format("%.2f", double).toDouble()
    Column (
        Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){
        Row (
            Modifier
                .fillMaxWidth()
                .padding()
        ){
            Text(
                data.brand_name.uppercase(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )
            Text(

                "$amount KWD",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }
        Text(data.name, Modifier.padding(bottom = 12.dp))
        Text("SKU"+data.sku, Modifier.padding(bottom = 12.dp))
    }
}

@Composable
fun ColorCompose(
    images:List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        Modifier.padding(12.dp)
    ) {
        Text("Colors:")
        Spacer(Modifier.height(12.dp))
        LazyRow{
            itemsIndexed(images){index , image->
                if (index==0 || index%2==0){
                    AsyncImage(
                        image,
                        contentDescription = "eyes image",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(70.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp, color = colorResource(R.color.brown), CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(R.color.lightBlack),
                shape = RoundedCornerShape(3.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            buildAnnotatedString {
                append("or 4 interest-free-payments\n")
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold
                    )
                ){
                    append("0.88 KWD ")
                }
                withStyle(
                    style = SpanStyle(
                        textDecoration = TextDecoration.Underline
                    )
                ){
                    append("Learn more")
                }
            },
            modifier = Modifier.weight(1f)
        )
        Card (
            colors = CardDefaults.cardColors().copy(
                containerColor = colorResource(R.color.tiffany),
                contentColor = Color.Black
            )
        ){
            Text(
                "taddy",
                style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}

@Composable
fun QuentinCompose(
    modifier: Modifier = Modifier
) {
    val sate = remember { mutableStateOf<Int>(0) }
    Column(
        Modifier.padding(12.dp)
    ) {
        Text("Quantity")
        Spacer(Modifier.height(12.dp))
        Row {
            Box(
                Modifier
                    .size(50.dp)
                    .background(colorResource(R.color.gray))
                    .clickable {
                        if (sate.value > 0) sate.value -= 1
                    },
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    painterResource(R.drawable.remove),
                    contentDescription = "descreas", tint = Color.White,
                    modifier = Modifier.size(12.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            Box(
                Modifier
                    .size(height = 50.dp, width = 70.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center,
            ){
                Text("${sate.value}", color = Color.Black)
            }
            Spacer(Modifier.width(12.dp))
            Box(
                Modifier
                    .size(50.dp)
                    .background(Color.Black)
                    .clickable { sate.value += 1 },
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    Icons.Default.Add,
                    contentDescription = "add", tint = Color.White,
                    modifier = Modifier.size(12.dp)
                    )
            }
        }
    }
}

@Composable
fun ExpandedDescription(
    description:String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
        ){
            Text(
                "PRODUCT INFORMATION",
                Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp),
                style = TextStyle(fontSize = 28.sp , fontWeight = FontWeight.Bold)
            )
              Icon(if(isExpanded)Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowUp, contentDescription = "Expanded")
        }
        AnimatedVisibility(isExpanded) {
            Text(
                AnnotatedString.fromHtml(
                    description.trimIndent()
                )
            )
        }
    }
}

@Composable
fun Buttons(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White),
    ) {
        Spacer(Modifier.height(1.dp).fillMaxWidth().background(Color.Black))
        Spacer(Modifier.height(12.dp))
        Box(
            modifier.fillMaxWidth()
                .padding(12.dp)
                .background(Color.Black),
            Alignment.Center
        ){
            Text(
                "Add to bag",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(12.dp),
                color = Color.White)
        }
        Box(
            modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 12.dp)
                .border(border = BorderStroke(1.dp , Color.Black)),
            Alignment.Center
        ){
            Text(
                "Share",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold), modifier = Modifier.padding(12.dp),
                color = Color.Black
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PreviewCompose() {
    Buttons()
}