package com.example.numbers.presentation.number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numbers.data.cache.NumbersDatabase
import com.example.numbers.databinding.FragmentNumbersBinding


class NumbersFragment : Fragment(), OnItemClickListener {

    private lateinit var binding: FragmentNumbersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumbersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[NumbersViewModel::class.java]
        val dao = NumbersDatabase.getDatabase(requireContext()).numbersDao()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllNumbers(dao) {
            binding.recyclerView.adapter = NumbersAdapter(it.numberFacts, this)
        }

        binding.getFactButton.setOnClickListener {
            val number = binding.inputEditText.text.toString()
            if (number.isEmpty())
                Toast.makeText(requireContext(), "Enter a number!", Toast.LENGTH_SHORT).show()
            else
                openDetailFragment(number = binding.inputEditText.text.toString())
        }
        binding.randomFactButton.setOnClickListener {
            openDetailFragment(isRandom = true)
        }
    }

    override fun onItemClick(fact: String) {
        openDetailFragment(fact = fact)
    }

    private fun openDetailFragment(number: String = "", isRandom: Boolean = false, fact: String = "") {
        findNavController().navigate(
            NumbersFragmentDirections.actionNumbersFragmentToDetailsFragment(number, isRandom, fact)
        )
    }
}

interface OnItemClickListener {
    fun onItemClick(fact: String)
}