package com.amanpoonia.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amanpoonia.sensors.ui.theme.ColumnBackground
import com.amanpoonia.sensors.ui.theme.ColumnTextColor
import com.amanpoonia.sensors.ui.theme.HeadingColor
import com.amanpoonia.sensors.ui.theme.SensorsTheme
import com.amanpoonia.sensors.ui.theme.TopBackground

private lateinit var sensorManager: SensorManager
private lateinit var sensorList: List<Sensor>
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SensorsTheme {
                Scaffold(
                    topBar = {SensorTopAppBar()}
                ) { innerPadding ->
                        DisplaySensors(innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorTopAppBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = TopBackground,
            titleContentColor = HeadingColor,
        ),
        title = {
            Text(
                "Sensors"
            )
        }
    )
}

@Composable
fun ScrollBoxes(sensors: List<Sensor>, padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .background(color = ColumnBackground)

    ) {
        itemsIndexed(sensors) { index, sensor ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
                .padding(vertical = 2.dp)
                .background(
                    color = ColumnBackground
                )
            ) {
                Text(
                    "${sensor.name}",
                    color = HeadingColor,
                    fontSize = 22.sp
                )
                Text(
                    "Vendor : ${sensor.vendor}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End
                )
                Text(
                    "Version : ${sensor.version}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End
                )
                Text(
                    "Type : ${sensor.type}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End
                )
                Text(
                    "Max Range : ${sensor.maximumRange}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End

                )
                Text(
                    "Resolution : ${sensor.resolution}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End
                )
                Text(
                    "Power : ${sensor.power}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End
                )
                Text(
                    "Min Delay : ${sensor.minDelay}",
                    fontSize = 16.sp,
                    color = ColumnTextColor,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
fun DisplaySensors(padding: PaddingValues) {
    val context = LocalContext.current
    sensorManager = remember(context) { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    sensorList = remember {sensorManager.getSensorList(Sensor.TYPE_ALL) }
    ScrollBoxes(sensorList, padding)
}