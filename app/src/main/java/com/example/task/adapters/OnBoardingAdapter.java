package com.example.task.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.task.R;
import com.example.task.Models.OnBoardingModel;

import java.util.List;

public class OnBoardingAdapter extends PagerAdapter {

    Context context;
    List<OnBoardingModel> listItems;

    public OnBoardingAdapter(Context context, List<OnBoardingModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {

        final View view = LayoutInflater.from(context).inflate(R.layout.onboarding_single_item , null );
        OnBoardingModel listItem = listItems.get(position);
        ImageView splashMainImage = (ImageView) view.findViewById(R.id.main_Image);
        TextView splashHeading = view.findViewById(R.id.text_Heading);
        splashMainImage.setImageResource(listItem.getOnBoardingImage());
        splashHeading.setText(listItem.getOnBoardingHeading());
        container.addView(view,0);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //remove object from conatiner
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }
}
