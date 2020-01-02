package com.example.dummydictionary.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.dummydictionary.R;
import com.example.dummydictionary.models.Word;
import com.example.dummydictionary.util.Utility;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.ViewHolder>{

    private static final String TAG = "WordsRecyclerAdapter";
    private ArrayList<Word> mWords = new ArrayList<>();
    private ArrayList<Word> mFilteredWords = new ArrayList<>();
    private OnWordListener mOnWordListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_word_list_item,
                parent, false);
        return new ViewHolder(view, mOnWordListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try{
            String month = mFilteredWords.get(position).getTimestamp().substring(0,2);
            month = Utility.getMonthFromNumber(month);
            String year = mFilteredWords.get(position).getTimestamp().substring(3);
            String timestamp = month + " " + year;
            holder.timestamp.setText(timestamp);
            holder.title.setText(mFilteredWords.get(position).getTitle());

        }catch (NullPointerException e){
            Log.e(TAG, "onBindViewHolder: Null Pointer: " + e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredWords.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        TextView timestamp, title;
        OnWordListener listener;

        public ViewHolder(@NonNull View itemView, OnWordListener listener) {
            super(itemView);
            timestamp = itemView.findViewById(R.id.word_timestamp);
            title = itemView.findViewById(R.id.word_title);
            this.listener = listener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: called.");
            listener.onWordClick(mWords.indexOf(mFilteredWords.get(getAdapterPosition())));
        }
    }



    public interface OnWordListener{
        void onWordClick(int position);
    }
}
