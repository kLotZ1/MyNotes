package com.example.mynotes.viewmodels

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.model.notes.Task

class RecyclerViewAdapter(var listOfTasks: List<Task>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var priority: ImageView = v.findViewById(R.id.priorityImage)
        var title: TextView = v.findViewById(R.id.segmentNoteTitle)
        var description: TextView = v.findViewById(R.id.segmentNoteText)
        var checkBox: CheckBox = v.findViewById(R.id.segmentCheckBox)
        var linearLayout: LinearLayout = v.findViewById(R.id.segmentLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_segment_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listOfTasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = listOfTasks[position].title
        holder.description.text = listOfTasks[position].description
        holder.checkBox.isChecked = listOfTasks[position].done == 1
        holder.priority.setBackgroundColor(Color.parseColor(listOfTasks[position].priority.color))
//        holder.linearLayout.setOnClickListener {
//            view.onClickRedactNote(listOfTasks[position].id)
//        }
    }
}


