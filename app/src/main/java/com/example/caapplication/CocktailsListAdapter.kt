package com.example.caapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.caapplication.data.CocktailEntity
import com.example.caapplication.databinding.ListItemBinding
import android.content.Context

// a reference to the Cocktail List data is passed in during initialisation
class CocktailsListAdapter (val context: Context,
    private val cocktailsList: List<CocktailEntity>,
    // now a listener for each list item is also passed in.
    private val listener: ListItemListener) :

    // Inherits from RecyclerView.Adapter
    // it also has an inner class ViewHolder
    RecyclerView.Adapter<CocktailsListAdapter.ViewHolder>() {

        val selectedCocktails = arrayListOf<CocktailEntity>()

        // Inner class details
        inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                // binding to list_item.xml
                val binding = ListItemBinding.bind(itemView)
            }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = cocktailsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cocktail = cocktailsList[position]
        with(holder.binding) {
            cocktailName.text = cocktail.strDrink
            // load the image from the web(strImageSource)
            // into the plantImage object in the layout
            Glide.with(context)
                .load(cocktail.strDrinkThumb)
                .into(cocktailImage)
            root.setOnClickListener{
                listener.onItemClick(cocktail)
            }
        }
    }
    interface ListItemListener {
        fun onItemClick(cocktail: CocktailEntity)
    }
    }