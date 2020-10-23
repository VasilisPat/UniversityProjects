#pragma once
#include <iostream>
#include <string>

using namespace std;

class AppDesigner{

    private:
        string code;
        char* name=nullptr;
        string email;
    public:
        //Constructors and Deconstructor Decloration
        AppDesigner();
        AppDesigner(string, char*, string);
        ~AppDesigner();
        //Setters Declaration
        void setAppDesignerCode(string);
        void setAppDesignerName(char*);
        void setAppDesignerEmail(string);
        //Getters Declaration
        string getAppDesignerCode();
        char* getAppDesignerName();
        string getAppDesignerEmail();
};
