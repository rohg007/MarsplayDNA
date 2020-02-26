package com.rohg007.android.marsplaydna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rohg007.android.marsplaydna.adapters.DocAdapter;
import com.rohg007.android.marsplaydna.models.Doc;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Doc> docArrayList = new ArrayList<>();
    DocViewModel docViewModel;
    DocAdapter adapter;
    RecyclerView docRv;
    private int lastFirstVisibleItem=0;
    private boolean scroll_down;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpActionBar();
        bindViews();

        docViewModel = ViewModelProviders.of(this).get(DocViewModel.class);

        //Handling No Response
        if(!docArrayList.isEmpty()){
            progressBar.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.GONE);
        }

        docViewModel.init();
        docViewModel.getDocRepository().observe(this, responseData -> {
            List<Doc> docs = responseData.getResponse().getDocs();
            docArrayList.addAll(docs);
            if(!docArrayList.isEmpty())
                constraintLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            adapter.notifyDataSetChanged();
        });
        if(adapter==null)
            setUpDocRecyclerView();
        else
            adapter.notifyDataSetChanged();
    }

    private void bindViews(){
        docRv = findViewById(R.id.doc_rv);
        progressBar = findViewById(R.id.progress_circular);
        constraintLayout = findViewById(R.id.empty_view);
    }

    private void setUpActionBar(){
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setElevation(0);
            getSupportActionBar().setTitle(Html.fromHtml("<font color=\"black\">"+"DNA Publications"+"</font>"));
        }
    }

    private void setUpDocRecyclerView(){
        adapter = new DocAdapter(docArrayList, MainActivity.this, getSupportFragmentManager());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        docRv.setLayoutManager(layoutManager);
        docRv.setAdapter(adapter);
        docRv.setItemAnimator(new DefaultItemAnimator());
        //docRv.setNestedScrollingEnabled(true);

        docRv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(scroll_down)
                    MainActivity.this.getSupportActionBar().hide();
                else
                    MainActivity.this.getSupportActionBar().show();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0.1)
                    scroll_down=true;
                else if(dy<-5)
                    scroll_down=false;
            }
        });
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(progressBar,"Press BACK again to Exit!", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
