package com.example.composepractise.readingbook

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composepractise.R
import com.example.composepractise.navigationdrawer.createAppBar
import com.example.composepractise.navigationdrawer.navigationDrawerModel
import com.example.composepractise.utility.showFabButton
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun showReadingBookScreen(navController: NavController,drawerState: DrawerState)
{
    val list = remember { mutableStateListOf<String>()}
    list.add("hello")
    list.add("hi")
    list.add("how")
    list.add("you")

    var progressBarIndicatorStatus = remember{ mutableStateOf(false) }
    var progressBarIndicator = remember{ mutableStateOf(0f) }
    var progressBarIndicatorTimerStatus = remember{ mutableStateOf(false) }

    launchScope(progressBarIndicatorTimerStatus,progressBarIndicator,progressBarIndicatorStatus)

    Scaffold(
        topBar = { createAppBar("Reading",drawerState) },
        floatingActionButton = {
        showFabButton(click = {
            if (progressBarIndicatorTimerStatus.value)
            {
                progressBarIndicatorTimerStatus.value = false
            }
            else{
                progressBarIndicatorTimerStatus.value = true
            }
            progressBarIndicatorStatus.value = true
           }) },
        floatingActionButtonPosition = FabPosition.End) {
        Column(modifier = Modifier.padding(it)) {
            LazyRow {
                items(list) {
                    showingListViewItem(progressBarIndicatorStatus, progressBarIndicatorTimerStatus)
                }
            }
            showProgressBar(progressBarIndicatorStatus, progressBarIndicator)
roundButtons()
            showCircularImage()
        }
    }
}

@Composable
fun showingListViewItem(progressBarIndicatorStatus:  MutableState<Boolean>,progressBarIndicator: MutableState<Boolean>)
{
    Card(elevation = CardDefaults.cardElevation(6.dp), shape = RoundedCornerShape(30.dp), modifier = Modifier.padding(10.dp).clickable { progressBarIndicatorStatus.value = false
    if (progressBarIndicator.value)
    {
        progressBarIndicator.value = true
    }
    else{
        progressBarIndicator.value = false
    }

    }) {
Column(modifier = Modifier.padding(10.dp).width(150.dp).height(180.dp)) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Image(
            modifier = Modifier.height(50.dp).width(50.dp),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "image"
        )
        Column() {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = "fav")
            Card(elevation = CardDefaults.cardElevation(5.dp), modifier = Modifier.padding(top=10.dp)) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Icon(imageVector = Icons.Default.Star, contentDescription = "star")
                    Text(text = "3.5")
                }
            }
        }
    }

    Column {
        Text(
            text = "Flutter book",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(text = "Flutter book", fontStyle = FontStyle.Italic)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Surface(
                color = Color.Yellow,
                modifier = Modifier.clip(
                    RoundedCornerShape(
                        topStartPercent = 29,
                        bottomEndPercent = 29
                    )
                )
            ) {
                Text(text = "Reading", modifier = Modifier.padding(5.dp))
            }
        }
    }
}
    }
}

@Composable
fun showProgressBar(progressBarIndicatorStatus:  MutableState<Boolean>,progressBarIndicator :MutableState<Float>) {
    if (progressBarIndicatorStatus.value) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Cyan, progress = { progressBarIndicator.value })
    }
}

suspend fun updateProgressBar(updateProgress:(Float)->Unit)
{
    for (i in 1..100) {
        updateProgress(i.toFloat() / 100)
        delay(100)
    }
}

@Composable
fun launchScope(progressBarTimer:MutableState<Boolean>,ProgressBarStatus:MutableState<Float>, progressBarIndicatorStatus: MutableState<Boolean>)
{
   LaunchedEffect(key1 = progressBarTimer.value)  {
        updateProgressBar {
            ProgressBarStatus.value = it

            if (it == 1.0f) {
                if (progressBarTimer.value && progressBarIndicatorStatus.value)
                {
                    progressBarTimer.value = false
                }
                else if (progressBarIndicatorStatus.value){
                    progressBarTimer.value = true
                }
            }
        }
    }
}


@Composable
fun showCircularImage() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.padding(20.dp).verticalScroll(scrollState).requiredHeight(1000.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = CircleShape, modifier = Modifier.padding(20.dp).verticalScroll(scrollState),
            color = Color.Blue
        ) {
            Image(
                modifier = Modifier.padding(30.dp),
                painter = painterResource(R.drawable.cloud_fog_svgrepo_com),
                contentDescription = ""
            )
        }
        Text(text ="Jyothy Labs Limited was founded in 1983 by Mr. M. P. Ramachandran in Thrissur, Kerala. JLL is renowned brand in the domestic FMCG industry.It has a diversified product portfolio spanning four major categories of fabric care, dishwashing, household insecticides (HI) and personal care and an established market position in the post-wash (under Ujala brand) and the dishwashing segments (under Pril and Exo brands). [1]\n" +
                "\n" +
                "Key Points [ edit ]\n" +
                "Business Segments\n" +
                "1) Fabric Care (44% in 9M FY25 vs 37% in FY22): [1] [2]The company offers fabric whitener, fabric enhancer, bar soap, detergent powder, and liquid detergents under its key brands Ujala & Henko. It also expanded its liquid detergent line to include the Mr. White & Morelight brands. Its fabric whitener Ujala Supreme is the leading product in the category with an 84% market share & Ujala Detergent has a market share of 22.9% in Kerala. The segment revenue grew by 6% YoY in 9M FY25. [3] [4] [5]\n" +
                "\n" +
                "2) Dishwashing (34% in 9M FY25 vs 36% in FY22): The company offers dishwash bars, liquid, gel, powder, and scrubbers under the EXO and PRIL brands. Exo dishwash bar and Pril liquid are the second-largest products in their respective categories, with a 13.7% market share. The segment revenue grew by 4% YoY in 9M FY25, driven by sustained brand investments and promotions. [6] [5]\n" +
                "\n" +
                "3) Personal Care (11% in 9M FY25 vs 12% in FY22): The company offers body soaps, hand wash, and toothpaste under the Margo brand. It is expanding its product portfolio in the mass toilet soap category by launching Jovia beauty soap in 2 variants – lemon & aloe vera and sandal & turmeric. The segment revenue grew by 1% YoY in 9M FY25. [7] [4]\n" +
                "\n" +
                "4) Household Insecticides (6% in 9M FY25 vs 12% in FY22): The company offers mosquito repellent coil, & liquid vaporizer under the Maxo brand. It has launched a new product, Maxo Anti-Mosquito Racquet. Maxo coil holds a 23.8% market share, while Maxo liquid vaporizer holds 8.3% in their respective categories. The segment revenue declined by 7% YoY in 9M FY25. [7] [5]\n" +
                "\n" +
                "5) Others (5% in 9M FY25 vs 3% in FY22): The company offers Agarbatti under the Maya brand and dry cleaning and laundry services under Fabricspa. [8]\n" +
                "\n" +
                "Distribution Network\n" +
                "The company's products are sold across 2.8 Mn outlets in India, with direct access to 1.2 Mn outlets and 9,900+ channel partners. [9]\n" +
                "\n" +
                "Distribution Mix:\n" +
                "Urban: 60%\n" +
                "Rural: 40% [10]\n" +
                "\n" +
                "Manufacturing Capabilities\n" +
                "The company operates 23 manufacturing plants across India. [9]\n" +
                "\n" +
                "Advertising & Promotion\n" +
                "The company invests in TV, OTT, digital, in-store activations, and OOH promotions, leveraging 9+ celebrity endorsements and strong social media engagement. In 9M FY25, ad spend was 8.5% of sales, up from 7% in FY22. [11] [12] [13]\n" +
                "\n" +
                "Acquisition\n" +
                "In Sept 2024, the company signed a Business Purchase Agreement with Smartwash Solutions Pvt Ltd to acquire its Quiclo brand, software, and customer database under its Laundry Service business. [14]" , modifier = Modifier.padding(10.dp))
    }
}


@Composable
fun roundButtons()
{
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Surface(color = Color.Yellow, modifier = Modifier.clip(RoundedCornerShape(topStartPercent = 30, bottomEndPercent = 30)).clickable {  }) {
            Text(text = "Click on me", color = Color.Blue, modifier = Modifier.padding(20.dp))
        }
    }
}