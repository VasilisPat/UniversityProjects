#pragma once
#include <iostream>
#include "GenericApp.h"

using namespace std;

class GameApp: public GenericApp{

    private:
        bool isOnline;
        string category;
    public:
        //Constructors Declaration
        GameApp();
        GameApp(char*, string, string, AppDesigner*, bool, string, UserReview*, float);
        //Class Methods
        ostream& printData(ostream& out);
        void clear();
        //Setters Declaration
        void setIsOnline(bool);
        void setAppCategory(string);
        //Getters Declaration
        bool getIsOnline();
        string getAppCategory();
};
