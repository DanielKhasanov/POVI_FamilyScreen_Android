package com.povi.povifamilyselect;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Daniel on 11/27/2015.
 */
public class FamilyAdapter<FamilyMember> extends RecyclerView.Adapter<FamilyMemberHolder> {

    private Context _context;
    private ArrayList<FamilyMember> _family;

    public FamilyAdapter(Context context, Family fam) {
        _context = context;
        _family = (ArrayList<FamilyMember>) fam.getMembers();
        if (_family == null) { this._family = new ArrayList<>();}

    }

    public void add(FamilyMember newFamilyMember) {
        if (newFamilyMember != null & !_family.contains(newFamilyMember)) {
            this._family.add(newFamilyMember);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return _family.size();
    }


    public Object getItem(int position) {
        return _family.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public FamilyMemberHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.family_list_container, null);
        FamilyMemberHolder holder = new FamilyMemberHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(FamilyMemberHolder holder, int position) {
        FamilyMember current = _family.get(position);
        //Context context = holder.memberIcon.getContext();
        holder.memberIcon.setImageResource(R.drawable.ic_people_placeholder);
    }
}
