package com.graduation.doctroidmedical.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = "DatePickerFragment";
    private OnDatePicked onDatePicked;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onDatePicked = (OnDatePicked) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Initial Listener Before Implementation");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
        String dateText = String.valueOf(year) + '/' + String.valueOf(month) + '/' + dayOfMonth;
        long timeStamp = calendar.getTimeInMillis();
        Log.d(TAG, "onDateSet: " + timeStamp);
        onDatePicked.onDateSetDone(timeStamp, dateText);
    }

    public interface OnDatePicked {
        void onDateSetDone(long timeStamp, String dateText);
    }
}
