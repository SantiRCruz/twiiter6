package com.santiago.twitter6application.controllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.santiago.twitter6application.R
import com.santiago.twitter6application.models.Constants
import com.santiago.twitter6application.models.DBManager
import com.santiago.twitter6application.models.users.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_users.view.*

class RecyclerAdapaterUsers(val data : List<Data>) : RecyclerView.Adapter<RecyclerAdapaterUsers.RecyclerHolderUsers>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolderUsers {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerHolderUsers(layoutInflater.inflate(R.layout.item_list_users,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerHolderUsers, position: Int) {
        holder.render(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class RecyclerHolderUsers (val view : View,val context : Context) : RecyclerView.ViewHolder(view){
        fun render(data: Data){
            view.textViewItemEmail.text = data.email
            view.textViewItemFirstName.text = data.first_name
            view.textViewItemLastName.text = data.last_name
            Picasso.get().load(data.avatar).into(view.imageViewItem)
            view.buttonItemEliminar.setOnClickListener {
                val dbManager = DBManager(context)
                dbManager.eliminar(Data(data.id,"","","",""))
                Navigation.findNavController(it).navigate(R.id.navigation_users)
            }
            view.buttonItemActualizar.setOnClickListener {
                Constants.ID_ACTUALIZAR = data.id
                Navigation.findNavController(it).navigate(R.id.navigation_actualizar)
            }
        }
    }
}