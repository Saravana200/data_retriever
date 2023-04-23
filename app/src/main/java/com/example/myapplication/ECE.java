package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class ECE extends Fragment {
    RecyclerView recyclerView;
    private DatabaseReference ref;
    private StorageReference str_ref;
    private ArrayList<data_retrieve> list;
    myAdapter ad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_e_c_e, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        str_ref= FirebaseStorage.getInstance().getReference();
        ref= FirebaseDatabase.getInstance().getReference();

        refresh();

        list=new ArrayList<data_retrieve>();
        clearall();
        Log.d("testing", "testing purpose");
        Log.d("testing storages",""+ref.child("CSE").toString());
        data_get();
    }

    private void refresh() {
        final int[] k = {5};
        str_ref.child("CSE").listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult)
            {
                for(StorageReference i: listResult.getItems())
                {
                    Log.d("storage name",i.getName());
                    str_ref.child("CSE").child(i.getName()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ref.child("posters").child("obj"+k[0]).child("description").setValue(i.getName());
                            ref.child("posters").child("obj"+k[0]).child("image").setValue(uri.toString());
                            k[0]++;
                        }
                    });
                }

            }
        });
    }

    private void data_get() {
        Query query=ref.child("posters");
        Log.d("testing",""+query.toString());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snap)
            {
                Log.d("testing", "testing purpose");
                clearall();
                for(DataSnapshot i:snap.getChildren())
                {

                    data_retrieve ob=new data_retrieve();
                    ob.setDesc(i.child("description").getValue().toString());
                    ob.setImage_url((i.child("image").getValue().toString()));
                    list.add(ob);
                    Log.d("testing", ""+list.toString());
                }
                ad=new myAdapter(getContext(),list);
                recyclerView.setAdapter(ad);
                ad.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("hello", "Failed to read value.", error.toException());
            }
        });
    }

    private void clearall() {
        if(list!=null) {
            list.clear();

            if(ad!=null)
            {
                ad.notifyDataSetChanged();
            }
        }
        list=new ArrayList<>();
    }
}