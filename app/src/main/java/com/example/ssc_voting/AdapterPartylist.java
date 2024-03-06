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

public class AdapterPartylist extends RecyclerView.Adapter<ViewHolderPartylist>{

    private Context context;
    private List<PartylistData> dataList;

    public AdapterPartylist(Context context, List<PartylistData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolderPartylist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partylist_viewer, parent, false);
        return new ViewHolderPartylist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPartylist holder, int position) {
        Glide.with(this.context).load(((PartylistData)this.dataList.get(position)).getPartylistlogo()).into(holder.partylistImage);
        holder.recCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AdapterPartylist.this.context, partylist_candidates.class);
                intent.putExtra("partylistname", ((PartylistData)AdapterPartylist.this.dataList.get(holder.getAdapterPosition())).getPartylistname());
                AdapterPartylist.this.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }
}
