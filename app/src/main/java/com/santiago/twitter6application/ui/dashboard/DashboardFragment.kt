package com.santiago.twitter6application.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.santiago.twitter6application.R
import com.santiago.twitter6application.databinding.FragmentDashboardBinding
import com.santiago.twitter6application.models.DBManager
import com.santiago.twitter6application.models.users.Data
import kotlinx.android.synthetic.main.fragment_actualizar.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager = DBManager(requireContext())

        buttonCrearCrear.setOnClickListener {
            if (editTextCrearEmail.text.isEmpty() || editTextCrearFirstName.text.isEmpty() || editTextCrearLastName.text.isEmpty()){
                Toast.makeText(requireContext(), "debe tener todos los datos completos", Toast.LENGTH_SHORT).show()
            }else{
                var result = dbManager.insertar(Data(editTextCrearEmail.text.toString(),editTextCrearFirstName.text.toString(),editTextCrearLastName.text.toString(),"https://reqres.in/img/faces/7-image.jpg"))
                if (result>0){
                    Toast.makeText(requireContext(), "se insertaron bien los datos", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(requireContext(), " no se insertaron bien los datos", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}