package me.mattlogan.stravaflow.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.StravaApi;

public class FragmentTestActivity extends Activity implements StravaApiInjector {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
    }

    @Override
    public StravaApi getStravaApi() {
        // todo: return mock retrofit api
        return null;
    }

    public void addFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }
}
