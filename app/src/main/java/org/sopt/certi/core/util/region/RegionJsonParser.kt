package org.sopt.certi.core.util.region

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.sopt.certi.R

object RegionJsonParser {
    private val json = Json { ignoreUnknownKeys = true }

    fun parseRegionData(context: Context): List<RegionData> {
        val jsonString = context.resources.openRawResource(R.raw.region)
            .bufferedReader()
            .use { it.readText() }

        val regionMap = json.parseToJsonElement(jsonString).jsonObject

        return regionMap.map { (city, districts) ->
            RegionData(
                city = city,
                districts = districts.jsonArray.map { it.jsonPrimitive.content }
            )
        }
    }

    fun getCities(context: Context): List<String> {
        return parseRegionData(context).map { it.city }
    }

    fun getDistricts(context: Context, city: String): List<String> {
        return parseRegionData(context)
            .find { it.city == city }
            ?.districts
            ?: emptyList()
    }
}
