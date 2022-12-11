package uk.ac.tees.b1325384.easyfood.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import uk.ac.tees.b1325384.easyfood.Common.Common;
import uk.ac.tees.b1325384.easyfood.Interface.ItemClickListener;
import uk.ac.tees.b1325384.easyfood.Model.Category;
import uk.ac.tees.b1325384.easyfood.Model.Food;
import uk.ac.tees.b1325384.easyfood.R;
import uk.ac.tees.b1325384.easyfood.ViewHolder.FoodListViewHolder;
import uk.ac.tees.b1325384.easyfood.ViewHolder.MenuViewHolder;
import uk.ac.tees.b1325384.easyfood.databinding.FragmentGalleryBinding;
import uk.ac.tees.b1325384.easyfood.ui.home.HomeFragmentDirections;


public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    FirebaseDatabase database;
    DatabaseReference foodList;

    TextView txtFullName;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Category pickFood = GalleryFragmentArgs.fromBundle(getArguments()).getFoodItem();



        //Initiate Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");


        //Set Name for user
//        View headerView = navigationView.getHeaderView(0);
//        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
//        txtFullName.setText(Common.currentUser.getName());

        Log.d("Common user -> ", Common.currentUser.getName());
        //Load the Menu
        recyclerView = binding.galleryRecyclerView;
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadFoodList(pickFood.getCategoryId());




//        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    private void loadFoodList(String categoryId) {
        FirebaseRecyclerAdapter<Food, FoodListViewHolder> adapter = new FirebaseRecyclerAdapter<Food, FoodListViewHolder>(Food.class, R.layout.food_list_item,FoodListViewHolder.class,foodList.orderByChild("MenuId"). equalTo(categoryId)) {
            @Override
            protected void populateViewHolder(FoodListViewHolder viewHolder, Food model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                viewHolder.txtDiscount.setText(model.getDiscount());
                viewHolder.txtPrice.setText("£" +model.getPrice());
                Picasso.with(getContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final Food fooditem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
//                        Toast.makeText(getContext(),""+clickitem.getName(), Toast.LENGTH_SHORT).show();
//                        Navigation.findNavController(view).navigate(HomeFragmentDirections.actionNavHomeToNavGallery2(model));

                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}