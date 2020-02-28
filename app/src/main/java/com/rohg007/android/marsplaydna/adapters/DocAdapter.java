package com.rohg007.android.marsplaydna.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rohg007.android.marsplaydna.R;
import com.rohg007.android.marsplaydna.databinding.DocItemBinding;
import com.rohg007.android.marsplaydna.models.Doc;
import com.rohg007.android.marsplaydna.ui.ArticleDialogFragment;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class DocAdapter extends RecyclerView.Adapter<DocViewHolder> {

    private ArrayList<Doc> docArrayList;
    private Context context;
    private int lastPostion=-1;
    private FragmentManager fragmentManager;
    private DocViewModel viewModel;

    public DocAdapter(ArrayList<Doc> docArrayList, Context context, FragmentManager fragmentManager, DocViewModel viewModel) {
        this.docArrayList = docArrayList;
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DocItemBinding docItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.doc_item,parent, false);
        return new DocViewHolder(docItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {
        Doc doc = docArrayList.get(position);
        holder.bind(doc);
        handleAbstractViewClick(holder.docItemBinding);
        handleSeeMoreClick(holder.docItemBinding, doc);
        setAnimationToItem(holder.docItemBinding.getRoot(), position);
    }

    @Override
    public int getItemCount() {
        return docArrayList.size();
    }

    private void handleSeeMoreClick(DocItemBinding docItemBinding, Doc doc){
        docItemBinding.seeMoreButton.setOnClickListener(v -> {
            viewModel.selectDoc(doc);
            ArticleDialogFragment.display(fragmentManager);
        });
    }

    private void handleAbstractViewClick(@NonNull DocItemBinding docItemBinding){
        docItemBinding.abstractTv.setOnClickListener(v -> {
            if (!(docItemBinding.abstractTv.getLineCount()==1)) {
                docItemBinding.abstractTv.setMaxLines((docItemBinding.abstractTv.getMaxLines() == 2) ? 4 : 2);
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                animation.setDuration(2000);
                docItemBinding.abstractTv.startAnimation(animation);
                docItemBinding.seeMoreButton.startAnimation(animation);
                if (docItemBinding.seeMoreButton.getVisibility() == View.GONE) {
                    docItemBinding.seeMoreButton.setVisibility(View.VISIBLE);
                } else
                    docItemBinding.seeMoreButton.setVisibility(View.GONE);
            }
        });
    }

    private void setAnimationToItem(View view, int position){
        if(position>lastPostion){
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            animation.setFillAfter(true);
            view.startAnimation(animation);
            lastPostion=position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull DocViewHolder holder) {
        holder.clearAnimation();
    }
}
