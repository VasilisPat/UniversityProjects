#pragma once
#include <iostream>

using namespace std;

class Course{

    private:
        char* newCode=nullptr;
        char* newName=nullptr;
        char* oldCode=nullptr;
        char* oldName=nullptr;
    public:
        //Constructors and Deconstructor Declaration
        Course();
        Course(char*, char*, char*, char*);
        Course(const Course&);
        ~Course();
        //Class Methods Declaration
        friend ostream& operator<<(ostream&, Course&);
        //Setters Declaration
        void setNewCode(char*);
        void setNewName(char*);
        void setOldCode(char*);
        void setOldName(char*);
        //Getters Declaration
        char* getNewCode();
        char* getNewName();
        char* getOldCode();
        char* getOldName();
};

