package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    prayerTimes: PrayerTimesUiState,
    onRefreshPrayerTimes: () -> Unit,
    selectedDay: Int = 0,
    onSelectDay: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Shalatku", style = MaterialTheme.typography.headlineMedium)
        Text("Aplikasi Penulisan Interaktif Pertama", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))

        PrayerTimesSection(
            state = prayerTimes,
            onRefresh = onRefreshPrayerTimes
        )
    }
}

@Composable
fun PrayerCard(prayer: Prayer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(prayer.name, style = MaterialTheme.typography.titleMedium)
            Text(prayer.time, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        prayerTimes = PrayerTimesUiState.Success(samplePrayers),
        onRefreshPrayerTimes = {}
    )
}