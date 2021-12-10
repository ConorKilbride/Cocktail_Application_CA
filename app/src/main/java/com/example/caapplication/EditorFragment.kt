package com.example.caapplication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.caapplication.data.FavouriteEntity
import com.example.caapplication.databinding.EditorFragmentBinding

class EditorFragment : Fragment() {

    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: EditorFragmentBinding
    private lateinit var viewModel: EditorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // ActionBar is menu on top
        (activity as AppCompatActivity).supportActionBar?.let{
            // 'it' is similar to 'this' in Java (there are small differences)
            it.setHomeButtonEnabled(true)
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_check)
        }
        setHasOptionsMenu(true)

        binding = EditorFragmentBinding.inflate(inflater, container, false)

        binding.title.setText(args.cocktail.strDrink)
        binding.instruction.setText(args.cocktail.strInstructions)
        binding.category.setText(args.cocktail.strCategory)

        // load the image from the web(strImageSource)
        // into the plantImage object in the layout
        Glide.with(this)
            .load(args.cocktail.strDrinkThumb)
            .into(binding.cocktailImage)

        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        viewModel.currentFavourite.observe(viewLifecycleOwner, Observer {
            binding.myNotes.setText(it.myNotes)
        })

        viewModel.getFavourite(args.cocktail.idDrink)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // When the home button is clicked, save changes then return to the MainFragment, which is the List
            android.R.id.home -> saveAndReturn()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveAndReturn() :Boolean{
        viewModel.saveFavourite(FavouriteEntity(args.cocktail.idDrink, binding.myNotes.text.toString()))
        findNavController().navigateUp()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}