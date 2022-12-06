package uk.ac.tees.b1325384.easyfood.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import uk.ac.tees.b1325384.easyfood.Common.Common;
import uk.ac.tees.b1325384.easyfood.HomeScreen;
import uk.ac.tees.b1325384.easyfood.Interface.ItemClickListener;
import uk.ac.tees.b1325384.easyfood.Model.Category;
import uk.ac.tees.b1325384.easyfood.R;
import uk.ac.tees.b1325384.easyfood.ViewHolder.MenuViewHolder;
import uk.ac.tees.b1325384.easyfood.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    FirebaseDatabase database;
    DatabaseReference category;

    TextView txtFullName;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //Initiate Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");


        //Set Name for user
//        View headerView = navigationView.getHeaderView(0);
//        txtFullName = (TextView)headerView.findViewById(R.id.txtFullName);
//        txtFullName.setText(Common.currentUser.getName());

        Log.d("Common user -> ", Common.currentUser.getName());
        //Load the Menu
        recyclerView = binding.recyclerView;
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        loadMenu();



//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    private void loadMenu() {

        FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.menu_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getContext()).load(model.getImage())
                        .into(viewHolder.imageView);
                final Category clickitem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(getContext(),""+clickitem.getName(), Toast.LENGTH_SHORT).show();
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