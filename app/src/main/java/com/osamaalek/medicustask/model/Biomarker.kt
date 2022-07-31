package com.osamaalek.medicustask.model

data class Biomarker(
    var id: Int = -1,
    var date: String = "",
    var info: String = "",
    var color: String = "#000000",
    var value: String = "",
    var symbol: String = "",
    var insight: String = "",
    var category: String = ""
)