#pragma once
#include <iostream>

using namespace std;

class Grade{

    private:
        char* am=nullptr;
        char* courseCode=nullptr;
        float grade;
    public:
        //Constructors and Deconstructor Declaration
        Grade();
        Grade(char*, char*, float);
        Grade(const Grade&);
        ~Grade();
        //Class Methods Declaration
        friend ostream& operator<<(ostream&, Grade&);
        //Setters Declaration
        void setAM(char*);
        void setCourseCode(char*);
        void setGrade(float);
        //Getters Declaration
        char* getAM();
        char* getCourseCode();
        float getGrade();
};



