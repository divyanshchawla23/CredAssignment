import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testing123.CircularSlider


@Composable
fun CreditAmountScreen(
    onProceedClick: () -> Unit,
    resetState: Boolean = false  // New parameter to control resetting
) {
    var isProceedClicked by remember { mutableStateOf(false) }
    var creditAmount by remember { mutableStateOf(150000) }
    val maxAmount = 487891

    // Check if we need to reset the state on navigation back
    if (resetState) {
        isProceedClicked = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(19, 25, 34))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = if (isProceedClicked) "Credit Amount" else "Nikunj, how much do you need?",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                color = Color(145, 166, 174)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text =  if (isProceedClicked) "₹$creditAmount" else "Move the slider to set any amount you need up to ₹ $maxAmount",
                fontSize =  if (isProceedClicked) 12.sp else 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            // White Box containing the circular slider
            Card(
                modifier = Modifier.padding(16.dp).offset(x = (38).dp, y = (0).dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                CircularSlider(
                    initialAmount = creditAmount,
                    maxAmount = maxAmount

                )
            }

            // Button to trigger the next screen
            Button(
                onClick = {
                    isProceedClicked = true // Update the text on button click
                    onProceedClick()       // Trigger the next sheet
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4145A1)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Proceed to EMI selection", color = Color.White)
            }
        }
    }
}
