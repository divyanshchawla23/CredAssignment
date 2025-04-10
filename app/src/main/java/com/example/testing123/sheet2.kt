package com.example.testing123


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepaymentOptionsScreen(
    scope: CoroutineScope,
    sheetState3: SheetState
) {
    var selectedPlan by remember { mutableStateOf(0) }
    var emiText by remember { mutableStateOf("how do you wish to repay?") }
    var durationText by remember { mutableStateOf("choose one of our recommended plans or make your own") }
    var isSheetLaunched by remember { mutableStateOf(false) }

    val plans = listOf(
        RepaymentPlan(amount = "₹4,247", duration = "12 months"),
        RepaymentPlan(amount = "₹5,580", duration = "9 months", recommended = true),
        RepaymentPlan(amount = "₹8,247", duration = "6 months")
    )

    // Observe sheetState3 visibility and reset text when the sheet is hidden
    LaunchedEffect(sheetState3) {
        snapshotFlow { sheetState3.isVisible }
            .collectLatest { isVisible ->
                if (!isVisible && isSheetLaunched) {
                    // Reset to original text when Sheet 3 is closed
                    emiText = "how do you wish to repay?"
                    durationText = "choose one of our recommended plans or make your own"
                    isSheetLaunched = false
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1B2E))
            .padding(16.dp)
    ) {
        // Top text showing selected EMI and duration or default text
        if (!isSheetLaunched) {
            // Display default introductory text if the sheet hasn't launched
            Text(
                text = emiText,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = durationText,
                color = Color(0xFF8890AD),
                fontSize = 11.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            // Display selected EMI and duration in a row if the sheet is launched
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = emiText,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "/ $durationText",
                    color = Color(0xFF8890AD),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            items(plans.size) { index ->
                RepaymentPlanCard(
                    plan = plans[index],
                    selected = selectedPlan == index,
                    onSelect = { selectedPlan = index }
                )
            }
        }

        Button(
            onClick = { /* Handle custom plan creation action here */ },
            modifier = Modifier.offset(x = (0).dp, y = (-8).dp)
                .width(150.dp)
                .border(2.dp, Color.White, shape = RoundedCornerShape(100.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(26, 27, 46))
        ) {
            Text(
                text = "Create Your Own Plan",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = {
                // Update EMI and duration text with selected plan's details

                emiText = plans[selectedPlan].amount
                durationText = plans[selectedPlan].duration
                isSheetLaunched = true // Mark the sheet as launched

                // Show the third sheet
                scope.launch { sheetState3.show() }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4145A1)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(40.dp)
                .offset(x = (0).dp, y = (20).dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Select your bank account",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}



@Composable
fun RepaymentPlanCard(plan: RepaymentPlan, selected: Boolean, onSelect: () -> Unit) {
    val backgroundColor = if (selected) Color(0xFF2E1B2E) else Color(0xFF332E48)
    val borderColor = if (plan.recommended) Color(0xFFB3A1DF) else Color.Transparent

    Box(
        modifier = Modifier
            .size(130.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable { onSelect() }
            .border(2.dp, borderColor, RoundedCornerShape(12.dp))
    ) {
        // Column containing the main content of the card
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (selected) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .border(2.dp, Color.White, CircleShape)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = plan.amount + " /mo",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "for ${plan.duration}",
                color = Color(0xFF8890AD),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "See calculations",
                color = Color(0xFF4A90E2),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Position the "Recommended" label on the border with more offset
        if (plan.recommended) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-15).dp, y = (-17).dp)
                    .background(Color(0xFF332E48), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Recommended",
                    color = Color(0xFFB3A1DF),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

data class RepaymentPlan(
    val amount: String,
    val duration: String,
    val recommended: Boolean = false
)

//@Preview(showBackground = true)
//@Composable
//fun PreviewRepaymentOptionsScreen() {
//    RepaymentOptionsScreen()
//}