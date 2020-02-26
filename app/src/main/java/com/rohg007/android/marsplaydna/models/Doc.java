package com.rohg007.android.marsplaydna.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Doc implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("journal")
    @Expose
    private String journal;
    @SerializedName("eissn")
    @Expose
    private String eissn;
    @SerializedName("publication_date")
    @Expose
    private String publicationDate;
    @SerializedName("article_type")
    @Expose
    private String articleType;
    @SerializedName("author_display")
    @Expose
    private List<String> authorDisplay = null;
    @SerializedName("abstract")
    @Expose
    private List<String> _abstract = null;
    @SerializedName("title_display")
    @Expose
    private String titleDisplay;
    @SerializedName("score")
    @Expose
    private Double score;

    protected Doc (Parcel in){
        id= in.readString();
        journal= in.readString();
        eissn=in.readString();
        publicationDate= in.readString();
        articleType= in.readString();
        authorDisplay= new ArrayList<>();
        in.readList(authorDisplay, String.class.getClassLoader());
        _abstract= new ArrayList<>();
        in.readList(_abstract, String.class.getClassLoader());
        titleDisplay= in.readString();
        score= in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(journal);
        dest.writeString(eissn);
        dest.writeString(publicationDate);
        dest.writeString(articleType);
        dest.writeList(authorDisplay);
        dest.writeList(_abstract);
        dest.writeString(titleDisplay);
        dest.writeDouble(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Doc> CREATOR = new Creator<Doc>() {
        @Override
        public Doc createFromParcel(Parcel in) {
            return new Doc(in);
        }

        @Override
        public Doc[] newArray(int size) {
            return new Doc[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getEissn() {
        return eissn;
    }

    public void setEissn(String eissn) {
        this.eissn = eissn;
    }

    public String getPublicationDate() {
        return publicationDate.substring(0,10);
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getAuthorDisplay() {
        String authors = authorDisplay.toString();
        if(authorDisplay.isEmpty())
            return "Author data not available";
        return authors.substring(1,authors.length()-1).trim();
    }

    public void setAuthorDisplay(List<String> authorDisplay) {
        this.authorDisplay = authorDisplay;
    }

    public String getAbstract() {
        String abstractString = _abstract.toString();
        if(_abstract.isEmpty()||abstractString.trim().length()==2)
            return "Abstract not available";
        return abstractString.substring(1,abstractString.length()-1).trim();
    }

    public void setAbstract(List<String> _abstract) {
        this._abstract = _abstract;
    }

    public String getTitleDisplay() {
        return titleDisplay;
    }

    public void setTitleDisplay(String titleDisplay) {
        this.titleDisplay = titleDisplay;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
