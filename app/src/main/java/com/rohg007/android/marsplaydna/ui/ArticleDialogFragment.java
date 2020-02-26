package com.rohg007.android.marsplaydna.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.rohg007.android.marsplaydna.R;
import com.rohg007.android.marsplaydna.models.Doc;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class ArticleDialogFragment extends DialogFragment {
    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView journalTextView;
    private TextView dateTextView;
    private TextView authorsTextView;
    private TextView abstractTextView;

    private static final String TAG = ArticleDialogFragment.class.getSimpleName();

    private Doc doc;
    private DocViewModel viewModel;

    public static void display(FragmentManager fragmentManager){
        ArticleDialogFragment articleDialogFragment = new ArticleDialogFragment();
        articleDialogFragment.show(fragmentManager,TAG);
    }

    public ArticleDialogFragment() {
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
            if(dialog.getWindow()!=null) {
                dialog.getWindow().setLayout(width, height);
                dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
            }
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Publication Details");
        viewModel = ViewModelProviders.of(this.getActivity()).get(DocViewModel.class);
        viewModel.getSelectedArticle().observe(this, this::displayItems);
    }

    public void displayItems(Doc doc){
        if(doc!=null) {
            titleTextView.setText(doc.getTitleDisplay());
            journalTextView.setText(doc.getJournal());
            dateTextView.setText(doc.getPublicationDate());
            authorsTextView.setText(doc.getAuthorDisplay());
            abstractTextView.setText(doc.getAbstract());
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
