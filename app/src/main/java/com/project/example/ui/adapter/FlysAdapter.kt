package com.project.example.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.example.R
import com.project.example.data.model.*
import com.project.example.databinding.FlysItemViewHolderBinding

class FlysAdapter(
    val airlines: List<Airline>,
    val airport: List<Airport>,
    val departure: List<Departure>
) : RecyclerView.Adapter<FlysAdapter.FlysViewHolder>() {


    class FlysViewHolder(val binding: FlysItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlysViewHolder {
        return FlysViewHolder(
            FlysItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FlysViewHolder, position: Int) {

        val departures = departure[position].segments[0]
        val baggage = departure[position].infos.baggage_info

        holder.binding.textViewDateArrival.text =
            departures.arrival_datetime.time
        holder.binding.textViewDateDeparture.text = departures.departure_datetime.time

        holder.binding.textViewOrigin.text = departures.origin
        holder.binding.textViewDestination.text = departures.destination

        holder.binding.textViewPrice.text =
            "${departure[position].price_breakdown.total}" + departure[position].price_breakdown.displayed_currency


        if (baggage.firstBaggageCollection != null) {
            holder.binding.textViewBaggage.text =
                "${baggage.firstBaggageCollection[0].allowance} " + baggage.firstBaggageCollection[0].unit + "/kişi"
        } else {
            holder.binding.textViewBaggage.text =
                holder.itemView.context.getString(R.string.hand_luggage)
        }

        airlines.forEach { airline ->
            if (airline.code == departures.marketing_airline) {
                holder.binding.textViewFlyName.text = airline.name
            }
        }

        Glide.with(holder.itemView.context)
            .load("https://www.enuygun.com/ucak-bileti/assets/images/airline-icon/${departures.marketing_airline}.png")
            .into(holder.binding.imageViewLogo)


    }

    override fun getItemCount(): Int {
        return departure.size
    }


}