package com.cognious.newsreader.Model;

public class News {

    private String title, thumbnailUrl, sourceLogoUrl;

    public News(String title, String thumbnailUrl, String sourceLogoUrl) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.sourceLogoUrl = sourceLogoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getSourceLogoUrl() {
        return sourceLogoUrl;
    }

    public void setSourceLogoUrl(String sourceLogoUrl) {
        this.sourceLogoUrl = sourceLogoUrl;
    }
}
