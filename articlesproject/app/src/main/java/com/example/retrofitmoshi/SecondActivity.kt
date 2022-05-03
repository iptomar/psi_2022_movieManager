package com.example.retrofitmoshi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    companion object {
        const val keyPos = "pos"
        const val keyList = "list"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)




        val cont = intent.getStringArrayListExtra(keyList)?.count()

        if(cont ==1){
            forwardsArrow.visibility = ViewPager.INVISIBLE
        }
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, intent.getParcelableArrayListExtra(keyList) ?: arrayListOf())
        val pos = intent.getIntExtra(keyPos,0)
        viewPager.currentItem = pos
        if(pos==0) backwardsArrow.visibility = ViewPager.INVISIBLE
        if(pos == viewPager.adapter?.count?.minus(1)) forwardsArrow.visibility = ViewPager.INVISIBLE
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}
            override fun onPageSelected(posi: Int) {

                if(posi==0)
                    backwardsArrow.visibility = ViewPager.INVISIBLE
                else{
                    backwardsArrow.visibility = ViewPager.VISIBLE
                }
                if(posi == viewPager.adapter?.count?.minus(1) ?: "N/A")
                    forwardsArrow.visibility = ViewPager.INVISIBLE
                else{
                    forwardsArrow.visibility = ViewPager.VISIBLE
                }
            }
        })


        backwardsArrow.setOnClickListener{
            viewPager.currentItem = viewPager.currentItem - 1
        }

        forwardsArrow.setOnClickListener{
            viewPager.currentItem = viewPager.currentItem + 1
        }

        exitSecond.setOnClickListener{
            onBackPressed()
        }



    }
}
