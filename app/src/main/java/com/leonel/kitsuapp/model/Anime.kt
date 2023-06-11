package com.leonel.kitsuapp.model

import com.google.gson.annotations.SerializedName


data class Anime(
    val id: String,
    val type: TypeEnum,
    val links: DatumLinks,
    val attributes: Attributes,
    val relationships: Map<String, Relationship>
)

data class Attributes (
    val createdAt: String,
    val updatedAt: String,
    val slug: String,
    val synopsis: String,
    val description: String,
    val coverImageTopOffset: Long,
    val titles: Titles,
    val canonicalTitle: String,
    val abbreviatedTitles: List<String>,
    val averageRating: String,
    val ratingFrequencies: Map<String, String>,
    val userCount: Long,
    val favoritesCount: Long,
    val startDate: String,
    val endDate: String,
    val nextRelease: Any? = null,
    val popularityRank: Long,
    val ratingRank: Long,
    val ageRating: AgeRating,
    val ageRatingGuide: String,
    val subtype: String,
    val status: String,
    val tba: String? = null,
    val posterImage: PosterImage,
    val coverImage: CoverImage? = null,
    val episodeCount: Long,
    val episodeLength: Long? = null,
    val totalLength: Long,

    @SerializedName("youtubeVideoId")
    val youtubeVideoID: String,

    val showType: String,
    val nsfw: Boolean
)

enum class AgeRating(val value: String) {
    PG("PG"),
    R("R");

    companion object {
         fun fromValue(value: String): AgeRating = when (value) {
            "PG" -> PG
            "R"  -> R
            else -> throw IllegalArgumentException()
        }
    }
}

data class CoverImage (
    val tiny: String,
    val large: String,
    val small: String,
    val original: String,
    val meta: CoverImageMeta
)

data class CoverImageMeta (
    val dimensions: Dimensions
)

data class Dimensions (
    val tiny: Large,
    val large: Large,
    val small: Large,
    val medium: Large? = null
)

data class Large (
    val width: Long,
    val height: Long
)

data class PosterImage (
    val tiny: String,
    val large: String,
    val small: String,
    val medium: String,
    val original: String,
    val meta: CoverImageMeta
)
data class Titles (
    val en: String? = null,

    @SerializedName("en_jp")
    val enJp: String,

    @SerializedName("ja_jp")
    val jaJp: String,

    @SerializedName("en_us")
    val enUs: String? = null
)

data class DatumLinks (
    val self: String
)

data class Relationship (
    val links: RelationshipLinks
)

data class RelationshipLinks (
    val self: String,
    val related: String
)

enum class TypeEnum(val value: String) {
    Anime("anime");

    companion object {
         fun fromValue(value: String): TypeEnum = when (value) {
            "anime" -> Anime
            else    -> throw IllegalArgumentException()
        }
    }
}

data class Welcome10Links (
    val first: String,
    val next: String,
    val last: String
)

data class Welcome10Meta (
    val count: Long
)