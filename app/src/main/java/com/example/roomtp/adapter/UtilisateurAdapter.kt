package com.example.roomtp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtp.R
import com.example.roomtp.data.entities.Utilisateur

class UtilisateurAdapter(
    private var utilisateurs: List<Utilisateur>,
    private val onDeleteClick: (Utilisateur) -> Unit
) : RecyclerView.Adapter<UtilisateurAdapter.UtilisateurViewHolder>() {

    class UtilisateurViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtNom: TextView = view.findViewById(R.id.txtNom)
        val txtEmail: TextView = view.findViewById(R.id.txtEmail)
        val btnSupprimer: Button = view.findViewById(R.id.btnSupprimer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilisateurViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_utilisateur, parent, false)
        return UtilisateurViewHolder(view)
    }

    override fun onBindViewHolder(holder: UtilisateurViewHolder, position: Int) {
        val utilisateur = utilisateurs[position]
        holder.txtNom.text = utilisateur.nom
        holder.txtEmail.text = utilisateur.email

        holder.btnSupprimer.setOnClickListener {
            onDeleteClick(utilisateur)
        }
    }

    override fun getItemCount(): Int = utilisateurs.size

    fun updateData(newList: List<Utilisateur>) {
        utilisateurs = newList
        notifyDataSetChanged()
    }
}
