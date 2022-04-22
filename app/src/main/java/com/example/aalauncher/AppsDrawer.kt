package com.example.aalauncher


import SpacesItemDecoration
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import layout.AppInfo


class AppsDrawer : AppCompatActivity() {

    var appsList: MutableList<Any>? = null
    var rAdapter: RAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.apps_drawer)

        val recyclerView: RecyclerView = findViewById(R.id.appsList)
        rAdapter = RAdapter(this)
        var myThread = MyThread()

        recyclerView.adapter = rAdapter
        recyclerView.layoutManager = GridLayoutManager(this,6, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(SpacesItemDecoration(20))
        myThread.execute()

    }

    inner class MyThread: AsyncTask<Void,Void,String>() {
        override fun doInBackground(p0: Array<Void?>): String {
            print("ASYNC TASK")
            Log.i("ASYNC TASK","")

            var pm: PackageManager = getPackageManager()

            var i = Intent(Intent.ACTION_MAIN, null)
            i.addCategory(Intent.CATEGORY_LAUNCHER)

            var allApps: MutableList<ResolveInfo> = pm.queryIntentActivities(i,0)


            for(ri in allApps) {
                val app = AppInfo()
                app.label = ri.loadLabel(pm)
                app.packageName = ri.activityInfo.packageName
                app.icon = ri.activityInfo.loadIcon(pm)

                rAdapter?.appsList?.add(app)
            }

            return "Success"
        }
    }

}