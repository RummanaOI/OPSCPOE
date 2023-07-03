package com.example.opscpoe3


import android.content.Context
import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class CalendarAdaptor(context: Context, cardModelArrayList: ArrayList<Day?>) :
    ArrayAdapter<Day?>(context, 0, cardModelArrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView = convertView
        if (listItemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(context).inflate(R.layout.reward_item, parent, false)
        }

        val dayModel: Day? = getItem(position)
        val item = listItemView!!.findViewById<CardView>(R.id.daycontainer)


        val dayNumber = listItemView.findViewById<TextView>(R.id.dayNumber)
        val starItem = listItemView.findViewById<ImageView>(R.id.starImage)

        dayNumber.text = "${dayModel?.dayNum}"
        starItem.setImageResource(dayModel!!.imageID)

        return listItemView
    }

}