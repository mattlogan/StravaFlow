package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mattlogan.stravaflow.R;

public class ActivitiesView extends RelativeLayout {

    @InjectView(R.id.activities_list) ListView listView;

    public ActivitiesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
    }

    public void setListViewAdapter(ActivitiesAdapter adapter) {
        listView.setAdapter(adapter);
    }
}
