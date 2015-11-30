package com.povi.povifamilyselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.HashMap;

public class Families extends AppCompatActivity {
    private static final int ADD_FAMILY_CODE = 314;
    private static final int ADD_FAMILY_MEMBER_CODE = 159;
    public final static String EXTRA_FAMILY_NAME = "com.povi.family_adder.NAME";
    private static final String EXTRA_FAMILY_EMAIL = "com.povi.family_adder.EMAIL";
    private static final String EXTRA_FAMILY_MESSAGE = "com.povi.family_adder.MESSAGE";
    private static final String ADD_FAMILY_MEMBER_ACTION = "com.povifamilyselect.Families.ADDMEMBER";

    View dimLights;

    private HashMap<String, Family> name_to_family;
    private ArrayList<Family> families_chronological;
    FamiliesAdapter familiesAdapter;


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.families_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_families);
        dimLights = findViewById(R.id.fadePopup);
        dimLights.setVisibility(View.INVISIBLE);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_navigation_bars);

        setSupportActionBar(myToolbar);
        name_to_family = new HashMap<String, Family>();
        families_chronological = new ArrayList<Family>();

        familiesAdapter = new FamiliesAdapter(this, families_chronological);
        addFamily("Aunts and Uncles");
        addFamily("Grandparents");

        ListView listView = (ListView) findViewById(R.id.family_list);
        listView.setAdapter(familiesAdapter);
    }

    public void queryAddFamily(){
        dimLights.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AddFamilyActivity.class);
        startActivityForResult(intent, ADD_FAMILY_CODE);
    }

    public void queryAddFamilyMember(String familyName){
        dimLights.setVisibility(View.VISIBLE);

        Intent intent = new Intent(this, AddFamilyMemberActivity.class);

        intent.putExtra(EXTRA_FAMILY_NAME, familyName);
        startActivityForResult(intent, ADD_FAMILY_MEMBER_CODE);
    }

    public void addFamily(String familyName) {
        Log.d("entering", "Entered addFamily");
        if (!name_to_family.containsKey(familyName)) {
            Family newFamily = new Family(familyName);
            name_to_family.put(familyName, newFamily);
            familiesAdapter.add(newFamily);
            familiesAdapter.notifyDataSetChanged();
        }
    }

    public void addFamilyMember(String email, String message, String familyName) {
        if (!name_to_family.containsKey(email)) {
            FamilyMember newMember = new FamilyMember(email, email, message);
            Family temp = name_to_family.get(familyName);
            if (temp == null) {
                temp = new Family(familyName);
                name_to_family.put(familyName, temp);
                familiesAdapter.add(temp);
                familiesAdapter.notifyDataSetChanged();
            }
            FamilyAdapter f = familiesAdapter.familyAdapters.get(temp);
            f.add(newMember);
            familiesAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_family) {
            queryAddFamily();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (ADD_FAMILY_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    addFamily(data.getStringExtra(EXTRA_FAMILY_NAME));
                }
                dimLights.setVisibility(View.INVISIBLE);
                break;
            }

            case (ADD_FAMILY_MEMBER_CODE) : {
                if (resultCode == Activity.RESULT_OK) {
                    addFamilyMember(data.getStringExtra(EXTRA_FAMILY_EMAIL),
                            data.getStringExtra(EXTRA_FAMILY_MESSAGE),
                            data.getStringExtra(EXTRA_FAMILY_NAME));
                }
                dimLights.setVisibility(View.INVISIBLE);
                break;
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (ADD_FAMILY_MEMBER_ACTION.equals(intent.getAction())) {
            Log.d("generic", "GOTEM BIGTIME");
            queryAddFamilyMember(intent.getStringExtra(EXTRA_FAMILY_NAME));
        }
    }


}
