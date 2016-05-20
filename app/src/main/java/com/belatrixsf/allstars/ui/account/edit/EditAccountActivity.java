package com.belatrixsf.allstars.ui.account.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.belatrixsf.allstars.R;
import com.belatrixsf.allstars.entities.Employee;
import com.belatrixsf.allstars.ui.common.AllStarsActivity;

/**
 * Created by PedroCarrillo on 5/17/16.
 */
public class EditAccountActivity extends AllStarsActivity {

    public static final String EMPLOYEE_KEY = "_user_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityCompat.postponeEnterTransition(this);
        setNavigationToolbar();
        if (savedInstanceState == null) {
            Employee user = getIntent().getParcelableExtra(EMPLOYEE_KEY);
            replaceFragment(EditAccountFragment.newInstance(user), false);
        }
    }

}