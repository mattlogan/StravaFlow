package util;

import android.app.Fragment;
import android.test.ActivityInstrumentationTestCase2;

public abstract class FragmentTestCase
        extends ActivityInstrumentationTestCase2<FragmentTestActivity> {

    public FragmentTestCase() {
        super(FragmentTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity().addFragment(getFragment());
    }

    protected abstract Fragment getFragment();
}
