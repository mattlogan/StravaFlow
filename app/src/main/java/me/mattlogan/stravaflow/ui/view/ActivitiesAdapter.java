package me.mattlogan.stravaflow.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.mattlogan.stravaflow.R;
import me.mattlogan.stravaflow.api.model.StravaActivity;

public class ActivitiesAdapter extends BaseAdapter {

    private Context context;
    private OnActivitySelectedListener listener;
    private List<StravaActivity> activities;

    public interface OnActivitySelectedListener {
        public void onActivitySelected(StravaActivity stravaActivity);
    }

    public ActivitiesAdapter(Context context, OnActivitySelectedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setActivitiesList(List<StravaActivity> activities) {
        this.activities = activities;
    }

    static class ViewHolder {
        TextView nameText;
    }

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
            view = LayoutInflater.from(context).inflate(R.layout.activity_item, viewGroup, false);

            vh = new ViewHolder();
            vh.nameText = (TextView) view.findViewById(R.id.activity_name);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        final StravaActivity activity = activities.get(i);
        vh.nameText.setText(activity.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                listener.onActivitySelected(activity);
            }
        });

        return view;
    }
}
