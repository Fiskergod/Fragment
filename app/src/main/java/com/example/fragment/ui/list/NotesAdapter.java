package com.example.fragment.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.bumptech.glide.Glide;
import com.example.fragment.R;
import com.example.fragment.domain.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.noteViewHolder> {

    private final ArrayList<Note> nList = new ArrayList<>();

    private onNoteClicked clickListener;

    private final Fragment fragment;

    private int longClickedPosition = -1;

    public NotesAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void addNList(List<Note> toAdd) {
        nList.clear();
        nList.addAll(toAdd);

        notifyDataSetChanged();
    }

    public int addNList(Note note) {
        nList.add(note);

        int position = nList.size() - 1;

        notifyItemInserted(position);

        return position;
    }

    public void delete(int index) {
        nList.remove(index);

        notifyItemRemoved(index);
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new noteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder holder, int position) {

        Note note = nList.get(position);
        holder.style.setText(note.getTitle());

        Glide
                .with(holder.icon)
                .load(note.getImageUrl())
                .centerCrop()
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return nList.size();
    }

    public onNoteClicked getClickListener() {
        return clickListener;
    }

    public void setClickListener(onNoteClicked clickListener) {
        this.clickListener = clickListener;
    }

    public int getLongClickedPosition() {
        return longClickedPosition;
    }

    public Note getItemAt(int longClickedPosition) {
        return nList.get(longClickedPosition);
    }

    class noteViewHolder extends ViewHolder {

        TextView style;
        ImageView icon;

        public noteViewHolder(@NonNull View itemView) {
            super(itemView);

            fragment.registerForContextMenu(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getClickListener() != null) {
                        getClickListener().onNoteClicked(nList.get(getAdapterPosition()));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    longClickedPosition = getAdapterPosition();
                    return true;
                }
            });

            style = itemView.findViewById(R.id.task);
            icon = itemView.findViewById(R.id.icon);
        }
    }

    interface onNoteClicked {
        void onNoteClicked(Note note);
    }
}
