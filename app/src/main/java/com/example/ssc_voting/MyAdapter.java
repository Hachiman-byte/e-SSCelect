package com.example.ssc_voting;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<CandidatesData> dataList;

    public MyAdapter(Context context, List<CandidatesData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidates_viewer, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Glide.with(this.context).load(((CandidatesData)this.dataList.get(position)).getProfileImage()).into(holder.candidateImage);
        String fullname = dataList.get(position).getLastname() +", "+ dataList.get(position).getFirstname();
        holder.candidatesname.setText(fullname);
        holder.description.setText(dataList.get(position).getPosition());
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MyAdapter.this.context, candidates_details.class);
                intent.putExtra("profileImage", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getProfileImage());
                intent.putExtra("firstname", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getFirstname());
                intent.putExtra("lastname", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getLastname());
                intent.putExtra("age", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getAge());
                intent.putExtra("gender", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getGender());
                intent.putExtra("course_section", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getCourse_section());
                intent.putExtra("position", ((CandidatesData)MyAdapter.this.dataList.get(holder.getAdapterPosition())).getPosition());
                MyAdapter.this.context.startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.dataList.size();
    }
}
