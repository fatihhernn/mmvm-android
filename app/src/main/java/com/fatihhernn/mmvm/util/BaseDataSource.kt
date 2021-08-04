package com.fatihhernn.mmvm.util

import retrofit2.Response
import java.lang.Exception

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call : suspend ()-> Response<T>):Resource<T>{
        try {
            val response=call()
            if (response.isSuccessful){
                val body=response.body()
                if (body!=null)
                    return Resource.success(body)
            }
            return error("${response.code()} - ${response.message()}")
        }
        catch (e: Exception){
            return error("${e.localizedMessage} - ${e.message}")
        }
    }
    private fun <T> error(message:String):Resource<T>{
        return Resource.error("Network error : $message")
    }
}