package e.aman.studytutorialapp.services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import e.aman.studytutorialapp.model.TopCoursesModel;
import e.aman.studytutorialapp.utils.Constants;

public class TopLecturesRepo {

    private FirebaseDatabase database;
    private DatabaseReference topLecturesRef;
    List<TopCoursesModel> topCoursesModelList = new ArrayList<>();


    public void getTopCourses(final MyFirebaseService myFirebaseService)
    {
        database = FirebaseDatabase.getInstance();
        topLecturesRef = database.getReference().child(Constants.TOP_LECTURES_REF);

        topLecturesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot dataSnapshot : snapshot.getChildren())
               {
                   TopCoursesModel course = new TopCoursesModel();
                   course.setImagePath(dataSnapshot.child(Constants.IMAGE_PATH).getValue().toString());
                   course.setTitle(dataSnapshot.child(Constants.TITLE).getValue().toString());
                   course.setDescription(dataSnapshot.child(Constants.DESC).getValue().toString());
                   course.setAuthor(dataSnapshot.child(Constants.AUTHOR).getValue().toString());
                   course.setDate(dataSnapshot.child(Constants.DATE).getValue().toString());
                   Log.e("course", course.getAuthor().toString());
                   topCoursesModelList.add(course);
               }

                myFirebaseService.onSuccess(topCoursesModelList);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
