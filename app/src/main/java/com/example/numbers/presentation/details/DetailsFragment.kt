package com.example.numbers.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.numbers.data.cache.NumbersDatabase
import com.example.numbers.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[DetailsViewModel::class.java]
        val dao = NumbersDatabase.getDatabase(requireContext()).numbersDao()

        val args: DetailsFragmentArgs by navArgs()

        if (args.fact.isNotEmpty()) {
            binding.progressBar.visibility = View.GONE
            binding.detailsTextView.text = args.fact
        } else if (args.isRandom) {
            viewModel.getRandomNumber(dao) { result ->
                binding.progressBar.visibility = View.GONE
                if (result is com.example.numbers.data.model.Result.NumberRandomFact) {
                    binding.detailsTextView.text = result.fact
                } else {
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorTextView.text =
                        (result as com.example.numbers.data.model.Result.Error).message
                }
            }
        } else {
            viewModel.getNumber(dao, args.number) { result ->
                binding.progressBar.visibility = View.GONE
                if (result is com.example.numbers.data.model.Result.NumberFact) {
                    binding.detailsTextView.text = result.fact
                } else {
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.errorTextView.text =
                        (result as com.example.numbers.data.model.Result.Error).message
                }
            }
        }
    }
}