#include "Course.h"
#include <iostream>
#include <cstring>

using namespace std;

//Class "Course" Constructors Implementation
Course::Course(){
    setNewCode(nullptr);
    setNewName(nullptr);
    setOldCode(nullptr);
    setOldName(nullptr);
}

Course::Course(char* inputNC, char* inputNN, char* inputOC, char* inputON){
    setNewCode(inputNC);
    setNewName(inputNN);
    setOldCode(inputOC);
    setOldName(inputON);
}

Course::Course(const Course& crs){
    setNewCode(crs.newCode);
    setNewName(crs.newName);
    setOldCode(crs.oldCode);
    setOldName(crs.oldName);
}

Course::~Course(){
    if(newCode!=nullptr){
        delete[] newCode;
    }
    if(newName!=nullptr){
        delete[] newName;
    }
    if(oldCode!=nullptr){
        delete[] oldCode;
    }
    if(oldName!=nullptr){
        delete[] oldName;
    }
}


//Class "Course" Methods Implementation
ostream& operator<<(ostream& out, Course& outCourse){
    out << outCourse.newCode << ";" << outCourse.newName << ";" << outCourse.oldCode << ";" << outCourse.oldName << ";";
    return out;
}


//Class "Course" Setters Implementation
void Course::setNewCode(char* inputNC){
    if(newCode!=nullptr){
        delete[] newCode;
    }
    if(inputNC!=nullptr){
        newCode = new char(strlen(inputNC));
        strcpy(newCode,inputNC);
    }else{
        newCode=nullptr;
    }

}

void Course::setNewName(char* inputNN){
    if(newName!=nullptr){
        delete[] newName;
    }
    if(inputNN!=nullptr){
        newName = new char(strlen(inputNN));
        strcpy(newName,inputNN);
    }else{
        newName=nullptr;
    }
}

void Course::setOldCode(char* inputOC){
    if(oldCode!=nullptr){
        delete[] oldCode;
    }
    if(inputOC!=nullptr){
        oldCode = new char(strlen(inputOC));
        strcpy(oldCode,inputOC);
    }else{
        oldCode=nullptr;
    }
}

void Course::setOldName(char* inputON){
    if(oldName!=nullptr){
        delete[] oldName;
    }
    if(inputON!=nullptr){
        oldName = new char(strlen(inputON));
        strcpy(oldName,inputON);
    }else{
        oldName=nullptr;
    }
}


//Class "Course" Setters Implementation
char* Course::getNewCode(){
    return newCode;
}

char* Course::getNewName(){
    return newName;
}

char* Course::getOldCode(){
    return oldCode;
}

char* Course::getOldName(){
    return oldName;
}
