package com.example.xvanol

import java.util.Random
import java.util.Scanner

class XAndZeroGame {
    companion object {
        private const val BOARD_SIZE = 3
        private const val PLAYER_X = 'X'
        private const val PLAYER_O = 'O'
    }

    private val board: Array<CharArray> = Array(BOARD_SIZE) { CharArray(BOARD_SIZE) }
    private var playerXTurn: Boolean = true
    private var turnsCount: Int = 0

    init {
        initializeBoard()
    }

    private fun initializeBoard() {
        for (i in 0 until BOARD_SIZE) {
            for (j in 0 until BOARD_SIZE) {
                board[i][j] = '-'
            }
        }
    }

    fun play() {
        val scanner = Scanner(System.`in`)

        while (!isGameOver()) {
            printBoard()
            if (playerXTurn) {
                println("Player X's turn.")
                getPlayerMove(scanner)
            } else {
                println("Player O's turn.")
                makeRandomMove()
            }
            playerXTurn = !playerXTurn
            turnsCount++
        }

        printBoard()
        printResult()
    }

    private fun printBoard() {
        for (i in 0 until BOARD_SIZE) {
            for (j in 0 until BOARD_SIZE) {
                print("${board[i][j]} ")
            }
            println()
        }
    }

    private fun getPlayerMove(scanner: Scanner) {
        print("Enter row (0-2): ")
        val row = scanner.nextInt()
        print("Enter column (0-2): ")
        val col = scanner.nextInt()

        if (isValidMove(row, col)) {
            board[row][col] = PLAYER_X
        } else {
            println("Invalid move. Try again.")
            getPlayerMove(scanner)
        }
    }

    private fun isValidMove(row: Int, col: Int): Boolean {
        return row in 0 until BOARD_SIZE && col in 0 until BOARD_SIZE && board[row][col] == '-'
    }

    private fun makeRandomMove() {
        val random = Random()
        var row: Int
        var col: Int
        do {
            row = random.nextInt(BOARD_SIZE)
            col = random.nextInt(BOARD_SIZE)
        } while (board[row][col] != '-')

        board[row][col] = PLAYER_O
    }

    private fun isGameOver(): Boolean {
        return checkForWin(PLAYER_X) || checkForWin(PLAYER_O) || turnsCount == BOARD_SIZE * BOARD_SIZE
    }

    private fun checkForWin(player: Char): Boolean {
        // Check rows
        for (i in 0 until BOARD_SIZE) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true
            }
        }

        // Check columns
        for (j in 0 until BOARD_SIZE) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true
        }

        return false
    }

    private fun printResult() {
        if (checkForWin(PLAYER_X)) {
            println("Player X wins!")
        } else if (checkForWin(PLAYER_O)) {
            println("Player O wins!")
        } else {
            println("It's a draw!")
        }
    }
}

fun main() {
    val game = XAndZeroGame()
    game.play()
}