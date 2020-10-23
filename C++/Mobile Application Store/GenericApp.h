#pragma once
#include <iostream>
#include <vector>
#include "AppDesigner.h"
#include "UserReview.h"

using namespace std;

class GenericApp{

    private:
        char* code=nullptr;
        string name;
        string minVersion;
        vector<AppDesigner*> designers;
        vector<UserReview*> reviews;
        float price;
    public:
        //Constructors and Deconstructor Declaration
        GenericApp();
        GenericApp(char*, string, string, AppDesigner*, UserReview*, float);
        ~GenericApp();
        //Setters Declaration
        void setAppCode(char*);
        void setAppName(string);
        void setAppMinimumVersion(string);
        void setAppDesigner(AppDesigner*);
        void setAppReview(UserReview*);
        void setAppPrice(float);
        virtual ostream& printData(ostream& out)=0;
        //Getters Declaration
        char* getAppCode();
        string getAppName();
        string getAppMinimumVersion();
        vector<AppDesigner*> getAppDesigners();
        vector<UserReview*> getAppReviews();
        float getAppPrice();

};


