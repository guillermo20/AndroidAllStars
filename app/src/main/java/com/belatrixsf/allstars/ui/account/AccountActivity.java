package com.belatrixsf.allstars.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.belatrixsf.allstars.R;
import com.belatrixsf.allstars.ui.common.AllStarsActivity;

/**
 * Created by pedrocarrillo on 4/26/16.
 */
public class AccountActivity extends AllStarsActivity {

    public static final String USER_ID_KEY = "_user_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityCompat.postponeEnterTransition(this);
        setNavigationToolbar();
        if (savedInstanceState == null) {
            Integer userId = getIntent().getIntExtra(USER_ID_KEY, -1);
            replaceFragment(AccountFragment.newInstance(userId), false);
        }
    }

    public static void startActivityAnimatingProfilePic(Activity activity, ImageView photoImageView, Integer employeeId) {
        Intent intent = new Intent(activity, AccountActivity.class);
        intent.putExtra(AccountActivity.USER_ID_KEY, employeeId);
        ViewCompat.setTransitionName(photoImageView, activity.getString(R.string.transition_photo));
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, photoImageView, activity.getString(R.string.transition_photo));
        activity.startActivity(intent, options.toBundle());
    }

}
