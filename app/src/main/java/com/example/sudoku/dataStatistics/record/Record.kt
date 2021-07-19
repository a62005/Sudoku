package com.example.sudoku.dataStatistics

data class Record(
    val game: Game,
    val time: Time,
    val score: Score,
    val comboWin: ComboWin
)

data class Game(
    val playedGameTimes:Int,
    val playedWinTimes:Int,
    val winRare:Int,
    val noMistakeWinTimes:Int
)

data class Time(
    val bestTime:String,
    val averageTime:String
)

data class Score(
    val bestScore:Int,
    val averageScore:Int
)

data class ComboWin(
    val nowCombo:Int,
    val bestCombo:Int
)