package com.example.alfonso.dogsapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity {

    @BindView(R.id.image) ImageView image;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton button;
    @BindView(R.id.text) TextView text;
    @BindView(R.id.progress_indicator) ProgressBar progressBar;
    @BindView(R.id.toolbar_layout) CollapsingToolbarLayout toolbarLayout;
    DogPresenter presenter = new DogPresenter();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getDogInfo("bulldog");
        //getSupportActionBar().setTitle("");
        button.setOnClickListener(view -> getDogInfo("boxer"));
    }

    private void getDogInfo(String name){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getDog(name)
                .subscribe(
                        this::showDog,
                        error -> {/*Handle error*/}
                );
    }

    private void showDog(Dog dog){
        text.setText(dog.getText());
        progressBar.setVisibility(View.GONE);
        toolbarLayout.setTitle(dog.getName());
        Picasso.with(ScrollingActivity.this)
                .load(dog.getImage())
                .into(image);
    }
}