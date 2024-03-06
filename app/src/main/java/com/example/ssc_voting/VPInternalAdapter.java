package com.example.ssc_voting;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VPInternalAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private Context context;
    private static List<CandidatesData> dataList;
    CardView cardView;
    private static int selectedPosition = RecyclerView.NO_POSITION;

    public VPInternalAdapter(Context context, List<CandidatesData> dataList) {
        this.context = context;
        this.dataList = dataList;
        selectedPosition = RecyclerView.NO_POSITION;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vote_viewer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(this.context).load(((CandidatesData) this.dataList.get(position)).getProfileImage()).into(holder.candidateImage);
        String fullname = dataList.get(position).getLastname() + ", " + dataList.get(position).getFirstname();
        holder.candidatesname.setText(fullname);
        holder.description.setText(dataList.get(position).getPartylist().toUpperCase());

        updateCardView(holder.cardViewvote, holder.voteBG, position == selectedPosition);
        holder.cardViewvote.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if (holder.getAdapterPosition() == selectedPosition) {
                    selectedPosition = RecyclerView.NO_POSITION;
                    updateCardView(holder.cardViewvote, holder.voteBG, false);
                } else {
                    handleRadioSelection(holder.getAdapterPosition());
                }
            }
        });

        holder.candidateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(holder.candidateImage.getContext());
                dialog.setContentView(R.layout.popup_candidate);
                ImageView imageView = dialog.findViewById(R.id.candidateImagePopup);
                TextView fname = dialog.findViewById(R.id.popupfirstname);
                TextView lname = dialog.findViewById(R.id.popuplastname);
                TextView gender = dialog.findViewById(R.id.popupgender);
                TextView partylist = dialog.findViewById(R.id.popuppartylist);

                fname.setText(dataList.get(position).getFirstname());
                lname.setText(dataList.get(position).getLastname());
                gender.setText(dataList.get(position).getGender());
                partylist.setText(dataList.get(position).getPartylist().toUpperCase());
                Glide.with(holder.candidateImage.getContext()).load(((CandidatesData) dataList.get(position)).getProfileImage()).into(imageView);
                dialog.show();
            }
        });
    }

    private void handleRadioSelection(int position) {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            int previousSelectedPosition = selectedPosition;
            selectedPosition = position;

            notifyItemChanged(previousSelectedPosition, "payload");
            notifyItemChanged(selectedPosition, "payload");
        } else {
            selectedPosition = position;

            notifyItemChanged(selectedPosition, "payload");
        }
    }

    private void updateCardView(CardView cardView, CardView cardView1, boolean isSelected) {
        int colorResource = isSelected ? R.color.highlightColor : android.R.color.white;
        int borderColorResource = isSelected ? R.color.secondary_color : android.R.color.white;

        int color = ContextCompat.getColor(cardView1.getContext(), colorResource);
        int borderColor = ContextCompat.getColor(cardView.getContext(), borderColorResource);

        cardView1.setCardBackgroundColor(color);
        cardView.setCardBackgroundColor(borderColor);
    }

    public static CandidatesData getSelectedCandidate() {
        if (selectedPosition != RecyclerView.NO_POSITION) {
            return dataList.get(selectedPosition);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }
}
