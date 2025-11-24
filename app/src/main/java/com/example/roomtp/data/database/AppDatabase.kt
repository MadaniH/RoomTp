package com.example.roomtp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomtp.data.dao.UtilisateurDao
import com.example.roomtp.data.entities.Utilisateur

@Database(entities = [Utilisateur::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun utilisateurDao(): UtilisateurDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "utilisateurs.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
