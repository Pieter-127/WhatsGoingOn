package com.pieterventer.whatsgoingon.data.repository.resource

import androidx.annotation.StringRes
import com.pieterventer.whatsgoingon.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber


sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val errorMessage: String? = null,
        @StringRes val errorRes: Int? = null,
        val code: Int? = null,
        val error: HttpException? = null
    ) :
        Resource<Nothing>()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Resource<T> {
    return withContext(Dispatchers.Main) {
        try {
            withContext(Dispatchers.IO) {
                Resource.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            Timber.e(throwable)
            Resource.Failure(throwable.message, R.string.default_error)
        }
    }
}