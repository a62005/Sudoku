package com.example.sudoku

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sudoku.dataStatistics.*
import com.example.sudoku.databinding.ItemSmallFrameNumberBinding
import com.example.sudoku.homePage.game.NORMAL
import com.example.sudoku.homePage.game.NO_NUMBER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class GameViewModel: ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    val nowGame = MutableLiveData<PlayingGame>()
    val bigFrameOnClick = MutableLiveData<View>()
    val smallFrameOnClick = MutableLiveData<View>()
    private var binding: ItemSmallFrameNumberBinding? = null

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
        scope.launch {
            game.postValue(record.game)
            time.postValue(record.time)
            score.postValue(record.score)
            comboWin.postValue(record.comboWin)
        }
    }

    fun setFrameOnclick(bigFrame:View,smallFrame: View,binding: ItemSmallFrameNumberBinding){
        bigFrameOnClick.postValue(bigFrame)
        smallFrameOnClick.postValue(smallFrame)
        this.binding = binding
    }

    fun setNumber(number:Int){
        scope.launch {
            if(binding == null)return@launch
            binding!!.number.text = number.toString()
            val s = smallFrameOnClick.value!!
            val text = s.findViewById<TextView>(R.id.number)
            text.visibility = View.VISIBLE
        }
    }

    fun clear(){
        scope.launch {
            if(binding == null) return@launch
            val s = smallFrameOnClick.value!!
            val text = s.findViewById<TextView>(R.id.number)
            text.visibility = View.INVISIBLE
        }
    }
}