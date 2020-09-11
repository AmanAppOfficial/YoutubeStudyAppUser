package e.aman.studytutorialapp.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import e.aman.studytutorialapp.R;
import e.aman.studytutorialapp.model.CourseModel;
import e.aman.studytutorialapp.model.FeedModel;
import e.aman.studytutorialapp.model.TopCoursesModel;
import e.aman.studytutorialapp.model.UpcomingModel;
import e.aman.studytutorialapp.services.MyFirebaseService;
import e.aman.studytutorialapp.services.TopLecturesRepo;
import e.aman.studytutorialapp.ui.screen.MainActivity;
import xyz.schwaab.avvylib.AvatarView;


public class ExploreFragment extends Fragment implements MyFirebaseService {

    private LinearLayout feedsLayout , upcomingLayout , topCoursesLayout , courseLayout;
    private List<FeedModel> feedList = new ArrayList<>();
    private List<UpcomingModel> upcomingList = new ArrayList<>();
    private List<TopCoursesModel> topCoursesList = new ArrayList<>();
    private List<CourseModel> coursesList = new ArrayList<>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);


        String title = "Explore";
        SpannableString s = new SpannableString(title);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(s);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        initFields(view);

        setUpFeedList(container);
        setUpTopCoursesList(container);
        setUpcomingList(container);
        setCourseList(container);


        return view;

    }

    private void setCourseList(ViewGroup container) {

        coursesList.add(new CourseModel("" , "NOUN" , "10"));
        coursesList.add(new CourseModel("" , "TENSE" , "10"));

        setCourseLayout(container);


    }

    private void setUpTopCoursesList(ViewGroup container) {

        new TopLecturesRepo(container).getTopCourses(this);

    }


    private void setCourseLayout(ViewGroup container) {
        int length = (coursesList.size() > 4) ? 4 : coursesList.size();

        for(int i=0 ; i<length ; i++)
        {
            CardView cardView = new CardView(container.getContext());
            cardView.setUseCompatPadding(true);
            cardView.setPreventCornerOverlap(true);
            cardView.setCardBackgroundColor(Color.WHITE);
            cardView.setCardElevation(9f);
            cardView.setRadius(20);
            cardView.setMaxCardElevation(9f);
            LinearLayout.LayoutParams cardParam = new LinearLayout.LayoutParams(650, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardParam.setMargins(0 , 0 , 20 , 20);
            cardView.setLayoutParams(cardParam);


            //Imageview for icon image...
            LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(650, 370);
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Picasso.get().load(Uri.parse(topCoursesList.get(i).getImagePath())).into(imageView);
            imageView.setLayoutParams(imageParam);
            imageView.setImageResource(R.drawable.try_pro);


            //Title Layout....
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            titleParams.setMargins(30 , 20 , 0 , 0);
            LinearLayout titleLayout = new LinearLayout(container.getContext());
            titleLayout.setLayoutParams(titleParams);
            TextView titleText = new TextView(container.getContext());
            titleText.setText(coursesList.get(i).getTitle());
            titleText.setTextColor(Color.BLACK);
            Typeface face = Typeface.SERIF;
            titleText.setTypeface(face);
            titleText.setTypeface(null , Typeface.BOLD);
            titleLayout.addView(titleText);

            //Total lectures in course...
            RelativeLayout descLayout = new RelativeLayout(container.getContext());
            RelativeLayout.LayoutParams lpDesc = new RelativeLayout.LayoutParams(600, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpDesc.setMargins(30 , 60 , 10 , 20);
            lpDesc.addRule(RelativeLayout.ALIGN_RIGHT);
            TextView descText = new TextView(container.getContext());
            descText.setText(coursesList.get(i).getCountVideos() + " videos");
            descText.setTextColor(Color.BLACK);

            descLayout.setLayoutParams(lpDesc);
            descLayout.addView(descText);


            //Linear layout for elements in cards...
            LinearLayout mainLayout = new LinearLayout(container.getContext());
            mainLayout.setOrientation(LinearLayout.VERTICAL);


            mainLayout.addView(imageView);
            mainLayout.addView(titleLayout);
            mainLayout.addView(descLayout);

            //add imageview to cardview...
            cardView.addView(mainLayout);

            courseLayout.addView(cardView);

        }
    }



    private void setUpTopCoursesLayout(ViewGroup container)
    {
        int length = (topCoursesList.size() > 2) ? 2 : topCoursesList.size();
        for(int i=0 ; i<length ; i++)
        {
            CardView cardView = new CardView(container.getContext());
            cardView.setUseCompatPadding(true);
            cardView.setPreventCornerOverlap(true);
            cardView.setCardBackgroundColor(Color.WHITE);
            cardView.setCardElevation(6f);
            cardView.setRadius(20);
            cardView.setMaxCardElevation(6f);
            LinearLayout.LayoutParams cardParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardParam.setMargins(0 , 30 , 0 , 20);
            cardView.setLayoutParams(cardParam);



            //ImageView
            LinearLayout.LayoutParams imageParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.get().load(Uri.parse(topCoursesList.get(i).getImagePath())).into(imageView);
            imageView.setLayoutParams(imageParam);

            //LinearLayout for black strip - dateLayout
            RelativeLayout.LayoutParams dateParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dateParams.setMargins(0 , 20 , 0 , 0);
            RelativeLayout dateLayout = new RelativeLayout(container.getContext());
            dateLayout.setBackgroundColor(Color.BLACK);
            dateLayout.setAlpha(0.8f);
            TextView dateTextView = new TextView(container.getContext());
            dateTextView.setPadding(10 , 10 , 10 , 10);
            dateTextView.setText("  Posted at " + topCoursesList.get(i).getDate());
            dateTextView.setTextColor(Color.WHITE);
            dateLayout.setLayoutParams(dateParams);
            dateLayout.addView(dateTextView);

            //Relative layout for date and image....
            RelativeLayout.LayoutParams imageLayoutParam = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            imageLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_END);
            imageLayoutParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            RelativeLayout imageLayout = new RelativeLayout(container.getContext());
            imageLayout.setLayoutParams(imageLayoutParam);
            imageLayout.addView(imageView);
            RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams)dateLayout.getLayoutParams();
            param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            dateLayout.setLayoutParams(param);
            imageLayout.addView(dateLayout);



            //Content linear layout
            LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            contentParams.setMargins(30 , 20 , 30 , 0);
            LinearLayout contentLayout = new LinearLayout(container.getContext());
            contentLayout.setLayoutParams(contentParams);
            contentLayout.setOrientation(LinearLayout.VERTICAL);


            //Title
            LinearLayout titleLayout = new LinearLayout(container.getContext());
            TextView titleText = new TextView(container.getContext());
            titleText.setText(topCoursesList.get(i).getTitle());
            titleText.setTextColor(Color.GRAY);
            Typeface face = Typeface.SERIF;
            titleText.setTypeface(face);
            titleText.setTypeface(null , Typeface.BOLD);
            titleLayout.addView(titleText);


            LinearLayout.LayoutParams descAndButtonParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout descAndButton = new LinearLayout(container.getContext());
            descAndButton.setOrientation(LinearLayout.HORIZONTAL);
            descAndButton.setLayoutParams(descAndButtonParam);

            //description
            LinearLayout descLayout = new LinearLayout(container.getContext());
            LinearLayout.LayoutParams lpDesc = new LinearLayout.LayoutParams(570, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpDesc.setMargins(0 , 10 , 10 , 0);
            TextView descText = new TextView(container.getContext());
            descText.setText(topCoursesList.get(i).getDescription());
            descText.setTextColor(Color.BLACK);
            descText.setTextSize(16f);
            descText.setTypeface(face);
            descText.setTypeface(null , Typeface.BOLD);

            descLayout.setLayoutParams(lpDesc);
            descLayout.addView(descText);

            //View button
            RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(280, 145);
            viewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            CardView viewCard = new CardView(container.getContext());
            viewCard.setLayoutParams(viewParams);
            viewCard.setUseCompatPadding(true);
            viewCard.setPreventCornerOverlap(true);
            viewCard.setCardBackgroundColor(Color.parseColor("#FFA500"));
            viewCard.setCardElevation(6f);
            viewCard.setRadius(60);
            viewCard.setMaxCardElevation(6f);

            RelativeLayout.LayoutParams viewTextParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            viewTextParams.setMargins(0 , 20 , 0 , 0);
            viewTextParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            RelativeLayout viewTextLayout = new RelativeLayout(container.getContext());
            TextView viewText = new TextView(container.getContext());
            viewText.setText("View");
            viewText.setTextSize(17f);
            viewText.setTextColor(Color.WHITE);
            viewText.setTypeface(face);
            viewText.setTypeface(null , Typeface.BOLD);
            viewText.setLayoutParams(viewTextParams);
            viewTextLayout.addView(viewText);

            viewCard.addView(viewTextLayout);

            descAndButton.addView(descLayout);
            descAndButton.addView(viewCard);


            //author
            LinearLayout authorLayout = new LinearLayout(container.getContext());
            LinearLayout.LayoutParams lpAuthor = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpAuthor.setMargins(0 , 10 , 10 , 0);
            TextView authorText = new TextView(container.getContext());
            authorText.setText( "By " + topCoursesList.get(i).getAuthor());
            authorText.setTextColor(Color.GRAY);
            authorLayout.setLayoutParams(lpAuthor);
            authorLayout.addView(authorText);


            //Add contents to content layout....
            contentLayout.addView(titleLayout);
            contentLayout.addView(descAndButton);
            contentLayout.addView(authorLayout);


            //Divider
            ImageView divider = new ImageView(container.getContext());
            RelativeLayout.LayoutParams lpDiv =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 3);
            lpDiv.setMargins(30 , 40 , 30 , 0);
            lpDiv.addRule(RelativeLayout.ALIGN_PARENT_END);
            divider.setLayoutParams(lpDiv);
            divider.setBackgroundColor(Color.GRAY);


            //Master text
            LinearLayout.LayoutParams masterParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout masterLayout = new LinearLayout(container.getContext());
            masterLayout.setLayoutParams(masterParams);
            masterLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams lpMaster = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpMaster.setMargins(30 , 30 , 10 , 50);
            TextView masterText = new TextView(container.getContext());
            masterText.setText("  Master class");
            masterText.setTextColor(Color.GRAY);
            masterLayout.setLayoutParams(lpMaster);


            ImageView starImage = new ImageView(container.getContext());
            starImage.setImageResource(R.drawable.ic_star_black_24dp);


            masterLayout.addView(starImage);
            masterLayout.addView(masterText);





            //Linear layout vertical for image and content

            LinearLayout mainLayout = new LinearLayout(container.getContext());
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.addView(imageLayout);
            mainLayout.addView(contentLayout);
            mainLayout.addView(divider);
            mainLayout.addView(masterLayout);




            //assign main layout to card
            cardView.addView(mainLayout);



            //assign to layout
            topCoursesLayout.addView(cardView);





        }
    }




    private void setUpcomingLayout(ViewGroup container) {


        int length = (upcomingList.size() > 2) ? 2 : upcomingList.size();
        for(int i=0 ; i<length ; i++)
        {
            //main card
            CardView cardView = new CardView(container.getContext());
            cardView.setUseCompatPadding(true);
            cardView.setPreventCornerOverlap(true);
            cardView.setCardBackgroundColor(Color.WHITE);
            cardView.setCardElevation(6f);
            cardView.setRadius(20);
            cardView.setMaxCardElevation(6f);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0 , 30 , 0 , 20);
            cardView.setLayoutParams(lp);

            LinearLayout mainLayout = new LinearLayout(container.getContext());
            mainLayout.setOrientation(LinearLayout.HORIZONTAL);




            //bell icon
            RelativeLayout.LayoutParams lpNotify = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpNotify.setMargins(10 , 10 , 10 , 0);
            ImageView bellIcon = new ImageView(container.getContext());
            bellIcon.setImageResource(R.drawable.notify);
            bellIcon.setLayoutParams(lpNotify);
            LinearLayout linearLayoutNotify = new LinearLayout(container.getContext());
            lpNotify.addRule(RelativeLayout.ALIGN_PARENT_START);
            linearLayoutNotify.addView(bellIcon);




            //Icon card
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(180, 150);
            lp1.setMargins(50 , 30 , 50 , 30);
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(R.drawable.try_pro);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            CardView iconCard = new CardView(container.getContext());
            iconCard.setLayoutParams(lp1);
            iconCard.setElevation(9);
            iconCard.addView(imageView);



            //vertical layout
            LinearLayout verticalLayout = new LinearLayout(container.getContext());
            verticalLayout.setOrientation(LinearLayout.VERTICAL);


            //title
            LinearLayout titleLayout = new LinearLayout(container.getContext());
            LinearLayout.LayoutParams lpTitle = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpTitle.setMargins(10 , 30 , 10 , 0);
            TextView titleText = new TextView(container.getContext());
            titleText.setText(upcomingList.get(i).getTitle());
            titleText.setTextColor(Color.GRAY);
            Typeface face = Typeface.SERIF;
            titleText.setTypeface(face);
            titleText.setTypeface(null , Typeface.BOLD);
            titleLayout.setLayoutParams(lpTitle);
            titleLayout.addView(titleText);


            //description
            LinearLayout descLayout = new LinearLayout(container.getContext());
            LinearLayout.LayoutParams lpDesc = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpDesc.setMargins(0 , 30 , 10 , 20);
            TextView descText = new TextView(container.getContext());
            descText.setText(upcomingList.get(i).getDesc());
            descText.setTextColor(Color.BLACK);

            descLayout.setLayoutParams(lpDesc);
            descLayout.addView(descText);


            //author
            LinearLayout authorLayout = new LinearLayout(container.getContext());
            LinearLayout.LayoutParams lpAuthor = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpAuthor.setMargins(0 , 30 , 10 , 0);
            TextView authorText = new TextView(container.getContext());
            authorText.setText( "By " + upcomingList.get(i).getAuthor());
            authorText.setTextColor(Color.GRAY);
            authorLayout.setLayoutParams(lpAuthor);
            authorLayout.addView(authorText);



            //add to vertical column

            verticalLayout.addView(titleLayout);
            verticalLayout.addView(descLayout);
            verticalLayout.addView(authorLayout);

            //add to horizontal row
            mainLayout.addView(iconCard);
            mainLayout.addView(verticalLayout);


            //Divider
            ImageView divider = new ImageView(container.getContext());
            RelativeLayout.LayoutParams lpDiv =
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 3);
            lpDiv.setMargins(0 , 40 , 0 , 0);
            lpDiv.addRule(RelativeLayout.ALIGN_PARENT_END);
            divider.setLayoutParams(lpDiv);
            divider.setBackgroundColor(Color.GRAY);


            //Master text
            LinearLayout.LayoutParams masterParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout masterLayout = new LinearLayout(container.getContext());
            masterLayout.setLayoutParams(masterParams);
            masterLayout.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams lpMaster = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpMaster.setMargins(30 , 30 , 10 , 20);
            TextView masterText = new TextView(container.getContext());
            masterText.setText("  Master class");
            masterText.setTextColor(Color.BLACK);
            masterLayout.setLayoutParams(lpMaster);


            ImageView starImage = new ImageView(container.getContext());
            starImage.setImageResource(R.drawable.ic_star_black_24dp);


            masterLayout.addView(starImage);
            masterLayout.addView(masterText);

            //notify, divider , master class and main card to vertical layout...
            LinearLayout.LayoutParams lp3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayoutVertical = new LinearLayout(container.getContext());
            linearLayoutVertical.setLayoutParams(lp3);
            linearLayoutVertical.setOrientation(LinearLayout.VERTICAL);
            linearLayoutVertical.addView(linearLayoutNotify);
            linearLayoutVertical.addView(mainLayout);
            linearLayoutVertical.addView(divider);
            linearLayoutVertical.addView(masterLayout);



            //add main card to main layout
            cardView.addView(linearLayoutVertical);


            //card and divider....
            LinearLayout linearLayoutCard = new LinearLayout(container.getContext());
            linearLayoutCard.setOrientation(LinearLayout.VERTICAL);
            linearLayoutCard.addView(cardView);



            upcomingLayout.addView(linearLayoutCard);

        }



    }


    private void setUpFeeds(ViewGroup container) {


        for(int i=0 ; i< feedList.size() ; i++)
        {

            LinearLayout linearLayout = new LinearLayout(getActivity().getApplicationContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);


            AvatarView avatarView = new AvatarView(container.getContext());
            avatarView.setAnimating(true);
            avatarView.setBorderThickness(10);
            avatarView.setBorderColor(Color.parseColor("#ff0000"));
            avatarView.setBorderColorEnd(Color.parseColor("#ff9999"));
            avatarView.setHighlightBorderColor(Color.GREEN);
            avatarView.setHighlightBorderColorEnd(Color.CYAN);
            avatarView.setNumberOfArches(4);
            avatarView.setShouldBounceOnClick(true);
            avatarView.setTotalArchesDegreeArea(80);
            avatarView.setImageResource(R.drawable.feed_logo);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(200, 200);
            if(i==0)
                lp.setMargins(50, 0,15, 15);
            else if(i==(feedList.size()-1))
                lp.setMargins(15, 0, 50, 15);
            else
                lp.setMargins(15, 0, 15, 15);
            avatarView.setLayoutParams(lp);



            TextView textView = new TextView(container.getContext());
            textView.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(200, 200);
            if(i==0)
                lp1.setMargins(50, 0,15, 15);
            else if(i==(feedList.size()-1))
                lp1.setMargins(15, 0, 50, 15);
            else
                lp1.setMargins(15, 0, 15, 15);
            textView.setLayoutParams(lp);
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setText(feedList.get(i).getTitle());

            linearLayout.addView(avatarView);
            linearLayout.addView(textView);

            feedsLayout.addView(linearLayout);

            setUpClickListener(i , avatarView ,container);

        }


    }

    private void setUpFeedList(ViewGroup container) {


        feedList.add(new FeedModel("" , "Text 1"));
        feedList.add(new FeedModel("" , "Text 2"));
        feedList.add(new FeedModel("" , "Text 1"));
        feedList.add(new FeedModel("" , "Text 2"));
        feedList.add(new FeedModel("" , "Text 1"));
        feedList.add(new FeedModel("" , "Text 2"));
        feedList.add(new FeedModel("" , "Text 1"));
        feedList.add(new FeedModel("" , "Text 2"));
        feedList.add(new FeedModel("" , "Text 1"));
        feedList.add(new FeedModel("" , "Text 2"));
        feedList.add(new FeedModel("" , "Text 1"));
        feedList.add(new FeedModel("" , "Text 2"));

        setUpFeeds(container);


    }

    private void setUpcomingList(ViewGroup container) {

        upcomingList.add(new UpcomingModel("" , "NOUN" , "Noun - Can be a name of person, place, animal or thing" , "Aman Srivastava" , null));
        upcomingList.add(new UpcomingModel("" , "TENSE" , "A great view of nouns" , "Aman Srivastava" , null));

        setUpcomingLayout(container);


    }


    private void setUpClickListener(final int i, ImageView imageView, final ViewGroup container)
    {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(container.getContext() , i + "" , Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initFields( View view) {

        feedsLayout = view.findViewById(R.id.feesLayout);
        upcomingLayout = view.findViewById(R.id.upcoming_layout);
        topCoursesLayout = view.findViewById(R.id.top_lectures_layout);
        courseLayout = view.findViewById(R.id.courses_scroll_layout);


    }

    @Override
    public void onSuccess(List snapshot , ViewGroup container) {
        topCoursesList = snapshot;
        setUpTopCoursesLayout(container);
    }

    @Override
    public void onSuccess(String e) {

    }

    @Override
    public void onFailure(String e) {

    }

}
