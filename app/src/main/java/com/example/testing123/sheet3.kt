package com.example.testing123


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Sheet3Content() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1A1B2E)) // Background color similar to the image
            .padding(16.dp)
    ) {
        // Top heading text
        Text(
            text = "where should we send the money?",
            color = Color(0xFF8890AD), // Light gray color for heading
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Secondary description text
        Text(
            text = "amount will be credited to this bank account, EMI will also be debited from this bank account",
            color = Color(0xFF657080), // Lighter color for secondary text
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )



        // Account Information Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

                .padding(16.dp)
                .offset(x = (-5).dp, y = (-30).dp)
        ) {
            // Placeholder for bank icon
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
                    .padding(8.dp)
            ) {
                // Place your bank logo image or icon here
                // For example:
                // Image(painter = painterResource(id = R.drawable.bank_icon), contentDescription = "Bank Icon")
                Text(
                    text = "Logo",
                    color = Color.Black,
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


            // Bank name and account number
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Paytm Payments Bank",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "919935670475",
                    color = Color(0xFF8890AD),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Placeholder for selected icon
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Gray, CircleShape)
            )
        }

        // "Change Account" Button
        Button(
            onClick = { /* Handle change account action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            border = BorderStroke(1.dp, Color(0xFF657080)), // Border color similar to text color
            shape = RoundedCornerShape(100.dp),

            modifier = Modifier
                .align(Alignment.Start)
                .height(40.dp)
                .width(130.dp)
                .offset(x = (13).dp, y = (-50).dp)
        ) {
            Text(
                text = "Change account",
                color = Color(0xFF657080),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold
            )
        }

        //

        Button(
            onClick = { /* Handle KYC action */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4145A1)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(70.dp)
                .offset(x = (0).dp, y = (-20).dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Tap for 1-click KYC",
                 // Light beige color for text
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // Footer button at the bottom

}

@Preview(showBackground = true, backgroundColor = 0xFF1A1B2E, widthDp = 360, heightDp = 640)
@Composable
fun PreviewSheet3Content() {
    Sheet3Content()
}
