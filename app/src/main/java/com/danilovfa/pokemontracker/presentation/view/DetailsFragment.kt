package com.danilovfa.pokemontracker.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.danilovfa.pokemontracker.databinding.FragmentDetailsBinding
import com.danilovfa.pokemontracker.presentation.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
    }
}