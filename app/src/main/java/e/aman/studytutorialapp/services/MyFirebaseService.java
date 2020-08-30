package e.aman.studytutorialapp.services;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface MyFirebaseService
{

        void onSuccess(List snapshot);
        void onSuccess(String e);
        void onFailure(String e);
}
