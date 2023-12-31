package me.loterio.mysimplepomodoro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.loterio.mysimplepomodoro.databinding.MainFragmentBinding
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit


class MainFragment: Fragment() {

    private var scheduledTimer: ScheduledFuture<*>? = null
    private var counter = 0
    private val timerTask = createTimerTask()
    private var executorService = Executors.newScheduledThreadPool(1)
    private lateinit var viewBinding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MainFragmentBinding.inflate(inflater, container, false).also {
            viewBinding = it
            return it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnStart.setOnClickListener { onClickStartTimer() }
        viewBinding.btnStop.setOnClickListener { onClickStopTimer() }
    }

    private fun createTimerTask() = object : TimerTask() {
        override fun run() {
            counter++
            updateCounterOnScreen()
        }
    }

    private fun onClickStartTimer() {
        viewBinding.btnStart.visibility = View.GONE
        viewBinding.btnStop.visibility = View.VISIBLE
        counter = 0
        scheduledTimer = executorService.scheduleAtFixedRate(timerTask, 0,1, TimeUnit.SECONDS);
    }

    private fun onClickStopTimer() {
        viewBinding.btnStop.visibility = View.GONE
        viewBinding.btnStart.visibility = View.VISIBLE
        counter = 0
        scheduledTimer?.cancel(true)
        updateCounterOnScreen()
    }

    private fun updateCounterOnScreen() {
        runOnUiThread {
            viewBinding.tvText.text = counter.toString()
        }
    }

    private fun runOnUiThread(function: () -> Unit) {
        activity?.runOnUiThread { function() }
    }

}