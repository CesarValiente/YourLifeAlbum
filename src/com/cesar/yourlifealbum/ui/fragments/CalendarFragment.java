package com.cesar.yourlifealbum.ui.fragments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.cesar.yourlifealbum.R;
import com.cesar.yourlifealbum.application.AppConstants;
import com.cesar.yourlifealbum.components.adapters.CalendarAdapter;
import com.cesar.yourlifealbum.components.adapters.CalendarAdapter.ClickOnCalendar;
import com.cesar.yourlifealbum.components.tasks.GetAllPhotosTask;
import com.cesar.yourlifealbum.components.tasks.GetAllPhotosTask.GetAllPhotosListener;
import com.cesar.yourlifealbum.data.db.models.Photo;
import com.cesar.yourlifealbum.ui.activities.ViewPhotosFragmentActivity;
import com.cesar.yourlifealbum.utils.DateUtils;
import com.cesar.yourlifealbum.utils.Log;

public class CalendarFragment extends Fragment implements GetAllPhotosListener,
        ClickOnCalendar {

    private final String CLASS_NAME = CalendarFragment.class.getSimpleName();

    private Calendar mMonth;
    private CalendarAdapter mCalendarAdapter;
    private Handler mHandler;
    private List<Photo> mPhotoList;

    // Widgets
    private GridView mGridView;
    private TextView mTitleMonth;
    private TextView mPreviousMonth;
    private TextView mNextMonth;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetAllPhotosTask(getActivity(), this).execute();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
            final ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.calendar_layout, container,
                false);

        mMonth = Calendar.getInstance();
        setupCalendar();

        mCalendarAdapter = new CalendarAdapter(getActivity(), mMonth, this);

        mGridView = (GridView) view.findViewById(R.id.calendar_gridview);
        mGridView.setAdapter(mCalendarAdapter);

        mTitleMonth = (TextView) view.findViewById(R.id.calendar_title);
        mTitleMonth.setText(android.text.format.DateFormat.format("MMMM yyyy",
                mMonth));

        mPreviousMonth = (TextView) view.findViewById(R.id.calendar_previous);
        mPreviousMonth.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (mMonth.get(Calendar.MONTH) == mMonth
                        .getActualMinimum(Calendar.MONTH)) {
                    mMonth.set((mMonth.get(Calendar.YEAR) - 1),
                            mMonth.getActualMaximum(Calendar.MONTH), 1);
                } else {
                    mMonth.set(Calendar.MONTH, mMonth.get(Calendar.MONTH) - 1);
                }
                refreshCalendar();
            }
        });

        mNextMonth = (TextView) view.findViewById(R.id.calendar_next);
        mNextMonth.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                if (mMonth.get(Calendar.MONTH) == mMonth
                        .getActualMaximum(Calendar.MONTH)) {
                    mMonth.set((mMonth.get(Calendar.YEAR) + 1),
                            mMonth.getActualMinimum(Calendar.MONTH), 1);
                } else {
                    mMonth.set(Calendar.MONTH, mMonth.get(Calendar.MONTH) + 1);
                }
                refreshCalendar();

            }
        });

        mGridView.setOnItemClickListener(new OnItemClickListener() {
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

    private void refreshCalendar() {

        mCalendarAdapter.refreshDays();
        mCalendarAdapter.setItems(getPhotosOfMonth());
        mCalendarAdapter.notifyDataSetChanged();

        mTitleMonth.setText(android.text.format.DateFormat.format("MMMM yyyy",
                mMonth));
    }

    @Override
    public void getAllPhotosCallback(final List<Photo> photoList) {

        if (photoList != null) {
            mPhotoList = photoList;
            refreshCalendar();
        }
    }

    @SuppressWarnings("deprecation")
    public List<Photo> getPhotosOfMonth() {

        List<Photo> photosOfTheMonth = new ArrayList<Photo>();
        if (mPhotoList != null) {
            Calendar calendar = Calendar.getInstance();
            for (Photo item : mPhotoList) {
                Date date = DateUtils.dateFromRFC3339String(item.getUpdated());
                calendar.setTime(date);
                if (calendar.get(Calendar.MONTH) == mMonth.get(Calendar.MONTH)
                        && calendar.get(Calendar.YEAR) == mMonth
                                .get(Calendar.YEAR)) {
                    Log.d(CLASS_NAME, "found one!!");
                    item.setDay(calendar.get(Calendar.DAY_OF_MONTH));
                    item.setMonth(calendar.get(Calendar.MONTH) + 1);
                    item.setYear(calendar.get(Calendar.YEAR));
                    photosOfTheMonth.add(item);
                }
            }
        }
        return photosOfTheMonth;
    }

    @Override
    public void viewPhotos(final ArrayList<Photo> photoList) {

        // // Create new fragment and transaction
        // ViewPhotosFragment viewPhotosFragment = new ViewPhotosFragment(
        // photoList);
        // FragmentTransaction transaction = ((MainFragmentActivity)
        // getActivity())
        // .getSupportFragmentManager().beginTransaction();
        //
        // // Replace whatever is in the fragment_container view with this
        // // fragment,
        // // and add the transaction to the back stack
        // transaction.replace(R.id.main_layout, viewPhotosFragment);
        //
        // // Commit the transaction
        // transaction.commit();

        Intent intent = new Intent(getActivity(),
                ViewPhotosFragmentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(AppConstants.Messages.DAY_PHOTOS,
                photoList);
        intent.putExtra(AppConstants.Messages.BUNDLE, bundle);

        startActivity(intent);

    }
}
