package com.danilovfa.pokemontracker.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.danilovfa.pokemontracker.R
import com.danilovfa.pokemontracker.databinding.FragmentDetailsBinding
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.presentation.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val binding: FragmentDetailsBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("id")
        viewModel.id = id!!
        viewModel.loadDetails()
        viewModel.details.observe(viewLifecycleOwner) { details ->
            setDetails(details)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            setError(error)
        }
        Log.d("Details", "onViewCreated: $id")
    }

    private fun setDetails(details: PokemonDetails) {
        binding.textDetailsName.text = details.name.replaceFirstChar { it.uppercaseChar() }
        binding.textHeight.text = getString(R.string.height, details.heightInDm * 10)
        binding.textWeight.text = getString(R.string.weight, details.weightInHg.toDouble() / 10)
        Glide.with(this)
            .asBitmap()
            .load(details.sprite)
            .into(binding.imagePokemonSprite)


        details.types.forEach {type ->
            addType(type)
        }

        setVisible()
    }

    private fun setVisible() {
        with (binding) {
            progressBarDetails.visibility = View.GONE
            textDetailsName.visibility = View.VISIBLE
            textWeight.visibility = View.VISIBLE
            textWeightLabel.visibility = View.VISIBLE
            textHeight.visibility = View.VISIBLE
            textHeightLabel.visibility = View.VISIBLE
        }
    }

    private fun addType(type: String) {
        val layout = LayoutInflater.from(requireContext())
            .inflate(R.layout.pokemon_type_item_layout, binding.typesLayout, false)

        val textView = layout.findViewById<TextView>(R.id.textType)
        textView.text = type.replaceFirstChar { it.uppercaseChar() }

        binding.typesLayout.addView(layout)
    }

    private fun setError(error: String) {
        with (binding) {
            detailsErrorMessage.visibility = View.VISIBLE
            progressBarDetails.visibility = View.GONE
            detailsErrorMessage.text = error
        }
    }
}