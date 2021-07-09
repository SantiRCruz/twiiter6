package com.santiago.twitter6application.ui.actualizar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.santiago.twitter6application.R
import com.santiago.twitter6application.databinding.FragmentActualizarBinding
import com.santiago.twitter6application.models.Constants
import com.santiago.twitter6application.models.DBManager
import com.santiago.twitter6application.models.users.Data
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_actualizar.*

class ActualizarFragment : Fragment() {
    private var _binding: FragmentActualizarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentActualizarBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbManager = DBManager(requireContext())
        //listar
        var lista = dbManager.listarId(Data(Constants.ID_ACTUALIZAR,"","","",""))
        var partesLista = lista.toString().split(" ")
        editTextActualizarEmail.setText(partesLista[1])
        editTextActualizarFirstName.setText(partesLista[2])
        editTextActualizarLastName.setText(partesLista[3])
        Picasso.get().load(partesLista[4].replace("]","")).into(imageViewActualizar)

        //actualizar
        buttonActualizarActualizar.setOnClickListener {
            if (editTextActualizarEmail.text.isEmpty() || editTextActualizarFirstName.text.isEmpty() || editTextActualizarLastName.text.isEmpty() ){
                Toast.makeText(requireContext(), "Debe tener todos los campos completos", Toast.LENGTH_SHORT).show()
            }else{
                var result = dbManager.actualizar(Data(Constants.ID_ACTUALIZAR,editTextActualizarEmail.text.toString(),editTextActualizarFirstName.text.toString(),editTextActualizarLastName.text.toString()))
                if (result>0){
                    Navigation.findNavController(it).navigate(R.id.navigation_users)
                }else{
                    Toast.makeText(requireContext(), "No se puedo actualizar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
