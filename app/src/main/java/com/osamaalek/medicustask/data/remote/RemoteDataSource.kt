package com.osamaalek.medicustask.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val biomarkerService: BiomarkerService){

    suspend fun getAllBiomarkers() = biomarkerService.getAllBiomarkers()

}