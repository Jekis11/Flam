package com.example.flam

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.flam.databinding.ActivityRateUsBinding
import kotlinx.android.synthetic.main.activity_rate_us.*

class RateUs : AppCompatActivity() {
        // use getter to get non-null value for binding variable
        private var _binding: ActivityRateUsBinding? = null
        private val binding get() = _binding!!

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            _binding = ActivityRateUsBinding.inflate(layoutInflater)
            setContentView(binding.root)

            captureRating()
        }

        @SuppressLint("SetTextI18n") // ignore concatenation warning
        private fun captureRating() {
            var userRating = 0F // initial value

            // use _ for unused parameters in this lambda function
            binding.ratingBar.apply {
                setOnRatingBarChangeListener { _, rating, _ ->
                    userRating = rating.also {
                        binding.tvRating.text = "${it.toInt()}/5"
                    }
                }
            }


            binding.btnSend.setOnClickListener {
                if (binding.etRating.text!!.isNotEmpty()) {
                    Toast.makeText(this, "Rating ($userRating/5.0) sent to Oigen", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    binding.etRating.error = "can't be blank"
                }
            }
        }
    }
