package me.mattlogan.stravaflow.ui.activity;

import android.app.Fragment;

import me.mattlogan.stravaflow.ui.fragment.AuthFragment;

public class AuthActivity extends BaseActivity {

    @Override
    protected Fragment getInitialFragment() {
        return AuthFragment.newInstance();
    }
}
