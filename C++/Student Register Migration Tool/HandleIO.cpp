#include "HandleIO.h"
#include <iostream>
#include <fstream>
#include <vector>
#include "Course.h"
#include "Grade.h"
#include "Student.h"

using namespace std;

void loadStudents(vector<Student>* studList){
    ifstream inputS;
    char a[15], s[15], n[15];
    inputS.open("Students.csv");
    try{
        while(!inputS.eof()){
            inputS.getline(a,15,';');
            inputS.getline(s,15,';');
            inputS.getline(n,15,';');
            studList->push_back(Student(a,s,n));
            inputS.ignore();
        }
        inputS.close();
    }catch(ifstream::failure e){
        cout << "File not found";
    }
}

void loadCourses(vector<Course>* crsList){
    ifstream inputC;
    char nc[10], nn[50], oc[10], on[50];
    inputC.open("CoursesMatch.csv");
    try{
        while(inputC.good()){
            inputC.getline(nc,10,';');
            inputC.getline(nn,50,';');
            inputC.getline(oc,10,';');
            inputC.getline(on,50,';');
            crsList->push_back(Course(nc,nn,oc,on));
            inputC.ignore();
        }
        inputC.close();
    }catch(ifstream::failure e){
        cout << "File not found";
    }
}

void loadGrades(vector<Grade>* grdList){
    ifstream inputG;
    char a[15], cC[10], g[4];
    inputG.open("Grades.csv");
    try{
        while(inputG.good()){
            inputG.getline(a,15,';');
            inputG.getline(cC,10,';');
            inputG.getline(g,4,';');
            grdList->push_back(Grade(a,cC,atof(g)));
            inputG.ignore();
        }
        inputG.close();
    }catch(ifstream::failure e){
        cout << "File not found";
    }
}

void exportError(Course* crs){
    ofstream expErr;
    try{
        expErr.open("Errors.csv");
        expErr << *crs << endl;
        expErr.close();
    }catch(ofstream::failure e){
        cout << "Can't export error" << endl;
    }
}

void exportMissedGrade(Student* stud, Course* crs, Grade* grd){
    ofstream expMiss;
    try{
        expMiss.open("MissedGrades.csv");
        expMiss << *stud << *crs << grd->getGrade() << ";";
        expMiss.close();
    }catch(ofstream::failure e){
        cout << "Can't export missed grade" << endl;
    }
}
