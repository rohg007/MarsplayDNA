package com.rohg007.android.marsplaydna.adapters;

import com.rohg007.android.marsplaydna.BR;
import com.rohg007.android.marsplaydna.databinding.DocItemBinding;
import androidx.recyclerview.widget.RecyclerView;

class DocViewHolder extends RecyclerView.ViewHolder {

    DocItemBinding docItemBinding;

    DocViewHolder(DocItemBinding docItemBinding) {
        super(docItemBinding.getRoot());
        this.docItemBinding = docItemBinding;
    }

    void bind(Object object){
        docItemBinding.setVariable(BR.doc,object);
        docItemBinding.executePendingBindings();
    }

    void clearAnimation(){
        docItemBinding.getRoot().clearAnimation();
    }
}
