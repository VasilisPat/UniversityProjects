#pragma once
#include <iostream>

using namespace std;

class UserReview{

    private:
        int stars;
        string name;
        string comments;
    public:
        //Constructors Decloration
        UserReview();
        UserReview(int, string, string);
        //Setters Declaration
        void setStars(int);
        void setReviewerName(string);
        void setComments(string);
        //Getters Declaration
        int getStars();
        string getReviewerName();
        string getComments();

};

