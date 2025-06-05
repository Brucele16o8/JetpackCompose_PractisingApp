package com.bruce.tt.weather.data.remote.dto

import com.bruce.tt.weather.domain.model.MarineWeatherForecast
import com.google.gson.annotations.SerializedName

data class MarineWeatherForecastDto (
    @SerializedName("location") val location: LocationInfo,
    @SerializedName("forecast") val forecast: Forecast
)

data class LocationInfo(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double,
    @SerializedName("tz_id") val tzId: String,
    @SerializedName("localtime_epoch") val localtimeEpoch: Long,
    @SerializedName("localtime") val localtime: String
)

data class Forecast(
    @SerializedName("forecastday") val forecastDay: List<ForecastDay>
)

data class ForecastDay(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: Long,
    @SerializedName("day") val day: Day,
    @SerializedName("astro") val astro: Astro,
    @SerializedName("hour") val hour: List<Hour>
)

data class Day(
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("maxtemp_f") val maxTempF: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("mintemp_f") val minTempF: Double,
    @SerializedName("avgtemp_c") val avgTempC: Double,
    @SerializedName("avgtemp_f") val avgTempF: Double,
    @SerializedName("maxwind_mph") val maxWindMph: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    @SerializedName("totalprecip_mm") val totalPrecipMm: Double,
    @SerializedName("totalprecip_in") val totalPrecipIn: Double,
    @SerializedName("totalsnow_cm") val totalSnowCm: Double,
    @SerializedName("avgvis_km") val avgVisKm: Double,
    @SerializedName("avgvis_miles") val avgVisMiles: Double,
    @SerializedName("avghumidity") val avgHumidity: Double,
    @SerializedName("tides") val tides: List<Tides>,
    @SerializedName("condition") val condition: ConditionInfo,
    @SerializedName("uv") val uv: Double
)

data class Tides(
    @SerializedName("tide") val tide: List<Tide>
)

data class Tide(
    @SerializedName("tide_time") val tideTime: String,
    @SerializedName("tide_height_mt") val tideHeightMt: String,
    @SerializedName("tide_type") val tideType: String
)

data class ConditionInfo(
    @SerializedName("text") val text: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int
)

data class Astro(
    @SerializedName("sunrise") val sunrise: String,
    @SerializedName("sunset") val sunset: String,
    @SerializedName("moonrise") val moonrise: String,
    @SerializedName("moonset") val moonset: String,
    @SerializedName("moon_phase") val moonPhase: String,
    @SerializedName("moon_illumination") val moonIllumination: Int,
    @SerializedName("is_moon_up") val isMoonUp: Int,
    @SerializedName("is_sun_up") val isSunUp: Int
)

data class Hour(
    @SerializedName("time_epoch") val timeEpoch: Long,
    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val condition: ConditionInfo,
    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDir: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeC: Double,
    @SerializedName("feelslike_f") val feelsLikeF: Double,
    @SerializedName("windchill_c") val windChillC: Double,
    @SerializedName("windchill_f") val windChillF: Double,
    @SerializedName("heatindex_c") val heatIndexC: Double,
    @SerializedName("heatindex_f") val heatIndexF: Double,
    @SerializedName("dewpoint_c") val dewPointC: Double,
    @SerializedName("dewpoint_f") val dewPointF: Double,
    @SerializedName("vis_km") val visKm: Double,
    @SerializedName("vis_miles") val visMiles: Double,
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double,
    @SerializedName("uv") val uv: Double,
    @SerializedName("sig_ht_mt") val sigHtMt: Double,
    @SerializedName("swell_ht_mt") val swellHtMt: Double,
    @SerializedName("swell_ht_ft") val swellHtFt: Double,
    @SerializedName("swell_dir") val swellDir: Double,
    @SerializedName("swell_dir_16_point") val swellDir16Point: String,
    @SerializedName("swell_period_secs") val swellPeriodSecs: Double,
    @SerializedName("water_temp_c") val waterTempC: Double,
    @SerializedName("water_temp_f") val waterTempF: Double
)

fun MarineWeatherForecastDto.toMarineWeatherForecast(): MarineWeatherForecast{
    return MarineWeatherForecast(
        locationName = this.location.name,
        forecastDayDate = this.forecast.forecastDay.getOrNull(index = 0)?.date,
        maxTempC = this.forecast.forecastDay.getOrNull(index = 0)?.day?.maxTempC,
        minTempC = this.forecast.forecastDay.getOrNull(index = 0)?.day?.minTempC,
        avgHumidity = this.forecast.forecastDay.getOrNull(index = 0)?.day?.avgHumidity,
        summaryOfTheDay = this.forecast.forecastDay.getOrNull(index = 0)?.day?.condition?.text,
        summaryIconOfTheDay = "https:${this.forecast.forecastDay.getOrNull(index = 0)?.day?.condition?.icon}",
        sunrise = this.forecast.forecastDay.getOrNull(index = 0)?.astro?.sunrise,
        sunset = this.forecast.forecastDay.getOrNull(index = 0)?.astro?.sunset,
        moonrise = this.forecast.forecastDay.getOrNull(index = 0)?.astro?.moonrise,
        moonset = this.forecast.forecastDay.getOrNull(index = 0)?.astro?.moonset
    )
}