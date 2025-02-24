package com.platformscience.smartroute

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.databinding.ActivityItemDetailBinding
import com.platformscience.smartroute.util.ShipmentInfoReader


class DriverShipmentActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel : DriverShipmentViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(findViewById(R.id.my_toolbar))

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
        loadDefaultRoutes();
    }
    private fun loadDefaultRoutes(){
        //Load shipment info
        val routeRequest = ShipmentInfoReader.readDefaultShipmentInfoAsset(applicationContext);
        loadRoutes(routeRequest);

    }
    private fun loadRoutes(routeRequest: RouteRequest) {
        val routeResults= ShipmentRouteEngine.getShipmentRoutes(routeRequest)
        viewModel.updateShipmentRoutes(routeResults);


    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_item_detail)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


}