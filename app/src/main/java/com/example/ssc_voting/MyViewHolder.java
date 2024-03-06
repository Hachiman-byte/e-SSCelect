package com.example.ssc_voting;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView candidateImage,popupImage;
    CardView recCard,cardViewvote,cardViewback,voteBG;
    TextView candidatesname,description,counts;

    RecyclerView presidentRecyclerView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.candidateImage = (ImageView)itemView.findViewById(R.id.candidatesImage);
        this.recCard = (CardView)itemView.findViewById(R.id.candidatesCard);
        this.candidatesname = (TextView) itemView.findViewById(R.id.candidatesName);
        this.description = (TextView) itemView.findViewById(R.id.description);
        cardViewvote = itemView.findViewById(R.id.votecandidatesCard);
        popupImage = itemView.findViewById(R.id.candidateImagePopup);
        presidentRecyclerView = itemView.findViewById(R.id.recyclerViewPresident);
        counts = itemView.findViewById(R.id.votesCounts);
        cardViewback = itemView.findViewById(R.id.cardviewbacground);
        voteBG = itemView.findViewById(R.id.voteBG);
    }
}
