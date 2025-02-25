package com.platformscience.smartroute.util

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class LoadShipmentInfoFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction.
            val urlText= EditText(activity);
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Load shipping info")
                .setPositiveButton("Start") { dialog, id ->
                    val url = urlText.text.toString();
                    
                }
                .setNegativeButton("Cancel") { dialog, id ->
                }
            builder.setView(urlText);

            // Create the AlertDialog object and return it.
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}