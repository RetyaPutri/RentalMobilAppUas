package com.example.rentalmobilapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rentalmobilapp.R
import com.example.rentalmobilapp.model.Car

class CarListAdapter(
    private val onItemClickListener: (Car) -> Unit
): ListAdapter<Car, CarListAdapter.CarViewModel>(WORDS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewModel {
        return CarViewModel.create(parent)
    }

    override fun onBindViewHolder(holder: CarViewModel, position: Int) {
        val car = getItem(position)
        holder.bind(car)
        holder.itemView.setOnClickListener {
            onItemClickListener(car)
        }
    }
    class CarViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val addressTextView: TextView = itemView.findViewById(R.id.addressTextView)
        private val typecarTextView: TextView = itemView.findViewById(R.id.typecarTextView)
        fun bind(car: Car?) {
            nameTextView.text = car?.name
            car?.name?.let { Log.d("P.TOEX RENTAL",it) }
            addressTextView.text = car?.address
            typecarTextView.text = car?.typecar

        }

        companion object {
            fun create(parent: ViewGroup): CarListAdapter.CarViewModel {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_car, parent, false )
                return CarViewModel(view)
            }
        }
    }
    companion object{
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Car>() {
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}