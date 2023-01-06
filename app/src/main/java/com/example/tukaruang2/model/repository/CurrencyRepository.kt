package com.example.tukaruang2.model.repository

import com.example.tukaruang2.model.currency.CurrencyAttribute
import com.example.tukaruang2.model.service.CurrencyService
import com.example.tukaruang2.util.ResourcesResponse
import java.lang.Exception
import javax.inject.Inject

//meberikan fungsi dagger hilt
class CurrencyRepository @Inject constructor(private val api: CurrencyService):
    ICurrencyRepository {
    override suspend fun getrates(base: String): ResourcesResponse<CurrencyAttribute> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null){
                ResourcesResponse.Success(result)
            }else{
                ResourcesResponse.Error(response.message())
            }
        }catch (e: Exception){
            ResourcesResponse.Error(e.message?:"Error")
        }
    }
}