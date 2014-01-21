package com.jfmyers9;

public class LagerEntry {
    private String name;
    private String rating;
    private String aroma;
    private String appearance;
    private String taste;
    private String image;
    private String created_at;
    private long id;

    public LagerEntry(String name, String rating, String aroma,
                      String appearance, String taste, String image) {
        this.name = name;
        this.rating = rating;
        this.aroma = aroma;
        this.appearance = appearance;
        this.taste = taste;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
