package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrayerTimesSection(
    state: PrayerTimesUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        when (state) {
            is PrayerTimesUiState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(Modifier.height(8.dp))
                    Text("Memuat jadwal shalat...", style = MaterialTheme.typography.bodyMedium)
                }
            }
            is PrayerTimesUiState.Success -> {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Jadwal Hari Ini", style = MaterialTheme.typography.titleMedium)
                        OutlinedButton(onClick = onRefresh) {
                            Text("Refresh")
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    state.times.forEach { prayer ->
                        PrayerCard(prayer = prayer)
                    }
                }
            }
            is PrayerTimesUiState.Error -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = onRefresh) {
                        Text("Coba Lagi")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionLoadingPreview() {
    PrayerTimesSection(state = PrayerTimesUiState.Loading, onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionSuccessPreview() {
    PrayerTimesSection(state = PrayerTimesUiState.Success(samplePrayers), onRefresh = {})
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionErrorPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Error("Gagal mengambil data jadwal shalat."),
        onRefresh = {}
    )
}