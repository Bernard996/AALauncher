@file:Suppress("JoinDeclarationAndAssignment")

package com.example.aalauncher

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import layout.AppInfo


class RAdapter(c: Context) : RecyclerView.Adapter<RAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val sortedList = appsList.sortedWith(compareBy { it.label.toString()})
        var textView: TextView
            var img: ImageView
            override fun onClick(v: View) {
                val pos = adapterPosition
                val context: Context = v.context
                val launchIntent: Intent? = context.packageManager.getLaunchIntentForPackage(
                    sortedList[pos].packageName.toString()
                )
                //context.startActivity(launchIntent)
                Toast.makeText(v.context, sortedList?.get(pos)?.label.toString(), Toast.LENGTH_LONG).show()
        }

        //This is the subclass ViewHolder which simply
        //'holds the views' for us to show on each row
        init {

            //Finds the views from our row.xml
            textView = itemView.findViewById(R.id.text)
            img = itemView.findViewById(R.id.img) as ImageView
            itemView.setOnClickListener(this)
        }
    }

    lateinit var appsList: MutableList<AppInfo>

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        //Here we use the information in the list we created to define the views
        val sortedList = appsList.sortedWith(compareBy { it.label.toString()})

        val appLabel: String = sortedList[i].label.toString()
        val appPackage: String = sortedList[i].packageName.toString()
        val appIcon: Drawable = sortedList[i].icon
        val textView = viewHolder.textView
        textView.text = appLabel
        val imageView: ImageView = viewHolder.img
        imageView.setImageDrawable(appIcon)
    }

    override fun getItemCount(): Int {

        //This method needs to be overridden so that Androids knows how many items
        //will be making it into the list
        return 0
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        //This is what adds the code we've written in here to our target view
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

}