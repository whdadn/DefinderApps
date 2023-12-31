package com.dicoding.definderapps.data.remote.retrofit

import com.dicoding.definderapps.data.remote.response.createreview.CreateReviewResponse
import com.dicoding.definderapps.data.remote.response.login.LoginResponse
import com.dicoding.definderapps.data.remote.response.place.PlaceResponse
import com.dicoding.definderapps.data.remote.response.register.RegisterResponse
import com.dicoding.definderapps.data.remote.response.detail.DetailResponse
import com.dicoding.definderapps.data.remote.response.detailtransport.DetailTransportResponse
import com.dicoding.definderapps.data.remote.response.favorite.FavoriteResponse
import com.dicoding.definderapps.data.remote.response.favorite.GetFavoriteResponse
import com.dicoding.definderapps.data.remote.response.mbti.MbtiDescResponse
import com.dicoding.definderapps.data.remote.response.mbti.MbtiResponse
import com.dicoding.definderapps.data.remote.response.profile.EditProfileResponse
import com.dicoding.definderapps.data.remote.response.profile.GetUserResponse
import com.dicoding.definderapps.data.remote.response.review.GetReviewResponse
import com.dicoding.definderapps.data.remote.response.transport.TransportResponse
import com.dicoding.definderapps.data.remote.response.typetransport.TypeTransportResponse
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse


    @GET("place")
    suspend fun getPlaceHome(
        @Header("Authorization") token: String,
        @Query("daerah") daerah: String,
        @Query("object") obj: String,
        @Query("mbti") mbti: String,
    ): PlaceResponse

    @GET("place/about/{id}")
    suspend fun getDetailPlace(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): DetailResponse

    @GET("place/search")
    suspend fun getPlaceByName(
        @Header("Authorization") token: String,
        @Query("name") name: String,
    ): PlaceResponse

    @FormUrlEncoded
    @POST("transportation/{placeId}")
    suspend fun addTransport(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
        @Field("userId") userId: Int,
        @Field("type") type: String,
        @Field("name") name: String,
        @Field("description") description: String,
    ): TransportResponse

    @GET("place/transport/{placeId}")
    suspend fun getTransport(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
    ): TypeTransportResponse

    @GET("transportation/{placeId}/type")
    suspend fun getDetailTransport(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
        @Query("type") type: String,
    ): DetailTransportResponse

    @GET("user/detail/{userId}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
    ): GetUserResponse

    @FormUrlEncoded
    @PUT("user/{userId}")
    suspend fun editAccount(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
        @Field("name") name: String,
        @Field("email") email: String,
    ): EditProfileResponse

    @FormUrlEncoded
    @PUT("user/update-password/{userId}")
    suspend fun editPassword(
        @Header("Authorization") token: String,
        @Path("userId") userId: Int,
        @Field("oldPassword") oldPassword: String,
        @Field("newPassword") newPassword: String,
    ): EditProfileResponse

    @POST("user-favorite-place/{placeId}")
    suspend fun createFavorite(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
    ): FavoriteResponse


    @GET("user-favorite-place")
    suspend fun getFavorite(
        @Header("Authorization") token: String,
    ): GetFavoriteResponse

    @DELETE("user-favorite-place/{placeId}")
    suspend fun deleteFavorite(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
    ): FavoriteResponse

    @GET("mbti")
    suspend fun getMbtiDesc(
        @Header("Authorization") token: String,
    ): MbtiDescResponse

    @GET("place/review/{placeId}")
    suspend fun getReview(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
    ):GetReviewResponse

    @FormUrlEncoded
    @POST("review/{placeId}")
    suspend fun createReview(
        @Header("Authorization") token: String,
        @Path("placeId") placeId: Int,
        @Field("review") review:String,
        @Field("rating") rating:String
    ): CreateReviewResponse
}

interface ApiServiceMbti {
    @GET("predict")
    suspend fun getMbti(
        @Query("parameters") parameters: String,
    ): MbtiResponse
}
