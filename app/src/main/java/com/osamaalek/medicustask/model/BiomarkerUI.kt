package com.osamaalek.medicustask.model

import android.graphics.Color
import java.io.Serializable

class BiomarkerUI(
    val id: Int,
    val date: String,
    val info: String,
    val color: Int,
    val value: String,
    val symbol: String,
    val insight: String,
    val category: String
) : Serializable {

    companion object {
        fun fromEntity(entity: Biomarker): BiomarkerUI {
            return BiomarkerUI(
                entity.id,
                entity.date,
                entity.info,
                Color.parseColor(entity.color),
                entity.value,
                entity.symbol,
                entity.insight,
                entity.category
            )
        }
    }
}