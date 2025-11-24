package com.example.roomtp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.roomtp.data.entities.Utilisateur

@Dao
interface UtilisateurDao {

    @Insert
    suspend fun ajouter(utilisateur: Utilisateur)

    @Delete
    suspend fun supprimer(utilisateur: Utilisateur)

    @Query("SELECT * FROM utilisateurs ORDER BY id DESC")
    suspend fun getAll(): List<Utilisateur>
}
