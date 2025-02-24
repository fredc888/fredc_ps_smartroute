package com.platformscience.smartroute

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.platformscience.smartroute.data.Driver
import com.platformscience.smartroute.databinding.FragmentItemListBinding
import com.platformscience.smartroute.databinding.DriverListContentBinding



class DriverListFragment : Fragment() {
    private lateinit var viewModel:DriverRouteViewModel;


    private var _binding: FragmentItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)
        viewModel = ViewModelProvider(requireActivity()).get(DriverRouteViewModel::class.java);
        setupRecyclerView(recyclerView, itemDetailFragmentContainer,
            viewModel=viewModel,
            lifecycleOwner = this)

    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?,
        viewModel: DriverRouteViewModel,
        lifecycleOwner: LifecycleOwner
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            itemDetailFragmentContainer = itemDetailFragmentContainer,
            viewModel =viewModel,
            lifecycleOwner = lifecycleOwner
        )

    }

    class SimpleItemRecyclerViewAdapter(
        private var drivers:Set<Driver>?=null,
        //private val values: List<PlaceholderContent.PlaceholderItem>,
        private val itemDetailFragmentContainer: View?,
        private val lifecycleOwner: LifecycleOwner,
        private val viewModel:DriverRouteViewModel
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
        init{
            viewModel.getShipmentRoutes().observe(lifecycleOwner, Observer { routeResult ->
                drivers=viewModel.getShipmentRoutes().value?.drivers;
                notifyDataSetChanged();
            });

        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                DriverListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val driver = drivers?.elementAt(position);
            holder.contentView.text = driver?.name?:"";

            with(holder.itemView) {
                tag = driver
                setOnClickListener { itemView ->
                    val item = itemView.tag as Driver
                    val bundle = Bundle()
                    bundle.putString(
                        DriverRouteDetailFragment.ARG_DRIVER_NAME,
                        item.name
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }


                setOnLongClickListener { v ->
                    // Setting the item id as the clip data so that the drop target is able to
                    // identify the id of the content
                    val clipItem = ClipData.Item(driver?.name)
                    val dragData = ClipData(
                        v.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        clipItem
                    )

                    if (Build.VERSION.SDK_INT >= 24) {
                        v.startDragAndDrop(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    } else {
                        v.startDrag(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    }
                }
            }
        }

        override fun getItemCount():Int{
            return drivers?.size?:0;
        }

        inner class ViewHolder(binding: DriverListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val contentView: TextView = binding.content
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}