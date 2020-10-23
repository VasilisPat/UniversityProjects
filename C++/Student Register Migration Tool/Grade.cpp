#include "Grade.h"
#include <iostream>
#include <cstring>

using namespace std;

//Class "Grade" Constructors Implementation
Grade::Grade(){
    setAM(nullptr);
    setCourseCode(nullptr);
    setGrade(0);
}

Grade::Grade(char* inputAM, char* inputCode, float inputGrade){
    setAM(inputAM);
    setCourseCode(inputCode);
    setGrade(inputGrade);
}

Grade::Grade(const Grade& grd){
    setAM(grd.am);
    setCourseCode(grd.courseCode);
    setGrade(grd.grade);
}


Grade::~Grade(){
    if(am!=nullptr){
        delete[] am;
    }
    if(courseCode!=nullptr){
        delete[] courseCode;
    }
}


//Class "Grade" Methods Implementation
ostream& operator<<(ostream& out, Grade& outGrade){
    out << outGrade.am << ";" << outGrade.courseCode << ";" << outGrade.grade << ";";
    return out;
}


//Class "Grade" Setters Implementation
void Grade::setAM(char* inputAM){
    if(am!=nullptr){
        delete[] am;
    }
    if(inputAM!=nullptr){
        am=new char(strlen(inputAM));
        strcpy(am,inputAM);
    }else{
        am=nullptr;
    }
}

void Grade::setCourseCode(char* inputCode){
    if(courseCode!=nullptr){
        delete[] courseCode;
    }
    if(inputCode!=nullptr){
        courseCode=new char(strlen(inputCode));
        strcpy(courseCode,inputCode);
    }else{
        courseCode=nullptr;
    }
}

void Grade::setGrade(float inputGrade){
    grade=inputGrade;
}


//Class "Grade" Setters Implementation
char* Grade::getAM(){
    return am;
}

char* Grade::getCourseCode(){
    return courseCode;
}

float Grade::getGrade(){
    return grade;
}
