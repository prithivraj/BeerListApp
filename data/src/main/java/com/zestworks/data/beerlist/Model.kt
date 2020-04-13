package com.zestworks.data.beerlist

import com.google.gson.annotations.SerializedName

class BeerListResponseModel : ArrayList<BeerListResponseModelItem>()

data class BeerListResponseModelItem(
    @SerializedName("abv")
    val abv: Double,
    @SerializedName("attenuation_level")
    val attenuationLevel: Float,
    @SerializedName("boil_volume")
    val boilVolume: BoilVolume,
    @SerializedName("brewers_tips")
    val brewersTips: String,
    @SerializedName("contributed_by")
    val contributedBy: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("ebc")
    val ebc: Float,
    @SerializedName("first_brewed")
    val firstBrewed: String,
    @SerializedName("food_pairing")
    val foodPairing: List<String>,
    @SerializedName("ibu")
    val ibu: Float,
    @SerializedName("id")
    val id: Float,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("ingredients")
    val ingredients: Ingredients,
    @SerializedName("method")
    val method: Method,
    @SerializedName("name")
    val name: String,
    @SerializedName("ph")
    val ph: Double,
    @SerializedName("srm")
    val srm: Float,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("target_fg")
    val targetFg: Float,
    @SerializedName("target_og")
    val targetOg: Float,
    @SerializedName("volume")
    val volume: Volume
)

data class BoilVolume(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Float
)

data class Ingredients(
    @SerializedName("hops")
    val hops: List<Hop>,
    @SerializedName("malt")
    val malt: List<Malt>,
    @SerializedName("yeast")
    val yeast: String
)

data class Method(
    @SerializedName("fermentation")
    val fermentation: Fermentation,
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp>,
    @SerializedName("twist")
    val twist: Any
)

data class Volume(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Float
)

data class Hop(
    @SerializedName("add")
    val add: String,
    @SerializedName("amount")
    val amount: Amount,
    @SerializedName("attribute")
    val attribute: String,
    @SerializedName("name")
    val name: String
)

data class Malt(
    @SerializedName("amount")
    val amount: AmountX,
    @SerializedName("name")
    val name: String
)

data class Amount(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Float
)

data class AmountX(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Double
)

data class Fermentation(
    @SerializedName("temp")
    val temp: Temp
)

data class MashTemp(
    @SerializedName("duration")
    val duration: Float,
    @SerializedName("temp")
    val temp: TempX
)

data class Temp(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Float
)

data class TempX(
    @SerializedName("unit")
    val unit: String,
    @SerializedName("value")
    val value: Float
)