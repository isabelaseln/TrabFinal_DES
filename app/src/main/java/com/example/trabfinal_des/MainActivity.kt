
package com.example.trabfinal_des

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.trabfinal_des.ui.theme.TrabFinal_DESTheme
import com.example.trabfinal_des.ui.theme.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrabFinal_DESTheme {
                Surface(
                    modifier = Modifier
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
