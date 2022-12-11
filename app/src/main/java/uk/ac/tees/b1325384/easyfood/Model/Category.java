package uk.ac.tees.b1325384.easyfood.Model;

import android.media.Image;

import java.io.Serializable;

public class Category implements Serializable {
    private String Name;
    private String Image;
    private String CategoryId;

    public Category() {
    }

    public Category(String name, String image, String categoryId) {
        Name = name;
        Image = image;
        CategoryId = categoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }


}
