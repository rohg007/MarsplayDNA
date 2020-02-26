package com.rohg007.android.marsplaydna.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.rohg007.android.marsplaydna.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class ArticleDialogFragment extends DialogFragment {
    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView journalTextView;
    private TextView dateTextView;
    private TextView authorsTextView;
    private TextView abstractTextView;

    static final String TAG = ArticleDialogFragment.class.getSimpleName();
    private String mTitle;
    private String mDate;
    private String mJournal;
    private String mAuthors;
    private String mAbstractString;

    public static ArticleDialogFragment display(FragmentManager fragmentManager, String title, String journal, String date, String authors, String abstractString){
        ArticleDialogFragment articleDialogFragment = new ArticleDialogFragment(title, date, journal, authors, abstractString);
        articleDialogFragment.show(fragmentManager,TAG);
        return articleDialogFragment;
    }

    public ArticleDialogFragment(String mTitle, String mDate, String mJournal, String mAuthors, String mAbstractString) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mJournal = mJournal;
        this.mAuthors = mAuthors;
        this.mAbstractString = mAbstractString;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.article_fragment, container, false);
        toolbar = v.findViewById(R.id.toolbar);
        titleTextView = v.findViewById(R.id.publication_title);
        journalTextView = v.findViewById(R.id.journal_tv);
        dateTextView = v.findViewById(R.id.pub_date_tv);
        authorsTextView = v.findViewById(R.id.authors);
        abstractTextView = v.findViewById(R.id.abstract_detailed);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Publication Details");
        titleTextView.setText(mTitle);
        journalTextView.setText(mJournal);
        dateTextView.setText(mDate);
        authorsTextView.setText(mAuthors);
        abstractTextView.setText(mAbstractString);
    }
}
