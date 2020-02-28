package com.rohg007.android.marsplaydna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.rohg007.android.marsplaydna.adapters.DocAdapter;
import com.rohg007.android.marsplaydna.databinding.ActivityMainBinding;
import com.rohg007.android.marsplaydna.models.Doc;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Doc> docArrayList = new ArrayList<>();
    private DocViewModel docViewModel;
    private DocAdapter adapter;
    private boolean scroll_down;
    private ConstraintLayout constraintLayout;
    private Button retryButton;
    private ActivityMainBinding activityMainBinding;
    private ImageView beerMugImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setUpActionBar();
        bindViews();
        docViewModel = ViewModelProviders.of(this).get(DocViewModel.class);
        docViewModel.init();

        //Handling Response
        if (!docArrayList.isEmpty()) {
            activityMainBinding.progressCircular.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.GONE);
            beerMugImageView.clearAnimation();
        }
        if (!internetEnabled()) {
            activityMainBinding.progressCircular.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.VISIBLE);
            retryButton.setVisibility(View.VISIBLE);
            showSnackBar("NO INTERNET CONNECTION!");
        }
        if (internetEnabled()) fetchData();
        handleRetryClick();

        if (adapter == null)
            setUpDocRecyclerView();
        else
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startBounceAnimation(beerMugImageView);
    }

    private void handleRetryClick() {
        retryButton.setOnClickListener(v -> {
            if (internetEnabled()) {
                docViewModel.recall();
                fetchData();
                activityMainBinding.progressCircular.setVisibility(View.VISIBLE);
            } else {
                showSnackBar("NO INTERNET CONNECTION");
            }
        });
    }

    private boolean internetEnabled() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private void fetchData() {
        docViewModel.getDocRepository().observe(this, responseData -> {
            List<Doc> docs = responseData.getResponse().getDocs();
            docArrayList.addAll(docs);

            if (!docArrayList.isEmpty()) {
                constraintLayout.setVisibility(View.GONE);
                beerMugImageView.clearAnimation();
            }
            else
                constraintLayout.setVisibility(View.VISIBLE);

            activityMainBinding.progressCircular.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        });
    }

    private void bindViews() {
        constraintLayout = findViewById(R.id.empty_view);
        retryButton = findViewById(R.id.retry_button);
        beerMugImageView = findViewById(R.id.beer_mug);
    }

    private void setUpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">" + "DNA Publications" + "</font>"));
        }
    }

    //function to set up recycler view with properties
    private void setUpDocRecyclerView() {
        adapter = new DocAdapter(docArrayList, MainActivity.this, getSupportFragmentManager(), docViewModel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        activityMainBinding.docRv.setLayoutManager(layoutManager);
        activityMainBinding.docRv.setAdapter(adapter);
        activityMainBinding.docRv.setItemAnimator(new DefaultItemAnimator());
        activityMainBinding.docRv.setNestedScrollingEnabled(true);
        handleRecyclerViewScroll();
    }

    //Hide & Show Action Bar onScroll
    private void handleRecyclerViewScroll() {
        activityMainBinding.docRv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (MainActivity.this.getSupportActionBar() != null) {
                    if (scroll_down)
                        MainActivity.this.getSupportActionBar().hide();
                    else
                        MainActivity.this.getSupportActionBar().show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0.1)
                    scroll_down = true;
                else if (dy < -5)
                    scroll_down = false;
            }
        });
    }

    //Function to handle Exit on Doubled Back Pressed
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        showSnackBar("Press BACK again to Exit!");
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    private View getView() {
        return findViewById(android.R.id.content);
    }

    private void showSnackBar(String s) {
        Snackbar.make(getView(), s, Snackbar.LENGTH_SHORT).show();
    }

    private void startBounceAnimation(View v){
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        v.startAnimation(bounce);
    }
}
