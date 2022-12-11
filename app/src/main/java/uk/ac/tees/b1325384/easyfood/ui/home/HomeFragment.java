package uk.ac.tees.b1325384.easyfood.ui.home;

import android.content.Intent;
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
import uk.ac.tees.b1325384.easyfood.FoodList;
import uk.ac.tees.b1325384.easyfood.HomeScreen;
import uk.ac.tees.b1325384.easyfood.Interface.ItemClickListener;
import uk.ac.tees.b1325384.easyfood.MainActivity;
import uk.ac.tees.b1325384.easyfood.Model.Category;
import uk.ac.tees.b1325384.easyfood.R;
import uk.ac.tees.b1325384.easyfood.ViewHolder.MenuViewHolder;
import uk.ac.tees.b1325384.easyfood.databinding.FragmentHomeBinding;
