package com.project.example.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.example.R
import com.project.example.databinding.FragmentHomeBinding
import com.project.example.ui.adapter.FlysAdapter
import com.project.example.ui.adapter.NextPreviousDayAdapter
import com.project.example.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!


    lateinit var flysAdapter : FlysAdapter
    lateinit var nextPreviousDayAdapter: NextPreviousDayAdapter

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.recyclerViewFlys.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }


        mainViewModel.flyLiveData.observe(viewLifecycleOwner) {

            binding.textViewRotationOrign.text = it.data.search_parameters.origin.slug
            binding.textViewRotationDestination.text = it.data.search_parameters.destination.slug

            flysAdapter = FlysAdapter(it.data.airlines,it.data.airports,it.data.flights.departure)
            nextPreviousDayAdapter = NextPreviousDayAdapter(it.data.price_history,it.data.flights.departure)
            binding.recyclerView.adapter = nextPreviousDayAdapter
            binding.recyclerViewFlys.adapter = flysAdapter

        }

        mainViewModel.getData(requireContext())



        return binding.root

    }

}