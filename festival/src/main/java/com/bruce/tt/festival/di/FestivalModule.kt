package com.bruce.tt.festival.di

import com.bruce.tt.festival.data.repository.FestivalRepositoryImpl
import com.bruce.tt.festival.domain.repository.IFestivalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FestivalModule {

    @Provides
    @Singleton
    fun providesSupabase(): SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://hvpclgswboaafzifohnu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh2cGNsZ3N3Ym9hYWZ6aWZvaG51Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg3Nzc0MTUsImV4cCI6MjA2NDM1MzQxNX0.lN79b3xgm5viYOur-limHip3tXKxRz6s2VA9cIhnkfk"
        ) {
            install(Postgrest)
        }
        return supabase
    }

    @Provides
    @Singleton
    fun providesRepository(supabaseClient: SupabaseClient): IFestivalRepository {
        return FestivalRepositoryImpl(supabaseClient = supabaseClient)
    }
}