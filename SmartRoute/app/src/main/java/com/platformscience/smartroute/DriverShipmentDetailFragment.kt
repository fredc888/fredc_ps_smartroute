package com.platformscience.smartroute

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.ShipmentScore
import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.databinding.FragmentShipmentDetailBinding

/**

 */
class DriverShipmentDetailFragment : Fragment() {

    /**
     * The driver for the route to display
     */
    private var driverName: String? = null;

    private lateinit var viewModel:DriverShipmentViewModel;

    lateinit var shipmentAddress: TextView
    lateinit var shipmentScore: TextView
    lateinit var detailContainer: CoordinatorLayout
    lateinit var shipmentScoreContainer: LinearLayout

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentShipmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            driverName = clipDataItem.text.toString()
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(DriverShipmentViewModel::class.java);

        arguments?.let {
            if (it.containsKey(ARG_DRIVER_NAME)) {
                // Load the driver and route specified by the fragment arguments.
                driverName = it.getString(ARG_DRIVER_NAME)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentShipmentDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        shipmentAddress = binding.shipmentAddress!!
        shipmentScore = binding.shipmentScore!!
        detailContainer = binding.itemDetailContainer!!
        shipmentScoreContainer = binding.shipmentScoreContainer!!

        // Create the observer which updates the UI.
        val developerModeOnObserver = Observer<Boolean> { isDeveloperModeOn ->
            Log.d("DriverShipmentDetailFragment", "DeveleporModeObserver: state changed")
            updateDeveloperContent();
        }
        viewModel.getDeveloperMode().observe(this, developerModeOnObserver)


        updateContent()
        rootView.setOnDragListener(dragListener)
        return rootView
    }

    private fun updateDeveloperContent() {
        val isDeveloperModeOn = viewModel.getDeveloperMode().value?:false
        if (isDeveloperModeOn)
            shipmentScoreContainer.visibility= View.VISIBLE
        else
            shipmentScoreContainer.visibility = View.INVISIBLE
    }

    private fun updateContent() {
        val driverName = this.driverName;

        updateDeveloperContent();

        if (driverName == null) {
            detailContainer.visibility= View.INVISIBLE;
            toolbarLayout?.title = "Select a Driver"
            shipmentAddress.text =""
        } else {
            detailContainer.visibility= View.VISIBLE;
            val routeResults = viewModel.getShipmentRoutes().value;

            val driver = Driver(driverName)
            var shipment: Shipment?= null;
            var routeScore: ShipmentScore?= null;
            routeResults?.let {
                shipment = routeResults.getOptimalShipment(driver);
                shipment?.let {
                    routeScore =routeResults.getShipmentScore(driver, shipment!!);
                }
            }

            toolbarLayout?.title =  getString(R.string.label_shipment_details,driverName)
            shipmentAddress.text = shipment?.address ?:"";
            shipmentScore.text = routeScore?.score.toString() ?:"";
        }
    }

    companion object {
        /**
         * The fragment argument representing the name of the driver this fragment
         * represents.
         */
        const val ARG_DRIVER_NAME = "driver_name"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}