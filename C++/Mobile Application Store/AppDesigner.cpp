#include <string>
#include <cstring>
#include "AppDesigner.h"

using namespace std;

//Class "AppDesigner" Constructors and Deconstructor Implementation
AppDesigner::AppDesigner(){
    setAppDesignerCode("");
    setAppDesignerName(nullptr);
    setAppDesignerEmail("");
}

AppDesigner::AppDesigner(string inputCode, char* inputName, string inputEmail){
    setAppDesignerCode(inputCode);
    setAppDesignerName(inputName);
    setAppDesignerEmail(inputEmail);
}

AppDesigner::~AppDesigner(){
    if(name!=nullptr){
        delete[] name;
    }
}


//Class "AppDesigner" Setters Implementation
void AppDesigner::setAppDesignerCode(string inputCode){
    code = inputCode;
}

void AppDesigner::setAppDesignerName(char *inputName){
    if(name!=nullptr){
        delete[] name;
    }
    if(inputName!=nullptr){
        name=new char[strlen(inputName)];
        strcpy(name,inputName);
    }else{
        name = nullptr;
    }

}

void AppDesigner::setAppDesignerEmail(string inputEmail){
    email = inputEmail;
}


//Class "AppDesigner" Getters Implementation
string AppDesigner::getAppDesignerCode(){
    return code;
}

char* AppDesigner::getAppDesignerName(){
    return name;
}

string AppDesigner::getAppDesignerEmail(){
    return email;
}


