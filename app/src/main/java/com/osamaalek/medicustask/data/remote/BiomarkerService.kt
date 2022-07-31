package com.osamaalek.medicustask.data.remote

import com.osamaalek.medicustask.model.Biomarker
import retrofit2.Response
import retrofit2.http.GET

interface BiomarkerService {

    @GET("biomarkers")
    suspend fun getAllBiomarkers() : Response<List<Biomarker>>

}
