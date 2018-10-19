package engineers.software.ng.developers

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import engineers.software.ng.developers.activity.DeveloperDetails
import engineers.software.ng.developers.adapter.DeveloperAdapter
import engineers.software.ng.developers.model.Developers
import engineers.software.ng.developers.network.GetDataService
import engineers.software.ng.developers.network.RetrofitClientInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DeveloperAdapter.ItemClickListener{


    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var developerAdapter: DeveloperAdapter? = null
    var coordinatorLayout: CoordinatorLayout? = null

    override fun onItemClick(developers: Developers) {
        val intent = Intent(this@MainActivity, DeveloperDetails::class.java)
        intent.putExtra("developer", developers)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Initialize components

        recyclerView = findViewById(R.id.recycler)
        coordinatorLayout = findViewById(R.id.coordinator_layout)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = GridLayoutManager(applicationContext, 2)

        progressBar = findViewById(R.id.progressBar)

        if (!isNetworkAvailable()){
            val snackbar = Snackbar
                    .make(coordinatorLayout!!, "No Network connection", Snackbar.LENGTH_LONG)
                    .setAction("RETRY",
                            View.OnClickListener { v -> fetchUsersData() })

            snackbar.show()
        } else {
            fetchUsersData()
        }
    }

    private fun fetchUsersData() {
        val searchParams = "language:java location:lagos"
        val service = RetrofitClientInstance.retrofitInstance!!.create(GetDataService::class.java)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo.isConnected
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
