#include "CheckLists.h"
#include <iostream>
#include <vector>
#include "Course.h"
#include "Grade.h"
#include "Student.h"
#include "HandleIO.h"

using namespace std;

void performCheck(vector<Student>* studList, vector<Course>* crsList, vector<Grade>* grdList){
    Student *st; Grade *gd,*gdCheck; Course *cr;
    bool flag;
    //Get grades to check
    for(unsigned int i=0; i<grdList->size(); i++){
        gd=&grdList->at(i);
        for(unsigned int j=0; j<crsList->size(); j++){
            cr=&crsList->at(j);
            //Check if this is an old system grade
            if(gd->getCourseCode()==cr->getOldCode()){
                flag=false;
                for(unsigned int k=0; k<grdList->size(); k++){
                    gdCheck=&grdList->at(k);
                    //Check if the same grade exists, but with the new course code
                    if(gd->getAM()==gdCheck->getAM() && cr->getNewCode()==gdCheck->getCourseCode() && gd->getGrade()==gdCheck->getGrade()){
                        //The grade has been transfered to new system
                        flag=true;
                        break;
                    }
                }
                if(flag==false){
                    //Find student's info to print missed grade, grade match between systems not found
                    for(unsigned int l=0; l<studList->size(); l++){
                        st=&studList->at(l);
                        if(gd->getAM()==st->getAM()){
                            exportMissedGrade(st,cr,gd);
                            break;
                        }
                    }
                }
            }else if(gd->getCourseCode()==cr->getNewCode()){
                //Check if this is new system's grade
            }else{
                //No match for course between new and old system
                exportError(cr);
            }
        }
    }
}


