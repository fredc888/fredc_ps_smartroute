package com.platformscience.smartroute

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.platformscience.smartroute.data.RouteRequest
import com.platformscience.smartroute.databinding.ActivityItemDetailBinding
import com.platformscience.smartroute.util.ShipmentInfoReader
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DriverRouteActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

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
        val viewModel: DriverRouteViewModel by viewModels()
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                }
            }
        }

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
        val routeResults= RouteEngine.getRoutes(routeRequest)


    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_item_detail)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}