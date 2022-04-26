package com.example.retrofitmoshi

import android.content.Intent
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.recycle_view.*
import kotlinx.android.synthetic.main.recycle_view.view.*
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter(private val itemsList: MutableList<Article>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycle_view, parent, false))
    }


    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layout = holder.layoutView
        val article = itemsList[position]

        //val pattern = "dd-MM-yyyy"
        //val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        //val formattedDate = formatter.format(DateTime.parse(article.publishedAt).toDate())

        layout.title.text = article.title ?: "N/A"
        layout.release_date.text = article.release_date
        layout.vote_average.text = article.vote_average
        layout.overview.text = article.overview
        (article.poster_path?.isEmpty()).let {
            Picasso.get().load(article.poster_path).into(layout.urlToImage)
        }

        if(MainApp.favouritesHelper.getFavouritesList()!!.contains(article))  {
            layout.container.btnFavP.setImageResource(R.drawable.ic_favpressed)
        }
        else{
            layout.container.btnFavP.setImageResource(R.drawable.ic_favunpressed)
        }

        layout.container.btnFavP.setOnClickListener {
            //Insere a estrela premida ou nao consoante as estrelas da recyclerView do MainActivity
            if(MainApp.favouritesHelper.getFavouritesList()!!.contains(article)){
                layout.container.btnFavP.setImageResource(R.drawable.ic_favunpressed)
                MainApp.favouritesHelper.removeFavourite(article)
            }else{
                layout.container.btnFavP.setImageResource(R.drawable.ic_favpressed)
                MainApp.favouritesHelper.addFavourite(article)
            }
        }

        layout.container.setOnClickListener {

            val intent = Intent(layout.context, SecondActivity::class.java).apply {
                putExtra(SecondActivity.keyPos, position)
                putParcelableArrayListExtra(SecondActivity.keyList, ArrayList(itemsList))
            }
            layout.context.startActivity(intent)
        }
    }

    data class ViewHolder(val layoutView: View) : RecyclerView.ViewHolder(layoutView)
}
