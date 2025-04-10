package com.example.testing123

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun CircularIconButton(icon: ImageVector, onClick: () -> Unit) {
    // Circular button with specified background color
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(30.dp) // Size of the button
            .background(
                color = Color(30, 36, 41),
                shape = CircleShape
            ) // Circular shape with dark background color
            .clickable(onClick = onClick), // Handle click
        contentAlignment = Alignment.Center // Center content within the Box
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White, // Icon color
            modifier = Modifier.size(24.dp) // Icon size
        )
    }
}







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiBottomSheetApp() {
    val scope = rememberCoroutineScope()

    // States for each sheet
    val sheetState1 = rememberModalBottomSheetState()
    val sheetState2 = rememberModalBottomSheetState()
    val sheetState3 = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        scope.launch { sheetState1.show() }
    }

    Column {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Left button with "X" icon
            Box(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                CircularIconButton(
                    icon = Icons.Default.Close,
                    onClick = { /* Handle left button click */ }
                )
            }

            // Right button with "?" icon
            Box(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                CircularIconButton(
                    icon = Icons.Default.MoreVert,
                    onClick = { /* Handle right button click */ }
                )
            }
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    scope.launch { sheetState1.show() }
                }) {
                    Text("Open Sheet 1")
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                OverlappingBottomSheets(
                    sheetState1, sheetState2, sheetState3, scope
                )
            }
        }
    }
}
