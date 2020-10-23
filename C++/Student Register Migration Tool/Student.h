#pragma once
#include <iostream>

using namespace std;

class Student{

    private:
        char* am=nullptr;
        char* surname=nullptr;
        char* name=nullptr;
    public:
        //Constructors and Deconstructor Declaration
        Student();
        Student(char*, char*, char*);
        Student(const Student&);
        ~Student();
        //Class Methods Declaration
        friend ostream& operator<<(ostream&, Student&);
        //Setters Declaration
        void setAM(char*);
        void setSurname(char*);
        void setName(char*);
        //Getters Declaration
        char* getAM();
        char* getSurname();
        char* getName();
};



