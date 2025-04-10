package com.example.testing123

import CreditAmountScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the background color for the app
            AppBackground()
        }
    }

    @Composable
    fun AppBackground() {
        // Background color (14, 21, 27)
        val backgroundColor = Color(14, 21, 27)

        // Using a Box to set the background color
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            // Replace with your Composable
            MultiBottomSheetApp()
        }
    }
}



// blue if commited file is changed



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverlappingBottomSheets(
    sheetState1: SheetState,
    sheetState2: SheetState,
    sheetState3: SheetState,
    scope: CoroutineScope
) {
    var resetState by remember { mutableStateOf(false) }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    // Define heights for each sheet to achieve the layered effect
    val sheet1Height = screenHeight * 1f
    val sheet2Height = screenHeight * 0.44f
    val sheet3Height = screenHeight * 0.37f

    LaunchedEffect(sheetState1.isVisible) {
        if (sheetState1.isVisible) {
            // Toggle resetState with a delay to ensure recomposition
            resetState = true
            delay(100) // Short delay to allow recomposition
            resetState = false
        }
    }

    Box {
        // Sheet 1 - Entry point, visible first
        if (sheetState1.isVisible) {
            ModalBottomSheet(
                onDismissRequest = { /* Keep it open */ },
                sheetState = sheetState1,
                dragHandle = null,
                modifier = Modifier.height(sheet1Height)
            ) {
                CreditAmountScreen(
                    onProceedClick = {
                        scope.launch {
                            sheetState2.show()
                        }
                    },
                    resetState = resetState
                )
            }
        }

        // Sheet 2 - Shows after Sheet 1, opens Sheet 3
        if (sheetState2.isVisible) {
            ModalBottomSheet(
                onDismissRequest = {
                    scope.launch {
                        sheetState2.hide()
                        sheetState1.show()
                    }
                },
                sheetState = sheetState2,
                dragHandle = null,
                modifier = Modifier.height(sheet2Height)
            ) {
                RepaymentOptionsScreen(scope, sheetState3)
            }
        }

        // Sheet 3 - Final layer
        if (sheetState3.isVisible) {
            ModalBottomSheet(
                onDismissRequest = { scope.launch { sheetState3.hide() } },
                sheetState = sheetState3,
                dragHandle = null,
                modifier = Modifier.height(sheet3Height)
            ) {
                Sheet3Content()
            }
        }
    }
}


//@Composable
//fun SheetContent(title: String, color: Color, onClick: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .background(color),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = title, style = MaterialTheme.typography.headlineMedium)
//        Spacer(modifier = Modifier.height(8.dp))
//        Button(onClick = onClick) {
//            Text("Open Next")
//        }
//    }
//}






