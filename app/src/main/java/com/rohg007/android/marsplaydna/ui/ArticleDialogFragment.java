package com.rohg007.android.marsplaydna.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rohg007.android.marsplaydna.R;
import com.rohg007.android.marsplaydna.databinding.ArticleFragmentBinding;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

public class ArticleDialogFragment extends DialogFragment {

    private static final String TAG = ArticleDialogFragment.class.getSimpleName();
    private ArticleFragmentBinding binding;

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
        binding = DataBindingUtil.inflate(inflater, R.layout.article_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.toolbar.setNavigationOnClickListener(v -> dismiss());
        DocViewModel viewModel = ViewModelProviders.of(this.getActivity()).get(DocViewModel.class);
        viewModel.getSelectedArticle().observe(this, docData->{
            binding.setDoc(docData);
        });
    }
}
