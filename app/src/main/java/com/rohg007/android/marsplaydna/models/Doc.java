package com.rohg007.android.marsplaydna.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Doc {
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
