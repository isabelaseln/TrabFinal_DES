package com.example.trabfinal_des.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.room.Room
import com.example.trabfinal_des.data.local.AppDatabase
import com.example.trabfinal_des.data.local.JogadorEntity
import com.example.trabfinal_des.data.remote.DesafioRemote
import com.example.trabfinal_des.data.repository.DesafioRepository
import com.example.trabfinal_des.data.repository.JogadorRepository

import com.example.trabfinal_des.ui.theme.navigation.BottomNavigationBar
import com.example.trabfinal_des.ui.theme.screens.*

import com.example.trabfinal_des.viewmodel.*

@Composable
fun AppNavigation() {

    val contexto = LocalContext.current
    val navController = rememberNavController()

    val database = remember {
        Room.databaseBuilder(
            contexto.applicationContext,
            AppDatabase::class.java,
            "acerte_numero_db"
        ).build()
    }

    val desafioRepository = remember {
        DesafioRepository(
            database.desafioDao(),
            DesafioRemoteService()
        )
    }

    val jogadorRepository = remember {
        JogadorRepository(
            database.jogadorDao()
        )
    }

    val factory = remember {
        ViewModelFactory(
            desafioRepository,
            jogadorRepository
        )
    }

    val jogoViewModel: JogoViewModel =
        viewModel(factory = factory)

    val adminViewModel: AdminViewModel =
        viewModel(factory = factory)

    val jogadorViewModel: JogadorViewModel =
        viewModel(factory = factory)

    val jogoState by jogoViewModel.uiState.collectAsState()
    val adminState by adminViewModel.uiState.collectAsState()
    val jogadorState by jogadorViewModel.uiState.collectAsState()

    var jogadorSelecionado by remember {
        mutableStateOf<JogadorEntity?>(null)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = backStackEntry?.destination?.route

    val rotasAdmin = setOf(
        "admin",
        "admin_desafios",
        "admin_jogadores"
    )

    val rotasJogador = setOf(
        "inicio",
        "selecionar_jogador",
        "desafios"
    )

    val modoAdmin = rotaAtual in rotasAdmin

    val mostrarBarra =
        rotaAtual in rotasAdmin || rotaAtual in rotasJogador

    Scaffold(
        bottomBar = {

            if (mostrarBarra) {

                BottomNavigationBar(
                    rotaAtual = rotaAtual,
                    modoAdmin = modoAdmin,
                    onNavegar = { destino ->

                        navController.navigate(destino) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(padding)
        ) {

            composable("inicio") {

                InicioScreen(
                    onJogadorClick = {
                        navController.navigate("selecionar_jogador")
                    },
                    onAdminClick = {
                        navController.navigate("admin")
                    }
                )
            }

            composable("selecionar_jogador") {

                SelecionarJogadorScreen(
                    jogadores = jogadorState.jogadores,

                    onSelecionar = { jogador ->

                        jogadorSelecionado = jogador

                        navController.navigate("desafios")
                    },

                    onVoltar = {
                        navController.popBackStack()
                    },

                    onGerenciarJogadores = {
                        navController.navigate("admin_jogadores")
                    }
                )
            }

            composable("desafios") {

                DesafiosScreen(
                    desafios = jogoState.desafios,

                    onSelecionar = { desafio ->

                        jogoViewModel.iniciarPartida(desafio)

                        navController.navigate("jogo")
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable("jogo") {

                JogoScreen(
                    uiState = jogoState,

                    onPalpiteChange = {
                        jogoViewModel.atualizarPalpite(it)
                    },

                    onEnviarPalpite = {
                        jogoViewModel.enviarPalpite()
                    },

                    onVoltar = {
                        jogoViewModel.limparPartida()
                        navController.popBackStack()
                    },

                    onVerResultado = {
                        navController.navigate("resultado")
                    }
                )
            }

            composable("resultado") {

                ResultadoScreen(
                    resultado = jogoState.resultado,

                    onVoltarDesafios = {
                        jogoViewModel.limparPartida()

                        navController.navigate("desafios") {
                            popUpTo("desafios") {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },

                    onVoltarInicio = {
                        jogoViewModel.limparPartida()

                        navController.navigate("inicio") {
                            popUpTo("inicio") {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable("admin") {

                AdminScreen(
                    uiState = adminState,

                    onGerenciarDesafios = {
                        navController.navigate("admin_desafios")
                    },

                    onGerenciarJogadores = {
                        navController.navigate("admin_jogadores")
                    },

                    onSincronizar = {
                        adminViewModel.sincronizarDesafios()
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable("admin_desafios") {

                GerenciarDesafiosScreen(
                    uiState = adminState,

                    onCadastrar = { titulo, minimo, maximo, secreto, dificuldade ->

                        adminViewModel.cadastrarDesafio(
                            titulo,
                            minimo,
                            maximo,
                            secreto,
                            dificuldade
                        )
                    },

                    onAtualizar = { desafio ->
                        adminViewModel.atualizarDesafio(desafio)
                    },

                    onExcluir = { id ->
                        adminViewModel.excluirDesafio(id)
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }

            composable("admin_jogadores") {

                GerenciarJogadoresScreen(
                    uiState = jogadorState,

                    onCadastrar = { nome, apelido ->
                        jogadorViewModel.cadastrarJogador(
                            nome,
                            apelido
                        )
                    },

                    onAtualizar = { jogador ->
                        jogadorViewModel.atualizarJogador(jogador)
                    },

                    onExcluir = { id ->
                        jogadorViewModel.excluirJogador(id)
                    },

                    onVoltar = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}