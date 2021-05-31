package com.jaspreetsingh.CovidTracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.jaspreetsingh.travelmania.R;

import org.jetbrains.annotations.NotNull;

import pl.droidsonroids.gif.GifImageView;


class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    int images[] = {R.drawable.handwash,
            R.drawable.sanitizer,
            R.drawable.mask,
            R.drawable.vaccine,
    };

    int headings[] = {
            R.string.washhands,
            R.string.sanitizer,
            R.string.wearmask,
            R.string.vaccine
    };
    int descriptions[] = {
            R.string.washdesc,
            R.string.sanitizerdesc,
            R.string.washdesc,
            R.string.vaccinedesc,
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);
        GifImageView imageView = view.findViewById(R.id.sliderimg);
        TextView heading = view.findViewById(R.id.sliderheading);
        TextView desc = view.findViewById(R.id.sliderdesc);

        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}