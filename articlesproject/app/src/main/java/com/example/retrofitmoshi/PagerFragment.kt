package com.example.retrofitmoshi

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*

import kotlinx.android.synthetic.main.fragment_pager.*



class PagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    companion object {
        const val ID_KEY = "id"
        const val TITLE_KEY = "title"
        const val URL_KEY = "url"

        fun newInstance(id:String?, title: String?, url: String?): PagerFragment {
            val fragment = PagerFragment()
            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            bundle.putString(TITLE_KEY, title)
            bundle.putString(URL_KEY, url)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textTitle.text = arguments?.getString(TITLE_KEY)
        (arguments?.getString(URL_KEY)?.isEmpty()).let {
            Picasso.get().load(arguments?.getString(URL_KEY)).into(imgView, object : Callback {

                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    if (textTitle != null && imgView != null && homeprogress != null) {
                        textTitle.gravity = Gravity.CENTER
                        imgView.visibility = ViewPager.GONE
                        homeprogress.visibility = ViewPager.GONE
                    }
                }
            })
        }


        val ur = arguments?.getString(URL_KEY)
        if (ur == null) {
            textTitle.gravity = Gravity.CENTER
            imgView.visibility = ViewPager.GONE
            homeprogress.visibility = ViewPager.GONE
        }



        val article = MainApp.listArt.first{it.id == arguments?.getString(ID_KEY)}

        //Insere a estrela premida ou nao consoante as estrelas da recyclerView do MainActivity
        if(MainApp.favouritesHelper.getFavouritesList()!!.contains(article))  {
            btnFavUnpressed.setImageResource(R.drawable.ic_favpressed)
        }else{
            btnFavUnpressed.setImageResource(R.drawable.ic_favunpressed)
        }


        btnFavUnpressed.setOnClickListener {
            //Insere a estrela premida ou nao consoante as estrelas da recyclerView do MainActivity
            if(MainApp.favouritesHelper.getFavouritesList()!!.contains(article))  {
                btnFavUnpressed.setImageResource(R.drawable.ic_favunpressed)
                MainApp.favouritesHelper.removeFavourite(article)
            }else{
                btnFavUnpressed.setImageResource(R.drawable.ic_favpressed)
                MainApp.favouritesHelper.addFavourite(article)
            }
        }
    }
}