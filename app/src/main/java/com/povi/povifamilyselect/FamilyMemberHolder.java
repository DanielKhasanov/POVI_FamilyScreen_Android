package com.povi.povifamilyselect;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Daniel on 11/27/2015.
 */
public class FamilyMemberHolder extends RecyclerView.ViewHolder {
    public ImageView memberIcon;
    public TextView memberText;
    public FamilyMemberHolder(View itemView) {
        super(itemView);
        memberIcon = (ImageView) itemView.findViewById(R.id.iconView);
    }
}
