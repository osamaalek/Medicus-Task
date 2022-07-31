package com.osamaalek.medicustask.ui.biomarkerslist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osamaalek.medicustask.databinding.BiomarkerHolderBinding
import com.osamaalek.medicustask.model.Biomarker
import com.osamaalek.medicustask.model.BiomarkerUI

class BiomarkersAdapter(
    private val onClicked: OnBiomarkerItemClicked,
    private val biomarkers : MutableList<Biomarker>
) : RecyclerView.Adapter<BiomarkersAdapter.ContentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BiomarkerHolderBinding.inflate(inflater, parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.binding.biomarker= BiomarkerUI.fromEntity(biomarkers[position])
        holder.binding.card.setOnClickListener {
            onClicked.onBiomarkerItemClicked(
                BiomarkerUI.fromEntity(biomarkers[position])
            )
        }
    }

    override fun getItemCount(): Int {
        return biomarkers.size
    }

    class ContentViewHolder (val binding: BiomarkerHolderBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnBiomarkerItemClicked {
        fun onBiomarkerItemClicked(biomarker: BiomarkerUI)
    }

}


