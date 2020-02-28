package com.custom.transportation.ui.view.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.ui.adapter.recycler.TagAdapter
import com.custom.transportation.ui.contract.TagContract
import com.google.android.material.textfield.TextInputEditText

class TagDialogFragment(tags: String, val position: Int, val view: TagContract.View) : DialogFragment() {

    private val tagAdapter = TagAdapter(tags)

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = requireActivity().layoutInflater.inflate(R.layout.layout_tag, null)
            val edtText = view.findViewById<TextInputEditText>(R.id.edt_input)
            view.findViewById<RecyclerView>(R.id.recycler).apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = tagAdapter
            }
            view.findViewById<ImageButton>(R.id.ib_add).setOnClickListener {
                val tag = edtText.text.toString()
                if(tag.isEmpty()) return@setOnClickListener
                if(tagAdapter.addItem(if(tag.startsWith('#')) tag else "#${tag}"))
                    edtText.text?.clear()
                else
                    Toast.makeText(context, "태그 추가에 실패하였습니다.", Toast.LENGTH_SHORT).show()
            }
            builder.setView(view)
                .setMessage("태그 편집")
                .setPositiveButton(android.R.string.ok) { dialog, id ->
                    if(tagAdapter.items.isNotEmpty()) {
                        this.view.addTags(position, tagAdapter.items.toString())
                    }
                }
                .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}