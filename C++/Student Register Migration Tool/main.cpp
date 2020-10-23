#include <iostream>
#include <vector>
#include "Course.h"
#include "Grade.h"
#include "Student.h"
#include "HandleIO.h"
#include "CheckLists.h"

using namespace std;

int main()
{
    system("chcp 1253");

    vector<Student> studentsList;
    vector<Course> coursesList;
    vector<Grade> gradesList;

    //Load data from files into memory
    loadStudents(&studentsList);
    loadCourses(&coursesList);
    loadGrades(&gradesList);

    performCheck(&studentsList,&coursesList,&gradesList);

    return 0;
}
