package com.platformscience.smartroute

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.core.view.marginLeft
import androidx.core.view.setPadding
import androidx.fragment.app.DialogFragment
import java.net.URL

class LoadShipmentInfoFragment(val viewModel: DriverShipmentViewModel) : DialogFragment() {
    private val exampleUrl = "https://raw.githubusercontent.com/fredc888/fredc_ps_smartroute/refs/heads/main/SmartRoute/samplejson/shipmentinfo.json"
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction.
            val urlText= EditText(activity);
            urlText.setPadding(100);
            urlText.minLines=2;
            urlText.setText(exampleUrl);
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Enter Url for Shipment Info.")
                .setPositiveButton("Start") { dialog, id ->
                    try {
                        val urlString = urlText.text.toString();
                        val url = URL(urlString);
                        viewModel.loadShipmentRoute(url);
                    } catch (e:Exception) {

                    }
                }
                .setNegativeButton("Cancel") { dialog, id ->
                }
            builder.setView(urlText);

            // Create the AlertDialog object and return it.
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}