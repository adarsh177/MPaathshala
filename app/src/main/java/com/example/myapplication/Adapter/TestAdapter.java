package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Models.TestModel;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.Holder> {
    Context context;
    ArrayList<TestModel> data;

    public TestAdapter() {
    }

    public TestAdapter(Context context, ArrayList<TestModel> data, String teacher) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TestAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.Holder holder, int position) {
        TestModel testModel = data.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView time;
        Button next;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView5);
            time = itemView.findViewById(R.id.textView4);
            next=itemView.findViewById(R.id.button3);
        }
    }

}
