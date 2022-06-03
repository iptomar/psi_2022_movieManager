package com.example.retrofitmoshi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fragmentManager: FragmentManager, private val mList: ArrayList<Movie>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        val article = mList[position]
        return PagerFragment.newInstance(article.id,article.title, article.poster_path)
}

    override fun getCount() = mList.size
}