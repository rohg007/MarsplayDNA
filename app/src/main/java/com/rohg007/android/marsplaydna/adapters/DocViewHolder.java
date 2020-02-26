package com.rohg007.android.marsplaydna.adapters;

import android.view.View;
import android.widget.TextView;

import com.rohg007.android.marsplaydna.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DocViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView;
    TextView dateTextView;
    TextView publicationTextView;
    TextView authorsTextView;
    TextView abstractTextView;
    private View rootLayout;
    TextView seeMoreTextView;
    TextView articleTypeTextView;

    public DocViewHolder(@NonNull View itemView) {
        super(itemView);
        rootLayout=itemView;
        titleTextView = itemView.findViewById(R.id.doc_title_tv);
        dateTextView = itemView.findViewById(R.id.publication_date_tv);
        publicationTextView = itemView.findViewById(R.id.publication_tv);
        authorsTextView = itemView.findViewById(R.id.author_tv);
        abstractTextView = itemView.findViewById(R.id.abstract_tv);
        seeMoreTextView = itemView.findViewById(R.id.see_more_button);
        articleTypeTextView = itemView.findViewById(R.id.article_type_tv);
    }

    public void clearAnimation(){
        rootLayout.clearAnimation();
    }
}
