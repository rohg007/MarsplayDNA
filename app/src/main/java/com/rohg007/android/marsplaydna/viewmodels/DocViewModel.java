package com.rohg007.android.marsplaydna.viewmodels;

import com.rohg007.android.marsplaydna.models.ApiResponse;
import com.rohg007.android.marsplaydna.models.Doc;
import com.rohg007.android.marsplaydna.network.DocRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DocViewModel extends ViewModel {

    private MutableLiveData<ApiResponse> responseMutableLiveData;
    private DocRepository docRepository;
    private MutableLiveData<Doc> selectedArticle = new MutableLiveData<>();

    public void init(){
        if (responseMutableLiveData!=null)
            return;

        docRepository = DocRepository.getInstance();
        responseMutableLiveData = docRepository.getDocs();
    }

    public void selectDoc(Doc doc){
        selectedArticle.setValue(doc);
    }

    public MutableLiveData<Doc> getSelectedArticle(){
        return selectedArticle;
    }

    public void recall(){
        responseMutableLiveData = docRepository.getDocs();
    }

    public LiveData<ApiResponse> getDocRepository(){
        return responseMutableLiveData;
    }
}
