package com.osamaalek.medicustask.data

import com.osamaalek.medicustask.model.BaseApiResponse
import com.osamaalek.medicustask.utils.NetworkResult
import com.osamaalek.medicustask.data.remote.RemoteDataSource
import com.osamaalek.medicustask.model.Biomarker
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class BiomarkerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getBiomarkers(): Flow<NetworkResult<List<Biomarker>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getAllBiomarkers() })
        }.flowOn(Dispatchers.IO)
    }
}