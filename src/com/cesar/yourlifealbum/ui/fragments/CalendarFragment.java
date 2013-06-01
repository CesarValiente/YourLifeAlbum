package com.cesar.yourlifealbum.ui.fragments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.application.ClassWiring;
import com.cesar.yourlifealbum.components.adapters.CalendarAdapter;
import com.cesar.yourlifealbum.components.tasks.EyeemTasks.GetAllPhotosListener;
import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.utils.Log;

public class CalendarFragment extends Fragment implements GetAllPhotosListener {

    public Calendar mMonth;
    public CalendarAdapter mCalendarAdapter;
    public Handler mHandler;
    public ArrayList<String> mItems; // container to store some random calendar
                                     // items

    private final String CLASS_NAME = CalendarFragment.class.getSimpleName();

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClassWiring.getEyeemTasksManager().new GetAllPhotos(getActivity(), this)
                .execute();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.calendar_layout, container,
                false);

        mMonth = Calendar.getInstance();
        setupCalendar();

        mItems = new ArrayList<String>();
        mCalendarAdapter = new CalendarAdapter(getActivity(), mMonth);

        GridView gridview = (GridView) view
                .findViewById(R.id.calendar_gridview);
        gridview.setAdapter(mCalendarAdapter);

        mHandler = new Handler();
        mHandler.post(calendarUpdater);

        TextView title = (TextView) view.findViewById(R.id.calendar_title);
        title.setText(android.text.format.DateFormat
                .format("MMMM yyyy", mMonth));

        TextView previous = (TextView) view
                .findViewById(R.id.calendar_previous);
        previous.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (mMonth.get(Calendar.MONTH) == mMonth
                        .getActualMinimum(Calendar.MONTH)) {
                    mMonth.set((mMonth.get(Calendar.YEAR) - 1),
                            mMonth.getActualMaximum(Calendar.MONTH), 1);
                } else {
                    mMonth.set(Calendar.MONTH, mMonth.get(Calendar.MONTH) - 1);
                }
                refreshCalendar(view);
            }
        });

        TextView next = (TextView) view.findViewById(R.id.calendar_next);
        next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (mMonth.get(Calendar.MONTH) == mMonth
                        .getActualMaximum(Calendar.MONTH)) {
                    mMonth.set((mMonth.get(Calendar.YEAR) + 1),
                            mMonth.getActualMinimum(Calendar.MONTH), 1);
                } else {
                    mMonth.set(Calendar.MONTH, mMonth.get(Calendar.MONTH) + 1);
                }
                refreshCalendar(view);

            }
        });

        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View v,
                    final int position, final long id) {
                TextView date = (TextView) v.findViewById(R.id.calendar_date);
                if (date instanceof TextView && !date.getText().equals("")) {

                    Intent intent = new Intent();
                    String day = date.getText().toString();
                    if (day.length() == 1) {
                        day = "0" + day;
                    }
                    // return chosen date as string format
                    intent.putExtra(
                            "date",
                            android.text.format.DateFormat.format("yyyy-MM",
                                    mMonth) + "-" + day);
                    // setResult(RESULT_OK, intent);
                    // finish();
                }

            }
        });

        return view;
    }

    private void setupCalendar() {

        Date date = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String dateString = year + "-" + month + "-" + day;

        String[] dateArr = dateString.split("-"); // date format is yyyy-mm-dd
        mMonth.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[2]));
    }

    private void refreshCalendar(final View view) {
        TextView title = (TextView) view.findViewById(R.id.calendar_title);

        mCalendarAdapter.refreshDays();
        mCalendarAdapter.notifyDataSetChanged();
        mHandler.post(calendarUpdater); // generate some random calendar items

        title.setText(android.text.format.DateFormat
                .format("MMMM yyyy", mMonth));
    }

    private Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            mItems.clear();
            // format random values. You can implement a dedicated class to
            // provide real values
            for (int i = 0; i < 31; i++) {
                Random r = new Random();

                if (r.nextInt(10) > 6) {
                    mItems.add(Integer.toString(i));
                }
            }

            mCalendarAdapter.setItems(mItems);
            mCalendarAdapter.notifyDataSetChanged();
        }
    };

    @Override
    public void getAllPhotosCallback(final List<Photo> photoList) {

        if (photoList != null) {
            for (Photo item : photoList) {
                Log.d(CLASS_NAME, item.toString());
            }
        }

    }
}
