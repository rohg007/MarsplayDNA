package com.rohg007.android.marsplaydna.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rohg007.android.marsplaydna.R;
import com.rohg007.android.marsplaydna.models.Doc;
import com.rohg007.android.marsplaydna.ui.ArticleDialogFragment;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;
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
        View view = LayoutInflater.from(context).inflate(R.layout.doc_item,parent, false);
        return new DocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocViewHolder holder, int position) {
        holder.titleTextView.setText(docArrayList.get(position).getTitleDisplay());
        holder.dateTextView.setText(docArrayList.get(position).getPublicationDate());
        holder.publicationTextView.setText(docArrayList.get(position).getJournal());
        holder.authorsTextView.setText(docArrayList.get(position).getAuthorDisplay());
        holder.abstractTextView.setText(docArrayList.get(position).getAbstract());
        holder.articleTypeTextView.setText(docArrayList.get(position).getArticleType());

        handleAbstractViewClick(holder);

        setAnimationToItem(holder.itemView, position);
        holder.seeMoreTextView.setOnClickListener(v -> {
            viewModel.selectDoc(docArrayList.get(position));
            ArticleDialogFragment.display(fragmentManager);
        });
    }

    @Override
    public int getItemCount() {
        return docArrayList.size();
    }

    private void handleAbstractViewClick(@NonNull DocViewHolder holder){
        holder.abstractTextView.setOnClickListener(v -> {
            if (!(holder.abstractTextView.getLineCount()==1)) {
                holder.abstractTextView.setMaxLines((holder.abstractTextView.getMaxLines() == 2) ? 4 : 2);
                Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
                animation.setDuration(2000);
                holder.abstractTextView.startAnimation(animation);
                holder.seeMoreTextView.startAnimation(animation);
                if (holder.seeMoreTextView.getVisibility() == View.GONE) {
                    holder.seeMoreTextView.setVisibility(View.VISIBLE);
                } else
                    holder.seeMoreTextView.setVisibility(View.GONE);
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
