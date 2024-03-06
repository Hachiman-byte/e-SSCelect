package com.example.ssc_voting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class resultSecretaryAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private static List<CandidatesData> dataList;
    CardView cardView;
    private static int selectedPosition = RecyclerView.NO_POSITION;


    public resultSecretaryAdapter(Context context, List<CandidatesData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_viewer, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(this.context).load(((CandidatesData) this.dataList.get(position)).getProfileImage()).into(holder.candidateImage);
        String fullname = dataList.get(position).getLastname() + ", " + dataList.get(position).getFirstname();
        holder.candidatesname.setText(fullname);
        holder.description.setText(dataList.get(position).getPartylist().toUpperCase());
        holder.counts.setText(String.valueOf(dataList.get(position).getVoteCounts()));


        CandidatesData candidateWithHighestVotes = findCandidateWithHighestVotes();
        CandidatesData candidateWithLowestVotes = findCandidateWithLowestVotes();


        if (dataList.get(position).equals(candidateWithHighestVotes)) {
            holder.cardViewvote.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_light));
        } else if (dataList.get(position).equals(candidateWithLowestVotes)) {
            holder.cardViewvote.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_light));
        } else {
            holder.cardViewvote.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
        }
    }

    private CandidatesData findCandidateWithHighestVotes() {
        CandidatesData candidateWithHighestVotes = dataList.get(0);
        for (CandidatesData candidate : dataList) {
            if (candidate.getVoteCounts() > candidateWithHighestVotes.getVoteCounts()) {
                candidateWithHighestVotes = candidate;
            }
        }
        return candidateWithHighestVotes;
    }

    private CandidatesData findCandidateWithLowestVotes() {
        CandidatesData candidateWithLowestVotes = dataList.get(0);
        for (CandidatesData candidate : dataList) {
            if (candidate.getVoteCounts() < candidateWithLowestVotes.getVoteCounts()) {
                candidateWithLowestVotes = candidate;
            }
        }
        return candidateWithLowestVotes;
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }
}

