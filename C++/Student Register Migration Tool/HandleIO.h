#pragma once
#include <iostream>
#include <vector>
#include "Course.h"
#include "Grade.h"
#include "Student.h"

void loadStudents(vector<Student>* studList);
void loadCourses(vector<Course>* crsList);
void loadGrades(vector<Grade>* grdList);
void exportError(Course* crs);
void exportMissedGrade(Student* stud, Course* crs, Grade* grd);
