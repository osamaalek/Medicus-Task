package com.osamaalek.medicustask.ui.biomarkerdetails

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.osamaalek.medicustask.utils.Constants
import com.osamaalek.medicustask.R
import com.osamaalek.medicustask.databinding.ActivityBiomarkerDetailsBinding
import com.osamaalek.medicustask.model.BiomarkerUI

class BiomarkerDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiomarkerDetailsBinding
    lateinit var biomarker: BiomarkerUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.biomarker_details)

        binding = ActivityBiomarkerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.infoIcon.setOnClickListener { showAlertDialog(biomarker.info) }
    }

    private fun initData() {
        biomarker = intent.extras?.getSerializable(Constants.BIOMARKER_DETAILS) as BiomarkerUI
        binding.biomarker = biomarker
    }

    private fun showAlertDialog(text: String) {
        AlertDialog.Builder(this, R.style.AlertDialogCustom)
            .setCancelable(true)
            .setMessage(text)
            .create()
            .show()
    }

}