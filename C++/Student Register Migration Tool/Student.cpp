#include "Student.h"
#include <iostream>
#include <cstring>

using namespace std;

//Class "Student" Constructors Implementation
Student::Student(){
    setAM(nullptr);
    setSurname(nullptr);
    setName(nullptr);
}

Student::Student(char* inputAM, char* inputSurname, char* inputName){
    setAM(inputAM);
    setSurname(inputSurname);
    setName(inputName);
}

Student::Student(const Student& stud){
    setAM(stud.am);
    setSurname(stud.surname);
    setName(stud.name);
}

Student::~Student(){
    if(am!=nullptr){
        delete[] am;
    }
    if(surname!=nullptr){
        delete[] surname;
    }
    if(name!=nullptr){
        delete[] name;
    }
}


//Class "Student" Methods Implementation
ostream& operator<<(ostream& out, Student& outStud){
    out << outStud.am << ";" << outStud.surname << ";" << outStud.name << ";";
    return out;
}


//Class "Grade" Setters Implementation
void Student::setAM(char* inputAM){
    if(am!=nullptr){
        delete[] am;
    }
    if(inputAM!=nullptr){
        am = new char(strlen(inputAM));
        strcpy(am,inputAM);
    }else{
        am=nullptr;
    }
}

void Student::setSurname(char* inputSurname){
    if(surname!=nullptr){
        delete[] surname;
    }
    if(inputSurname!=nullptr){
        surname = new char(strlen(inputSurname));
        strcpy(surname,inputSurname);
    }else{
        surname=nullptr;
    }
}

void Student::setName(char* inputName){
    if(name!=nullptr){
        delete[] name;
    }
    if(inputName!=nullptr){
        name = new char(strlen(inputName));
        strcpy(name,inputName);
    }else{
        name=nullptr;
    }
}


//Class "Student" Getters Implementation
char* Student::getAM(){
    return am;
}

char* Student::getSurname(){
    return surname;
}

char* Student::getName(){
    return name;
}
