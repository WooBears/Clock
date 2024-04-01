package com.example.clock.ui.alarm

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.clock.databinding.FragmentHomeBinding
import java.util.Calendar
import java.util.Locale

class AlarmFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.buttonSetAlarm.setOnClickListener {
                setAlarm()
            }
    }
    private fun setAlarm() {

            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                requireContext(),
                TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                    val timeString = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute)
                    binding.textAlarmTime.text = "Alarm Time: $timeString"
                    // Here you can set up your alarm with the selected hourOfDay and minute
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}