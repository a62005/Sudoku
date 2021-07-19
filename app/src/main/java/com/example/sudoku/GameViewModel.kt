package com.example.sudoku

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sudoku.dataStatistics.*


class GameViewModel: ViewModel() {

    val nowGame = MutableLiveData<PlayingGame>()
    val bigFrameOnClick = MutableLiveData<View>()
    val smallFrameOnClick = MutableLiveData<View>()

    private lateinit var record :Record
    val game = MutableLiveData<Game>()
    val time = MutableLiveData<Time>()
    val score = MutableLiveData<Score>()
    val comboWin = MutableLiveData<ComboWin>()

    fun setNowGame(playingGame:PlayingGame){
        nowGame.postValue(playingGame)
    }

    fun setRecord(){
        val record = Record(
            Game(0,0,0,0),
            Time("00:00","00:00"),
            Score(0,0),
            ComboWin(0,0)
        )
        this.record = record
        setRecordDetail()
    }

    private fun setRecordDetail(){
        game.postValue(record.game)
        time.postValue(record.time)
        score.postValue(record.score)
        comboWin.postValue(record.comboWin)
    }

    fun setFrameOnclick(bigFrame:View,smallFrame: View){
        bigFrameOnClick.postValue(bigFrame)
        smallFrameOnClick.postValue(smallFrame)

    }
}