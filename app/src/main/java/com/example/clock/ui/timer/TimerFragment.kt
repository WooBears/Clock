package com.example.clock.ui.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.clock.databinding.FragmentNotificationsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimerFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var countdownTimer: CountDownTimer
    private val totalTime: Long = 100000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvStopwatch.setOnClickListener {
            startTimer()
        }
    }

    private fun startTimer() {
            countdownTimer = object : CountDownTimer(totalTime, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val timeLeft = millisUntilFinished / 1000 // converting milliseconds to seconds
                    updateCountdownUI(timeLeft)
                }

                override fun onFinish() {

                }
            }.start()
    }

    private fun updateCountdownUI(timeLeft: Long) {
        val hours = timeLeft / 3600
        val minutes = (timeLeft % 3600) / 60
        val seconds = timeLeft % 60

        val timeLeftFormatted = String.format("%02d:%02d:%02d", hours ,minutes, seconds)
        lifecycleScope.launch (Dispatchers.Main){
            binding.tvStopwatch.text = timeLeftFormatted
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}