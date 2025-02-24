package com.platformscience.smartroute

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.data.ShipmentScore
import com.platformscience.smartroute.data.Shipment
import com.platformscience.smartroute.databinding.FragmentItemDetailBinding

/**

 */
class DriverShipmentDetailFragment : Fragment() {

    /**
     * The driver for the route to display
     */
    private var driverName: String? = null;

    private lateinit var viewModel:DriverShipmentViewModel;

    lateinit var shipmentAddress: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

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

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        shipmentAddress = binding.itemDetail!!

        updateContent()
        rootView.setOnDragListener(dragListener)
        return rootView
    }

    private fun updateContent() {
        val driverName = this.driverName;
        if (driverName == null) {
            toolbarLayout?.title = "Select a Driver"
            shipmentAddress.text =""
        } else {
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

            toolbarLayout?.title = "Shipment Details for ${driver}.name "
            shipmentAddress.text = shipment?.address ?:"";
            //itemDetailTextView.text = routeScore?.score.toString() ?:"";
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