package com.osamaalek.medicustask.ui.biomarkerslist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.osamaalek.medicustask.*
import com.osamaalek.medicustask.databinding.ActivityBiomarkersListBinding
import com.osamaalek.medicustask.model.Biomarker
import com.osamaalek.medicustask.model.BiomarkerUI
import com.osamaalek.medicustask.ui.biomarkerdetails.BiomarkerDetailsActivity
import com.osamaalek.medicustask.utils.Constants
import com.osamaalek.medicustask.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BiomarkersListActivity : AppCompatActivity(), BiomarkersAdapter.OnBiomarkerItemClicked {

    private lateinit var binding: ActivityBiomarkersListBinding
    private val viewModel: BiomarkersViewModel by viewModels()
    private val biomarkers = mutableListOf<Biomarker>()

    private val adapter = BiomarkersAdapter(this, biomarkers)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.report_list_title)

        binding = ActivityBiomarkersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        fetchData()
    }

    private fun initView() {
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        binding.viewModel=viewModel
        binding.recyclerViewBiomarkers.adapter = adapter
    }

    private fun fetchData() {
        viewModel.fetchBiomarkersResponse()
        viewModel.response.observe(this) { response ->

            when (response) {
                is NetworkResult.Success -> {
                    viewModel.response.value?.data?.let {
                        if(!response.data.isNullOrEmpty()){
                            viewModel.isEmpty.postValue(false)
                            biomarkers.clear()
                            biomarkers.addAll(response.data!!)
                            adapter.notifyDataSetChanged()
                            binding.recyclerViewBiomarkers.scheduleLayoutAnimation()
                        }
                        else {
                            biomarkers.clear()
                            adapter.notifyDataSetChanged()
                            binding.imageViewBiomarkers.setImageResource(R.drawable.ic_empty)
                            viewModel.isEmpty.postValue(true)
                        }
                    }
                }
                is NetworkResult.Error -> {
                    if (!biomarkers.isNullOrEmpty())
                        Toast.makeText(this,getString(R.string.check_your_internet),Toast.LENGTH_SHORT).show()
                    else {
                        binding.imageViewBiomarkers.setImageResource(R.drawable.ic_no_connection)
                        viewModel.isEmpty.postValue(true)
                    }
                }
            }
        }
    }

    override fun onBiomarkerItemClicked(biomarker: BiomarkerUI) {
        Intent(this, BiomarkerDetailsActivity::class.java)
            .apply {
                putExtra(Constants.BIOMARKER_DETAILS, biomarker)
                startActivity(this)
            }
    }

}


