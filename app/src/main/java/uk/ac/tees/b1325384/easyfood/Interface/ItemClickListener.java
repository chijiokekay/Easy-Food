package uk.ac.tees.b1325384.easyfood.Interface;

import android.view.View;

import androidx.lifecycle.ViewTreeViewModelKt;

public interface ItemClickListener {
    void onClick(View view, int position, boolean isLongClick);
}
