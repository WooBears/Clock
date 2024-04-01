package com.example.clock.ui.stopwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.clock.databinding.FragmentDashboardBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopwatchFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!

    private var  isRunning =  false
    private var timeSeconds = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnStart.setOnClickListener {
            startTime()
        }

        binding.btnStop.setOnClickListener {
            stopTimer()
        }

        binding.btnReset.setOnClickListener {
            resetTimer()
        }

    }

    private fun startTime() {
        if (!isRunning) {
            isRunning = true
            binding.btnStart.isEnabled = false
            binding.btnStop.isEnabled = true
            binding.btnReset.isEnabled = true

            // Start updating time
            lifecycleScope.launch(Dispatchers.IO) {
                while (isRunning) {
                    delay(1000)
                    timeSeconds++
                    updateTime()
                }
            }
        }
    }

    private fun stopTimer(){
        if(isRunning){
            isRunning = false
            binding.btnStart.isEnabled = true
            binding.btnStart.text = "Resume"
            binding.btnStop.isEnabled = false
            binding.btnReset.isEnabled = true
        }
    }

    private fun resetTimer(){

        stopTimer()

        binding.tvStopwatch.text = "00:00:00"
        timeSeconds = 0

        binding.btnStart.isEnabled = true
        binding.btnStart.text = "Start"
        binding.btnReset.isEnabled = false
        binding.btnStop.isEnabled = false

    }
    private fun updateTime() {
        val hours = timeSeconds / 3600
        val minutes = (timeSeconds % 3600) / 60
        val seconds = timeSeconds % 60

        val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        lifecycleScope.launch (Dispatchers.Main){
            binding.tvStopwatch.text = time
        }
    }
}
