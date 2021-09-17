package ru.maggy.singlealbumapp.api

import androidx.viewbinding.BuildConfig
import retrofit2.Response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.maggy.singlealbumapp.dto.Album
import ru.maggy.singlealbumapp.BuildConfig.BASE_URL

interface AlbumApi {
    @GET("album.json")
    suspend fun getAlbum(): Response<Album>

    companion object {
        private val logging = HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val okhttp = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okhttp)
            .build()

        val apiService: AlbumApi by lazy {
            retrofit.create(AlbumApi::class.java)
        }
    }
}