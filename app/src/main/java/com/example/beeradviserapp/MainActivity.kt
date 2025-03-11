package com.example.beeradviserapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.beeradviserapp.ui.theme.BeerAdviserAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeerAdviserAppTheme {
                BeerSelectionScreen()
            }
        }
    }
}

@Composable
fun BeerSelectionScreen() {
    // Mapa con tipos de cerveza y sus marcas
    val beerTypes = mapOf(
        "Light" to listOf("Bud Light", "Miller Lite", "Coors Light", "Corona Light"),
        "Dark" to listOf("Guinness", "Porter", "Stout", "Schwarzbier"),
        "Amber" to listOf("Fat Tire", "Vienna Lager", "Amber Ale", "Oktoberfest"),
        "Wheat" to listOf("Hefeweizen", "Witbier", "Dunkelweizen", "Belgian Wheat")
    )

    var selectedType by remember { mutableStateOf(beerTypes.keys.first()) }
    var selectedBrands by remember { mutableStateOf(beerTypes[selectedType] ?: emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Selecciona un tipo de cerveza:", style = MaterialTheme.typography.titleMedium)

        // Spinner (Dropdown) para seleccionar el tipo de cerveza
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.padding(vertical = 8.dp)) {
            Button(onClick = { expanded = true }) {
                Text(selectedType)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                beerTypes.keys.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedType = type
                            selectedBrands = beerTypes[type] ?: emptyList()
                            expanded = false
                        }
                    )
                }
            }
        }

        // Lista de marcas según el tipo de cerveza elegido
        Text(text = "Marcas disponibles:", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))
        selectedBrands.forEach { brand ->
            ClickableText(
                text = AnnotatedString(brand),
                onClick = { /* Acción si se desea hacer algo con la marca */ },
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BeerSelectionPreview() {
    BeerAdviserAppTheme {
        BeerSelectionScreen()
    }
}
