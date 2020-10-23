#include "UserReview.h"

using namespace std;

//Class "UserReview" Constructors Implementation
UserReview::UserReview(){
    setStars(0);
    setReviewerName("");
    setComments("");
}

UserReview::UserReview(int inputStars, string inputName, string inputComments){
    setStars(inputStars);
    setReviewerName(inputName);
    setComments(inputComments);
}


//Class "UserReview" Setters Implementation
void UserReview::setStars(int inputStars){
    stars = inputStars;
}

void UserReview::setReviewerName(string inputName){
    name = inputName;
}

void UserReview::setComments(string inputComments){
    comments = inputComments;
}


//Class "UserReview" Getters Implementation
int UserReview::getStars(){
    return stars;
}

string UserReview::getReviewerName(){
    return name;
}

string UserReview::getComments(){
    return comments;
}
