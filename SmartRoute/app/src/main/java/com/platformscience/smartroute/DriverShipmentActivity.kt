package com.platformscience.smartroute

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
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

        setSupportActionBar(findViewById(R.id.my_toolbar));

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_item_detail) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //Create view model
        viewModel = ViewModelProvider(this).get(DriverShipmentViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val developerModeOption = menu.findItem(R.id.action_toggle_developer_mode);
        val isOn = viewModel.getDeveloperMode().value?:false;
        if (isOn) {
            developerModeOption.title=getString(R.string.label_developer_mode_turn_off)
        } else {
            developerModeOption.title=getString(R.string.label_developer_mode_turn_on)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.action_load_default -> {
                loadDefaultShipmentRoute()
                true
            }
            R.id.action_load -> {
                loadShipmentRouteFromUrl()
                true
            }
            R.id.action_toggle_developer_mode -> {
                toggleDeveloperMode()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
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
        Log.d("DriverShipmentActivity", "loadDefaultShipmentRoute")
        val assetReader = BufferedReader(InputStreamReader(applicationContext.assets.open(DEFAULT_ASSET_SHIPMENTINFO)));
        viewModel.loadShipmentRoute(assetReader);
    }

    private fun loadShipmentRouteFromUrl(){
        LoadShipmentInfoFragment(viewModel).show(supportFragmentManager,
            "LOAD_SHIPMENT_ROUTE")

    }

    private fun toggleDeveloperMode(){
        val isOn = viewModel.getDeveloperMode().value?:false;
        viewModel.setDeveloperModeOn(!isOn);
        invalidateOptionsMenu();
    }


}