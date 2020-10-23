#pragma once
#include <iostream>
#include <vector>
#include "GenericApp.h"

using namespace std;

class OfficeApp: public GenericApp{

    private:
        vector<string> fileTypes;
    public:
        //Constructors and Deconstructor Declaration
        OfficeApp();
        OfficeApp(char*, string, string, AppDesigner*, string, UserReview*, float);
        ~OfficeApp();
        //Class Methods
        ostream& printData(ostream& out);
        void clear();
        //Setters Declaration
        void setAppFileTypes(string);
        //Getters Declaration
        vector<string> getAppFileTypes();
};
