package com.example.caapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.caapplication.data.CocktailEntity

import com.example.caapplication.databinding.FragmentMainBinding

class MainFragment : Fragment(),
    CocktailsListAdapter.ListItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: CocktailsListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // action bar with the up/back/home button - Tick symbol, does not display in ListFragment, but is set to display in EditorFragment,
        (activity as AppCompatActivity)
            .supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(true)


        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(
                context, LinearLayoutManager(context).orientation
            )
            addItemDecoration(divider)
        }

        viewModel.cocktails.observe(viewLifecycleOwner, Observer {
            adapter = CocktailsListAdapter(requireContext(),it, this@MainFragment)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        })
        return binding.root
    }


    override fun onItemClick(cocktail: CocktailEntity) {

        // Log - print out to logcat to help with debugging if errors occur
        // TAG is a constant defined in Constants.kt - you can search yhe logcat using this TAG to help with debugging errors
        // Log.i(TAG, "onItemClick : Received Cocktail name ${cocktail.strDrink}")
        val action = MainFragmentDirections.actionMainFragmentToEditorFragment(cocktail)
        findNavController().navigate(action)
    }

}