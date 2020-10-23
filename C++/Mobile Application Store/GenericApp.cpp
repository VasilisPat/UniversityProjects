#include "GenericApp.h"
#include <string>
#include <cstring>
#include <vector>

using namespace std;

//Class "GenericApp" Constructors and Deconstructor Implementation
GenericApp::GenericApp(){
    setAppCode(nullptr);
    setAppName("");
    setAppMinimumVersion("");
    setAppDesigner(nullptr);
    setAppReview(nullptr);
    setAppPrice(0);
}

GenericApp::GenericApp(char* inputCode, string inputName, string inputMinVers, AppDesigner* inputDesg, UserReview* inputRev, float inputPrice){
    setAppCode(inputCode);
    setAppName(inputName);
    setAppMinimumVersion(inputMinVers);
    setAppDesigner(inputDesg);
    setAppReview(inputRev);
    setAppPrice(inputPrice);
}

GenericApp::~GenericApp(){
    if(code!=nullptr){
        delete[] code;
    }
    if(designers.empty()!=true){
        designers.clear();
    }
    if(reviews.empty()!=true){
        designers.clear();
    }
}


//Class "GenericApp" Setters Implementation
void GenericApp::setAppCode(char* inputCode){
    if(code!=nullptr){
        delete[] inputCode;
    }
    if(inputCode!=nullptr){
        code=new char[strlen(inputCode)];
        strcpy(code,inputCode);
    }else{
        code = inputCode;
    }
}

void GenericApp::setAppName(string inputName){
    name = inputName;
}

void GenericApp::setAppMinimumVersion(string inputMinVers){
    minVersion = inputMinVers;
}

void GenericApp::setAppDesigner(AppDesigner* inputDesg){
    if(inputDesg!=nullptr){
        designers.push_back(inputDesg);
    }
}

void GenericApp::setAppReview(UserReview* inputRev){
    if(inputRev!=nullptr){
        reviews.push_back(inputRev);
    }
}

void GenericApp::setAppPrice(float inputPrice){
    price = inputPrice;
}


//Class "GenericApp" Getters Implementation
char* GenericApp::getAppCode(){
    return code;
}

string GenericApp::getAppName(){
    return name;
}

string GenericApp::getAppMinimumVersion(){
    return minVersion;
}

vector<AppDesigner*> GenericApp::getAppDesigners(){
    return designers;
}

vector<UserReview*> GenericApp::getAppReviews(){
    return reviews;
}

float GenericApp::getAppPrice(){
    return price;
}

