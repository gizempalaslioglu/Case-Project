package com.project.example.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.example.data.model.*
import com.project.example.databinding.TopFlyItemViewHolderBinding

class NextPreviousDayAdapter(
    val priceHistory: PriceHistory,
    val departure: List<Departure>
) : RecyclerView.Adapter<NextPreviousDayAdapter.NextPreviousDayViewHolder>() {


    class NextPreviousDayViewHolder(val binding: TopFlyItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextPreviousDayViewHolder {
        return NextPreviousDayViewHolder(
            TopFlyItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NextPreviousDayViewHolder, position: Int) {

        val departures = departure[position].segments[0]
        holder.binding.nextDayPrice.text = "${priceHistory.departure.next_day_price} TL"
        holder.binding.previousDayPrice.text = "${priceHistory.departure.previous_day_price} TL"

    }

    override fun getItemCount(): Int {
        return 1
    }


}