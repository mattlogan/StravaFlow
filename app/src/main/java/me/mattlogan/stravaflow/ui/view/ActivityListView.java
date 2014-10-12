package me.mattlogan.stravaflow.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import flow.Flow;
import me.mattlogan.stravaflow.StravaFlowActivity;
import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.model.StravaActivity;
import me.mattlogan.stravaflow.ui.presenter.ActivityListPresenter;
import me.mattlogan.stravaflow.ui.screen.ActivityDetailScreen;
import me.mattlogan.stravaflow.ui.screen.ActivityListScreen;

public class ActivityListView extends RelativeLayout {

    @InjectView(R.id.activities_list) ListView listView;

    Flow flow;
    ActivityListScreen screen;
    ActivityListPresenter presenter;

    List<StravaActivity> activities;
    ActivitiesAdapter adapter;

    public ActivityListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        StravaFlowActivity activity = (StravaFlowActivity) getContext();
        flow = activity.getFlow();
        screen = (ActivityListScreen) activity.getCurrentScreen();
        presenter = new ActivityListPresenter(this);
        if (activity.getActionBar() != null) {
            activity.getActionBar().setTitle(context.getString(R.string.app_name));
        }
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);
        adapter = new ActivitiesAdapter();
        listView.setAdapter(adapter);
        presenter.register();
        presenter.requestActivities();
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.unregister();
    }

    public void updateActivities(List<StravaActivity> activities) {
        this.activities = activities;
        adapter.notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView nameText;
    }

    class ActivitiesAdapter extends BaseAdapter {

        @Override public int getCount() {
            return activities == null ? 0 : activities.size();
        }

        @Override public Object getItem(int i) {
            return activities == null ? null : activities.get(i);
        }

        @Override public long getItemId(int i) {
            return 0;
        }

        @Override public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder vh;
            if (view == null) {
                LayoutInflater li = (LayoutInflater)
                        getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.activity_item, viewGroup, false);

                vh = new ViewHolder();
                vh.nameText = (TextView) view.findViewById(R.id.activity_name);
                view.setTag(vh);
            } else {
                vh = (ViewHolder) view.getTag();
            }

            final StravaActivity activity = activities.get(i);
            vh.nameText.setText(activity.getName());

            view.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View view) {
                    int id = Integer.parseInt(activity.getId());
                    flow.goTo(new ActivityDetailScreen(id, activity.getName()));
                }
            });

            return view;
        }
    }
}
