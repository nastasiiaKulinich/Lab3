package com.example.numbers.presentation.number

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.numbers.R
import com.example.numbers.data.model.Result

class NumbersAdapter(
    private val numbersFact: List<Result.NumberFact>,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<NumbersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titleTextView: TextView
        val subTitleTextView: TextView
        val layout: CardView

        init {
            titleTextView = view.findViewById(R.id.titleTextView)
            subTitleTextView = view.findViewById(R.id.subTitleTextView)
            layout = view.findViewById(R.id.layout)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.number_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currentNumber = numbersFact[position]

        viewHolder.titleTextView.text = currentNumber.number
        viewHolder.subTitleTextView.text = currentNumber.fact

        viewHolder.layout.setOnClickListener {
            listener.onItemClick(currentNumber.fact)
        }
    }

    override fun getItemCount() = numbersFact.size

}