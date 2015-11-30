package com.povi.povifamilyselect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AddFamilyActivity extends Activity {
    public final static String EXTRA_FAMILY_NAME = "com.povi.family_adder.NAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    public void cancelOut(View view) {
        finish();
    }

    public void createFamily(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_family_name);
        String family_name = editText.getText().toString();
        if (!family_name.equals("")){
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_FAMILY_NAME, family_name);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }


}
