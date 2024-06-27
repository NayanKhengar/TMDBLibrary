package com.nayan.tmdbmoviesdemo.data.model

import com.google.gson.annotations.SerializedName


data class MovieResponse(
    val results: List<Movie>
)
data class MovieRecommendationsResponse(
    val results: List<Movie>
)
data class MovieKeywordsResponse(
    val keywords: List<KeyValue>
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val adult: Boolean?,
    val backdrop_path: String?,
    val vote_average: Double?,
    val budget: Long?,
    val revenue: Long?,
    val status: String?,
    val tagline: String?,
    val genres: List<KeyValue>?
)

data class KeyValue(
    val id: Int,
    val name: String?
)


data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String?,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)

data class MovieDetailsResponse(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Any?,  // Assuming this can be null or an object. Adjust as needed.
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: List<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Long,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class MovieImagesResponse(
    @SerializedName("backdrops") val backdrops: List<Backdrop>,
    @SerializedName("id") val id: Int,
    @SerializedName("logos") val logos: List<Logo>
)

data class Backdrop(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("file_path") val filePath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("width") val width: Int
)

data class Logo(
    @SerializedName("aspect_ratio") val aspectRatio: Double,
    @SerializedName("height") val height: Int,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("file_path") val filePath: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("width") val width: Int
)


data class MovieCastCrewResponse(
    val id: Int,
    val cast: List<CastCrew>,
    val crew: List<CastCrew>
)

data class CastCrew(
    val adult: Boolean,
    val gender: Int?,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val order: Int,
    val job: String
)
