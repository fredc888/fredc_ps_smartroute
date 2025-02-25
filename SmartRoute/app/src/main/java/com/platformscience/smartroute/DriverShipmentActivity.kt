package com.platformscience.smartroute

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.platformscience.smartroute.databinding.ActivityItemDetailBinding
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Application main activity
 */
class DriverShipmentActivity : AppCompatActivity() {

    //Default Shipping Info JSON file stored in assets
    private val DEFAULT_ASSET_SHIPMENTINFO="shipmentinfo.json";

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel : DriverShipmentViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_item_detail) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Create view model
        viewModel = ViewModelProvider(this).get(DriverShipmentViewModel::class.java)
    }

    override fun onResume() {
        super.onResume();
        loadDefaultShipmentRoute();
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_item_detail)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /**
     * Read the default shipping info JSON and get the shipping routes for it
     */
    private fun loadDefaultShipmentRoute() {
        val assetReader = BufferedReader(InputStreamReader(applicationContext.assets.open(DEFAULT_ASSET_SHIPMENTINFO)));
        viewModel.loadShipmentRoute(assetReader);
    }


}