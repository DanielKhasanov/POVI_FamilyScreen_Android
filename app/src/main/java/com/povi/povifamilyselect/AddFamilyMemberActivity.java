package com.povi.povifamilyselect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class AddFamilyMemberActivity extends Activity {

    private static final String EXTRA_FAMILY_EMAIL = "com.povi.family_adder.EMAIL";
    private static final String EXTRA_FAMILY_MESSAGE = "com.povi.family_adder.MESSAGE";
    public final static String EXTRA_FAMILY_NAME = "com.povi.family_adder.NAME";
    private String familyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View thisView = getLayoutInflater().inflate(R.layout.activity_add_family_member, null);
        setContentView(thisView);

        familyName = getIntent().getStringExtra(EXTRA_FAMILY_NAME);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height = dm.heightPixels;

//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (width * .8), (int) (height *.6));
//        params.add;
//        thisView.findViewById(R.id.button_add_family_member).setLayoutParams(params);
//

        getWindow().setLayout((int) (width * .8), RelativeLayout.LayoutParams.WRAP_CONTENT);

    }

    public void cancelOut(View view) {
        finish();
    }

    public void addFamilyMember(View view) {
        EditText editTextEmail = (EditText) findViewById(R.id.edit_family_member_email);
        String email = editTextEmail.getText().toString();

        EditText editTextMessage = (EditText) findViewById(R.id.edit_family_member_message);
        String message = editTextEmail.getText().toString();

        if (!email.equals("")){
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_FAMILY_EMAIL, email);
            resultIntent.putExtra(EXTRA_FAMILY_MESSAGE, message);
            resultIntent.putExtra(EXTRA_FAMILY_NAME, familyName);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }


}
