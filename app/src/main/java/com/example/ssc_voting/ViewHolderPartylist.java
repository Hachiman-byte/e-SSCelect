package com.example.ssc_voting;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPartylist extends RecyclerView.ViewHolder{

    ImageView partylistImage;
    CardView recCard;

    public ViewHolderPartylist(@NonNull View itemView) {
        super(itemView);
        this.partylistImage = (ImageView)itemView.findViewById(R.id.partylistimg);
        this.recCard = (CardView)itemView.findViewById(R.id.partylistCard);
    }
}
