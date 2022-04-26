package com.example.retrofitmoshi

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class ViewPagerAdapter (fragmentManager: FragmentManager, private val mList: ArrayList<Article>) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        val article = mList[position]
        return PagerFragment.newInstance(article.id,article.release_date, article.poster_path)
}

    override fun getCount() = mList.size
}