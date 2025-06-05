package com.bruce.tt.festival.data.repository

import android.R.attr.data
import com.bruce.tt.datasource.local.entity.FestivalsData
import com.bruce.tt.festival.domain.repository.IFestivalRepository
import com.bruce.tt.utilities.Resource
import com.bruce.tt.utilities.logging.AppLogger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class FestivalRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : IFestivalRepository {

    override suspend fun getFestivalData(): Flow<Resource<FestivalsData?>> = flow {
        try {
            emit(Resource.Loading())

            val result = supabaseClient.postgrest["FestivalsData"].select()
            val festivals = result.decodeList<FestivalsData>()

            festivals.getOrNull(index = 0)?.also { festival ->
                festival.festivalDate?.also {
                    if (isTodayFestival(it)) {
                        AppLogger.d(message = "Yes, today is a festival")
                        emit(Resource.Success(data = festival))
                    } else {
                        AppLogger.d(message = "No, today is not festival")
                        emit(Resource.Success(data = null))
                    }
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    private fun isTodayFestival(festivalDate: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            val parsedDate = LocalDate.parse(festivalDate, formatter)
            val today = LocalDate.now()
            parsedDate.isEqual(today)
        } catch (e: DateTimeParseException) {
            return false
        }
    }

}