package com.povi.povifamilyselect;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Daniel on 11/27/2015.
 */
public class FamiliesAdapter extends ArrayAdapter<Family> {

    private Context context;
    private ArrayList<Family> families;
    public final static String EXTRA_FAMILY_NAME = "com.povi.family_adder.NAME";
    private static final String ADD_FAMILY_MEMBER_ACTION = "com.povifamilyselect.Families.ADDMEMBER";
    private static final int ADD_FAMILY_MEMBER_CODE = 159;
    HashMap<Family, FamilyAdapter> familyAdapters;

    public FamiliesAdapter(Context context, ArrayList<Family> fams) {
        super(context, R.layout.family_list, fams);
        this.context = context;
        this.families = fams;
        familyAdapters = new HashMap<>();
    }

    public void add(Family f) {
        if (!families.contains(f)) {
            familyAdapters.put(f, new FamilyAdapter<FamilyMember>(context, f));
            families.add(f);
            notifyDataSetChanged();
        }

    }

    @Override
    public int getCount() {
        return families.size();
    }

    @Override
    public Family getItem(int position) {

        return families.get(families.size() - position - 1); //reverse order
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View temp = convertView;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FamilyHolder holder = new FamilyHolder();

        if (convertView == null) {

            temp =  inflater.inflate(R.layout.family_list, null);

            TextView familyName = (TextView)temp.findViewById(R.id.family_name);
            RecyclerView memberScroll = (RecyclerView)temp.findViewById(R.id.family_member_scroll);
            ImageButton addMemberButton = (ImageButton)temp.findViewById(R.id.button_add_family_member);
            TextView numberOfMembers = (TextView) temp.findViewById(R.id.number_members);

            holder.name = familyName;
            holder.scroll = memberScroll;
            holder.button = addMemberButton;
            holder.numberOf = numberOfMembers;

            temp.setTag(holder);

        } else {
            holder = (FamilyHolder) temp.getTag();
        }

        final Family currentFamily = getItem(position);

        holder.name.setText(currentFamily.getName());
        holder.numberOf.setText(Integer.toString(currentFamily.getNumberOfMembers()) + " members");
        WrapLayoutManager m = new WrapLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        m.setChildSize(165); //in pixels! for dp try dividing by 3 for most sizes
        holder.scroll.setLayoutManager(m);
        holder.scroll.setAdapter(familyAdapters.get(currentFamily));
        //holder.button.setImageResource(R.drawable.ic_add_circle); // for dynamic image setting
        holder.button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, Families.class);
                        intent.putExtra(EXTRA_FAMILY_NAME, currentFamily.familyName);
                        intent.setAction(ADD_FAMILY_MEMBER_ACTION);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        context.startActivity(intent);
                    }
                }
        );

        return temp;
    }
}
