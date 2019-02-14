package com.graduation.doctroidmedical.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.doctroidmedical.R;

public class SliderAdapter extends PagerAdapter {
    private Context context;

    public SliderAdapter(Context context) {
        this.context = context;
    }


    private int[] slideImages = {
            R.drawable.surgeon,
            R.drawable.medicine,
            R.drawable.medical_history,

    };

    private String[] slideHeaders = {
            "Hospitals",
            "Appointments",
            "Medical News"
    };

    private String[] bodyStrings = {
            "Find hospitals in Egypt and near by.",
            "Book appointments and get best treatment on one tap.",
            "Your source for health news, Latest headlines news."
    };

    @Override
    public int getCount() {
        return slideHeaders.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView iconView = view.findViewById(R.id.icon_image);
        TextView headerTextView = view.findViewById(R.id.header_tv);
        TextView bodyTextView = view.findViewById(R.id.body_tv);

        iconView.setImageResource(slideImages[position]);
        headerTextView.setText(slideHeaders[position]);
        bodyTextView.setText(bodyStrings[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
