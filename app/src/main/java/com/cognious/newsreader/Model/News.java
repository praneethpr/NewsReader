package com.cognious.newsreader.Model;

import java.util.Date;

public class News {

    private String title, thumbnailUrl, sourceLogoUrl;

    private Date publishedAt;

    public News(String title, String thumbnailUrl, String sourceLogoUrl, Date publishedAt) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.sourceLogoUrl = sourceLogoUrl;
        this.publishedAt = publishedAt;
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

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
}
