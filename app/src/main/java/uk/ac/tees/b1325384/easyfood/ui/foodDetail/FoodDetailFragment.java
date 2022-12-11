package uk.ac.tees.b1325384.easyfood.ui.foodDetail;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import uk.ac.tees.b1325384.easyfood.Model.Food;
import uk.ac.tees.b1325384.easyfood.databinding.FragmentFoodDetailBinding;
import uk.ac.tees.b1325384.easyfood.ui.gallery.FoodListFragmentArgs;

public class FoodDetailFragment extends Fragment {

    private FoodDetailViewModel mViewModel;
    private FragmentFoodDetailBinding binding;





    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FoodDetailViewModel foodDetailModel =
                new ViewModelProvider(this).get(FoodDetailViewModel.class);

        binding = FragmentFoodDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Food foodItem = FoodDetailFragmentArgs.fromBundle(getArguments()).getFoodItem();
        Picasso.with(getContext()).load(foodItem.getImage())
                .into(binding.foodImage);


        binding.foodPrice.setText(foodItem.getPrice());
        binding.foodName.setText(foodItem.getName());
        binding.foodDescription.setText(foodItem.getDescription());


        return root;
    }



}