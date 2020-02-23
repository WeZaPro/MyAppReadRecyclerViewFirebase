package example.com.myappreadrecyclerviewfirebase;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.opencensus.tags.Tags;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    View v;
    RecyclerView _myRecyclerView;
    MyAdapter myAdapter;
    DatabaseReference databaseReference;

    ArrayList<Users> listData = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (v == null){
            v = inflater.inflate(R.layout.fragment_main, container, false);

            _myRecyclerView = v.findViewById(R.id.myRecyclerView);
            _myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren() ){
                        Users users = dataSnapshot1.getValue(Users.class);
                        listData.add(users);
                    }

                    myAdapter = new MyAdapter(getActivity(),listData);
                    _myRecyclerView.setAdapter(myAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getActivity(),"error "+databaseError,Toast.LENGTH_SHORT).show();
                }
            });

        }

        return v;
    }

}
