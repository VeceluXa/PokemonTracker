package com.danilovfa.pokemontracker.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.map
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.danilovfa.pokemontracker.R
import com.danilovfa.pokemontracker.databinding.FragmentPageBinding
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonByPageUseCase
import com.danilovfa.pokemontracker.presentation.adapters.PokemonLoaderAdapter
import com.danilovfa.pokemontracker.presentation.adapters.PokemonPageAdapter
import com.danilovfa.pokemontracker.presentation.viewmodel.PageViewModel
import com.danilovfa.pokemontracker.presentation.viewmodel.PageViewModel_Factory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PageFragment : Fragment(), PokemonPageAdapter.OnItemClickListener {

    /**
     * I didn't use viewbinding delegate here because app was crashing
     * when user clicked after closing details fragment.
     * Here is the code for it:
     * private val binding: FragmentPageBinding by viewBinding(createMethod = CreateMethod.INFLATE)
     */
    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PageViewModel by viewModels()
    private val adapter: PokemonPageAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PokemonPageAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewPage.adapter = adapter.withLoadStateFooter(PokemonLoaderAdapter())

        adapter.addLoadStateListener {state ->
            with(binding) {
                recyclerViewPage.isVisible = state.refresh != LoadState.Loading
                progress.isVisible = state.refresh == LoadState.Loading
            }
        }

        lifecycleScope.launch {
            viewModel.pokemons.collectLatest {
                Log.d("Paged", "onViewCreated: $it")
                adapter.submitData(it)
            }
        }

        adapter.setOnItemClickLister(this)
    }

    override fun onItemClick(pokemonItem: PokemonItem?) {
        Log.d("Details", "onItemClick: ${pokemonItem?.id}")
        val bundle = bundleOf("id" to pokemonItem?.id)
        findNavController().navigate(R.id.action_PageFragment_to_DetailsFragment, bundle)
    }


}