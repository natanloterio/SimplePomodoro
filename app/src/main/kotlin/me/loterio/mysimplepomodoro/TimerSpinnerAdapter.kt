package me.loterio.mysimplepomodoro

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class TimerSpinnerAdapter: BaseAdapter() {

    private val timeOptions: Array<String> = arrayOf("Select Time", "25", "30", "35", "40", "45", "50", "55", "60")
    private val timeOptionsItemView = R.layout.time_option_item

    override fun getCount(): Int {
        return timeOptions.size
    }

    override fun getItem(position: Int): Any {
        return timeOptions[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val validView = convertView ?: View.inflate(parent?.context, timeOptionsItemView, null)
        validView.findViewById<TextView>(R.id.text1)?.text = timeOptions[position]
        return validView
    }
}