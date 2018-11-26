package com.example.martynas.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.google.android.gms.tasks.Task;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<Task> task;
    protected Context context;
    public RecyclerViewAdapter(Context context, List<Task> task) {
        this.task = task;
        this.context = context;
    }

    //public RecyclerViewAdapter(MainActivity context, List<com.example.martynas.myapplication.Task> allTask) {
    //}

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView, task);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.categoryTitle.setText(task.get(position).getTask());
    }
    @Override
    public int getItemCount() {
        return this.task.size();
    }
}