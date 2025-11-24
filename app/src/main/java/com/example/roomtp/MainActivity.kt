package com.example.roomtp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.roomtp.adapter.UtilisateurAdapter
import com.example.roomtp.data.database.AppDatabase
import com.example.roomtp.data.entities.Utilisateur
import com.example.roomtp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var adapter: UtilisateurAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- Init Database Room ---
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "utilisateurs.db"
        ).build()

        // --- Init RecyclerView ---
        adapter = UtilisateurAdapter(emptyList()) { utilisateur ->
            supprimerUtilisateur(utilisateur)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // --- Bouton Ajouter ---
        binding.btnAjouter.setOnClickListener {
            val nom = binding.editNom.text.toString()
            val email = binding.editEmail.text.toString()

            if (nom.isNotEmpty() && email.isNotEmpty()) {
                ajouterUtilisateur(nom, email)
                binding.editNom.text.clear()
                binding.editEmail.text.clear()
            }
        }

        chargerUtilisateurs()
    }

    private fun ajouterUtilisateur(nom: String, email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            db.utilisateurDao().ajouter(Utilisateur(nom = nom, email = email))
            chargerUtilisateurs()
        }
    }

    private fun supprimerUtilisateur(utilisateur: Utilisateur) {
        CoroutineScope(Dispatchers.IO).launch {
            db.utilisateurDao().supprimer(utilisateur)
            chargerUtilisateurs()
        }
    }

    private fun chargerUtilisateurs() {
        CoroutineScope(Dispatchers.IO).launch {
            val liste = db.utilisateurDao().getAll()
            runOnUiThread {
                adapter.updateData(liste)
            }
        }
    }
}
