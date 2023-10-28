package ufam.projetodeprogramas.gerenciapessoal.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import ufam.projetodeprogramas.gerenciapessoal.R

@OptIn(ExperimentalMaterial3Api::class)
class CustomNavigationDrawerItemColors : NavigationDrawerItemColors {
    @Composable
    override fun iconColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) {
                colorResource(id = R.color.black) // Cor do ícone quando selecionado
            } else {
                colorResource(id = R.color.black) // Cor do ícone quando não selecionado
            }
        )
    }

    @Composable
    override fun textColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) {
                colorResource(id = R.color.black) // Cor do texto quando selecionado
            } else {
                colorResource(id = R.color.black) // Cor do texto quando não selecionado
            }
        )
    }

    @Composable
    override fun badgeColor(selected: Boolean): State<Color> {
        TODO("Not yet implemented")
    }

    @Composable
    override fun containerColor(selected: Boolean): State<Color> {
        return rememberUpdatedState(
            if (selected) {
                colorResource(id = R.color.gray) // Cor do fundo do item quando selecionado
            } else {
                colorResource(id = R.color.white) // Cor do fundo do item quando não selecionado
            }
        )
    }
}