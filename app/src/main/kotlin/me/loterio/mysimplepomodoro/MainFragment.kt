package me.loterio.mysimplepomodoro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import me.loterio.mysimplepomodoro.databinding.MainFragmentBinding
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

        with(viewBinding.spnrTime) {
            adapter = TimerSpinnerAdapter()
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (position == 0) return
                    val selectedItem = parent?.getItemAtPosition(position)
                    counter = selectedItem.toString().toInt()
                    viewBinding.tvText.text = selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewBinding.tvText.text = "Nothing selected"
                }
            }
        }

    }

    private fun createTimerTask() = object : TimerTask() {
        override fun run() {
            counter--
            updateCounterOnScreen()
        }
    }

    private fun onClickStartTimer() {
        viewBinding.btnStart.visibility = View.GONE
        viewBinding.btnStop.visibility = View.VISIBLE
        viewBinding.spnrTime.visibility = View.GONE
        viewBinding.tvText.visibility = View.VISIBLE
        scheduledTimer = executorService.scheduleAtFixedRate(timerTask, 0,1, TimeUnit.SECONDS);
    }

    private fun onClickStopTimer() {
        viewBinding.btnStop.visibility = View.GONE
        viewBinding.btnStart.visibility = View.VISIBLE
        viewBinding.spnrTime.visibility = View.VISIBLE
        viewBinding.tvText.visibility = View.GONE
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