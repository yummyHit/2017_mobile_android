package com.test.yummy.myintroduction01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class PhotosActivity extends AppCompatActivity {
  private ArrayList<Integer> images;
  private BitmapFactory.Options options;
  private ViewPager viewPager;
  private View btnNext, btnPrev;
  private FragmentStatePagerAdapter adapter;
  private LinearLayout thumbnailsContainer;
  private final static int[] resourceIDs = new int[]{R.drawable.background03, R.drawable.background04,
    R.drawable.background05, R.drawable.background06, R.drawable.background07, R.drawable.background08,
    R.drawable.background09, R.drawable.background10, R.drawable.background11, R.drawable.background12,
    R.drawable.background13, R.drawable.background14, R.drawable.background15, R.drawable.background16};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photos);
    images = new ArrayList<>();

    //find view by id
    viewPager = (ViewPager) findViewById(R.id.view_pager);
    thumbnailsContainer = (LinearLayout) findViewById(R.id.container);
    btnNext = findViewById(R.id.next);
    btnPrev = findViewById(R.id.prev);

    btnPrev.setOnClickListener(onClickListener(0));
    btnNext.setOnClickListener(onClickListener(1));

    setImagesData();

    // init viewpager adapter and attach
    adapter = new ViewPagerAdapter(getSupportFragmentManager(), images);
    viewPager.setAdapter(adapter);

    inflateThumbnails();
  }

  private View.OnClickListener onClickListener(final int i) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (i > 0) {
          //next page
          if (viewPager.getCurrentItem() < viewPager.getAdapter().getCount() - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
          }
        } else {
          //previous page
          if (viewPager.getCurrentItem() > 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
          }
        }
      }
    };
  }

  private void setImagesData() {
    for (int i = 0; i < resourceIDs.length; i++) {
      images.add(resourceIDs[i]);
    }
  }

  private void inflateThumbnails() {
    for (int i = 0; i < images.size(); i++) {
      View imageLayout = getLayoutInflater().inflate(R.layout.item_image, null);
      ImageView imageView = (ImageView) imageLayout.findViewById(R.id.img_thumb);
      imageView.setOnClickListener(onChagePageClickListener(i));
      options = new BitmapFactory.Options();
      options.inSampleSize = 3;
      options.inDither = false;
      options.inJustDecodeBounds = false;
      Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images.get(i), options );
      //set to image view
      imageView.setImageBitmap(bitmap);
      //add imageview
      thumbnailsContainer.addView(imageLayout);
    }
  }

  private View.OnClickListener onChagePageClickListener(final int i) {
    return new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        viewPager.setCurrentItem(i);
      }
    };
  }
  public void on_home2BtnClicked(View v) {
    Intent myIntent = new Intent(PhotosActivity.this, MainActivity.class);
    startActivity(myIntent);
    finish();
  }
  public void on_return2BtnClicked(View v) {
    Intent myIntent = new Intent(PhotosActivity.this, HomeActivity.class);
    startActivity(myIntent);
    finish();
  }
}
