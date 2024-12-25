package com.filnm.nearby.core.network

import com.filnm.nearby.data.model.Coupons
import com.filnm.nearby.data.model.Market
import com.filnm.nearby.data.model.Marketdetails
import com.filnm.nearby.data.model.NearbyCategory
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object RemoteDataSource { //EndPoints

    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.2.2:33333"

    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    //1#Buscar categorias
    suspend fun getCategories(): Result<List<NearbyCategory>> =
        try { // Faz a requisição para o servidor
            val categories = KtorHttpClient.httpClientAndroid.get("$BASE_URL/categories")
                .body<List<NearbyCategory>>()

            Result.success(categories)

        } catch (e: Exception) {
            Result.failure(e)
        }

    //2#Buscar de locais (com base em uma categoria)
    suspend fun getMarkets(categoryId: String): Result<List<Market>> = try {
        val markets =
            KtorHttpClient.httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}")
                .body<List<Market>>()

        Result.success(markets)
    } catch (e: Exception) {
        Result.failure(e)

    }

    //3#Buscar de detalhes de um local (com base em um local especifico)
    suspend fun getMarketDetails(marketId: String): Result<Marketdetails> = try {
        val marketDetails = KtorHttpClient.httpClientAndroid
            .get("$BASE_URL/markets/${marketId}").body<Marketdetails>()

        Result.success(marketDetails)
    } catch (e: Exception) {
        Result.failure(e)
    }


    //4#Gerar cupom a partir da leitura do qrcode
    suspend fun patchCoupon(marketId: String): Result<Coupons> = try {
        val coupon = KtorHttpClient.httpClientAndroid
            .patch("$BASE_URL/coupons/${marketId}").body<Coupons>()

        Result.success(coupon)
    } catch (e: Exception) {
        Result.failure(e)
    }

}

