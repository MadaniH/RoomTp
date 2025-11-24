
package com.example.roomtp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "utilisateurs")
data class Utilisateur(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nom: String,

    val email: String

)
