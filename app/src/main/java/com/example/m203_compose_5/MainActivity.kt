package com.example.m203_compose_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.m203_compose_5.ui.theme.M203_Compose_5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            M203_Compose_5Theme {
                CarApp()
            }
        }
    }
}

@Composable
fun CarApp() {
    // États pour les TextField, RadioButton, et Switch
    var name by remember { mutableStateOf("") }
    var days by remember { mutableStateOf("") }
    var carType by remember { mutableStateOf("") }
    var isLoyalCustomer by remember { mutableStateOf(false) }
    var totalPrice by remember { mutableStateOf(0) }

    // Interface utilisateur
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        // Premier TextField pour le nom
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom du client") },
            modifier = Modifier.fillMaxWidth()
        )

        // Deuxième TextField pour le nombre de jours
        TextField(
            value = days,
            onValueChange = { days = it },
            label = { Text("Nombre de jours de location") },
            modifier = Modifier.fillMaxWidth(),

        )

        // RadioButtons pour le type de voiture (disposés horizontalement)
        Text("Type de voiture :")
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = carType == "Économique",
                    onClick = { carType = "Économique" }
                )
                Text("Économique\n(100 dh/jour)")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = carType == "SUV",
                    onClick = { carType = "SUV" }
                )
                Text("SUV\n(150 dh/jour)")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                RadioButton(
                    selected = carType == "Luxe",
                    onClick = { carType = "Luxe" }
                )
                Text("Luxe\n(250 dh/jour)")
            }
        }

        // Switch pour Client Fidèle
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Client fidèle :")
            Switch(
                checked = isLoyalCustomer,
                onCheckedChange = { isLoyalCustomer = it }
            )
        }

        // Bouton pour calculer le prix total
        Button(
            onClick = {
                val daysInt = days.toIntOrNull() ?: 0
                var price = when (carType) {
                    "Économique" -> daysInt * 100
                    "SUV" -> daysInt * 150
                    "Luxe" -> daysInt * 250
                    else -> 0
                }
                if (isLoyalCustomer) {
                    price = (price * 0.85).toInt() // 15 % de réduction
                }
                totalPrice = price
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculer")
        }

        // Résultats
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nom du client : $name")
        Text("Prix total : $totalPrice dh")
    }
}
