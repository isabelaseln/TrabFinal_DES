
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
import com.example.trabfinal_des.data.repository.DesafioRepository
import com.example.trabfinal_des.data.repository.JogadorRepository
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
        DesafioRepository(database.desafioDao())
    }

    val jogadorRepository = remember {
        JogadorRepository(database.jogadorDao())
    }

    val factory = remember {
        ViewModelFactory(desafioRepository, jogadorRepository)
    }

    val jogoViewModel: JogoViewModel = viewModel(factory = factory)
    val adminViewModel: AdminViewModel = viewModel(factory = factory)
    val jogadorViewModel: JogadorViewModel = viewModel(factory = factory)
    val perfilViewModel: PerfilViewModel = viewModel(factory = factory)

    val perfilState by perfilViewModel.uiState.collectAsState()
    val jogoState by jogoViewModel.uiState.collectAsState()
    val adminState by adminViewModel.uiState.collectAsState()
    val jogadorState by jogadorViewModel.uiState.collectAsState()

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

    val modoAdmin = perfilState.perfilSelecionado == TipoPerfil.ADMIN

    val mostrarBarra = when (perfilState.perfilSelecionado) {
        TipoPerfil.ADMIN -> rotaAtual in rotasAdmin
        TipoPerfil.JOGADOR -> rotaAtual in rotasJogador
        null -> false
    }

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
        },
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = "inicio",
                modifier = Modifier.padding(padding)
            ) {
                composable("inicio") {
                    InicioScreen(
                        onJogadorClick = {
                            perfilViewModel.selecionarPerfil(TipoPerfil.JOGADOR)
                            navController.navigate("selecionar_jogador")
                        },
                        onAdminClick = {
                            perfilViewModel.selecionarPerfil(TipoPerfil.ADMIN)
                            navController.navigate("admin")
                        }
                    )
                }

                composable("selecionar_jogador") {
                    SelecionarJogadorScreen(
                        jogadores = jogadorState.jogadores,
                        onSelecionar = { jogador ->
                            perfilViewModel.selecionarJogador(jogador)
                            navController.navigate("desafios")
                        },
                        onVoltar = {
                            perfilViewModel.limparPerfil()
                            navController.popBackStack()
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
                            perfilViewModel.limparPerfil()
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
                        onSincronizar = {
                            adminViewModel.sincronizarDesafios()
                        },
                        onVoltar = {
                            perfilViewModel.limparPerfil()
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
                            jogadorViewModel.cadastrarJogador(nome, apelido)
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
    )
}
