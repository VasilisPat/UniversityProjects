#include <iostream>
#include <iomanip>
#include <string>
#include <cstring>

//Enabled the "-std=c++0x" compiler option for nullptr support

using namespace std;


class Course{

    private:
        string crscode;
        string crsdesc;
        unsigned int crssem;

    public:
        //Constructor Decloration
        Course();
        Course(string, string, unsigned int);
        //Class Methods Declaration
        friend ostream& operator<<(ostream&, const Course&);
        //Setters Declaration
        void setCode(string);
        void setDesc(string);
        void setSem(unsigned int);
        //Getters Declaration
        string getCode();
        string getDesc();
        unsigned int getSem();
};


//Class "Course" Constructors Implementation
Course::Course(){
    setCode("");
    setDesc("");
    setSem(0);
}

Course::Course(string code, string desc, unsigned int sem){
    setCode(code);
    setDesc(desc);
    setSem(sem);
}


//Class "Course" Methods Implementation
ostream& operator<<(ostream& out, const Course& cprt){
    out << "Course ID: " << cprt.crscode << "\tCourse Name: " << cprt.crsdesc << "\tCourse Semester: " << cprt.crssem << endl << endl;
    return out;
}


//Class "Course" Setters Implementation
void Course::setCode(string code){
    crscode=code;
}

void Course::setDesc(string desc){
    crsdesc=desc;
}

void Course::setSem(unsigned int sem){
    crssem=sem;
}


//Class "Course" Getters Implementation
string Course::getCode(){
    return crscode;
}

string Course::getDesc(){
    return crsdesc;
}

unsigned int Course::getSem(){
    return crssem;
}


class Student{

	private:
		char *am;
		string name;
		unsigned int cursem;
		unsigned int lesspass;
		float *gradesarr;
		unsigned int declcrs;
		Course **declcrsarr;

	public:
		//Constructors & Deconstructor Declaration
		Student(char*, string, unsigned int, Course**);
		Student(char*, string, unsigned int, unsigned int, Course**);
		Student(char*, string, unsigned int, unsigned int, float*, unsigned int, Course**);
		Student(const Student&);
		~Student();
		// Methods Declaration
		int SetNewGrade(float);
		void ShowGrades();
		//Function Overloading Declaration
		friend ostream& operator<<(ostream&, const Student&);
		void operator+=(Course&);
		Student& operator=(Student&);
		bool operator==(const Student&);
		bool operator!=(const Student&);
		bool operator<(const Student&);
		bool operator<=(const Student&);
		bool operator>(const Student&);
		bool operator>=(const Student&);
		//Setters Declaration
		void setAM(char*);
		void setName(string);
		void setCurSemester(unsigned int);
		void setLessPass(unsigned int);
		void setGradesArr(float*);
		void setDeclCrs(unsigned int);
		void setDeclCrsArr(Course**);
		//Getters Declaration
		char* getAM();
		string getName();
		unsigned int getCurSemester();
		unsigned int getLessPass();
		float* getGradesArr();
		unsigned int getDeclCrs();
		Course** getDeclCrsArr();
};


//Class "Student" Constructors & Deconstructor Implementation
Student::Student(char id[], string nm, unsigned int crs, Course** crsarr){
	setAM(id);
    setName(nm);
	cursem=1;
	lesspass=0;
	gradesarr=nullptr;
	setDeclCrs(crs);
	setDeclCrsArr(crsarr);

}

Student::Student(char id[], string nm, unsigned int usrsem, unsigned int crs, Course** crsarr){
	setAM(id);
    setName(nm);
    setCurSemester(usrsem);
	lesspass=0;
	gradesarr=nullptr;
	setDeclCrs(crs);
	setDeclCrsArr(crsarr);

}

Student::Student(char id[], string nm, unsigned int usrsem, unsigned int pless, float lgrd[], unsigned int crs, Course** crsarr){
    setAM(id);
    setName(nm);
    setCurSemester(usrsem);
    setLessPass(pless);
	setGradesArr(lgrd);
	setDeclCrs(crs);
	setDeclCrsArr(crsarr);
}

Student::Student(const Student& cln){
    am=new char[strlen(cln.am)];
    setAM(cln.am);
    setName(cln.name);
    setCurSemester(cln.cursem);
    setLessPass(cln.lesspass);
	setGradesArr(cln.gradesarr);
	setDeclCrs(cln.declcrs);
	setDeclCrsArr(cln.declcrsarr);
}

Student::~Student(){
    if(am!=nullptr){
        delete[] am;
    }
	if(gradesarr!=nullptr){
        delete[] gradesarr;
	}
	if(declcrsarr!=nullptr){
       delete[] declcrsarr;
	}
}


//Class "Student" Methods Implementation
int Student::SetNewGrade(float ngrd){
	if(((unsigned int)ngrd)<5){
		cout << "The grade given is less than 5. The student can't have passed this lesson!";
		return 1;
	}else{
	    float* newGrdArr = new float[lesspass];
	    for(int i=0; i<=lesspass; i++){
            if(i!=lesspass && gradesarr!=nullptr){
                    newGrdArr[i]=gradesarr[i];
            }else{
                newGrdArr[i]=ngrd;
                break;
            }
	    }
	    delete[] gradesarr;
	    gradesarr=newGrdArr;
		lesspass++;
		return 0;
	}
}

void Student::ShowGrades(){
	float sum=0,avg=0;
	if(((unsigned int)lesspass)==0){
			cout << "This student has not passed any lesson.";
	}else{
		for(int i=0; i<lesspass; i++){
			sum=sum+gradesarr[i];
			cout << "Lesson " << i+1 << " grade is: " << gradesarr[i] << endl;
		}
		avg=sum/lesspass;
		cout << "The average grade of the student is: " << setprecision(3) << avg << endl;
	}
	cout << endl;
}


//Class "Student" Functions Overloading Implementation
ostream& operator<<(ostream& out, const Student& sprt){
    out << "AM: " << sprt.am << "\tName: " << sprt.name << "\tCurrrent Semester: " << sprt.cursem << endl;
    return out;
}

void Student::operator+=(Course &crs){
    Course **newArr = new Course*[declcrs];
    for(int i=0; i<=declcrs; i++){
        if(i!=declcrs && declcrsarr!=nullptr){
            newArr[i]=declcrsarr[i];
        }else{
            newArr[i]=&crs;
            break;
        }
    }
    delete[] declcrsarr;
    declcrsarr = newArr;
    declcrs++;
}

Student& Student::operator=(Student& st){
    this->setAM(st.am);
    this->setName(st.name);
    this->setCurSemester(st.cursem);
    this->setLessPass(st.lesspass);
	this->setGradesArr(st.gradesarr);
	this->setDeclCrs(st.declcrs);
	this->setDeclCrsArr(st.declcrsarr);
}

bool Student::operator==(const Student &st){
    if (this->cursem==st.cursem){
        return true;
    }else{
        return false;
    }
}

bool Student::operator!=(const Student &st){
    if (this->cursem!=st.cursem){
        return true;
    }else{
        return false;
    }
}

bool Student::operator<(const Student &st){
    if (this->cursem<st.cursem){
        return true;
    }else{
        return false;
    }
}

bool Student::operator<=(const Student &st){
    if (this->cursem<=st.cursem){
        return true;
    }else{
        return false;
    }
}

bool Student::operator>(const Student &st){
    if (this->cursem>st.cursem){
        return true;
    }else{
        return false;
    }
}

bool Student::operator>=(const Student &st){
    if (this->cursem>=st.cursem){
        return true;
    }else{
        return false;
    }
}


//Class "Student" Setters Implementation
void Student::setAM(char* newAM){
    if(am!=nullptr){
        delete[] am;
    }
    am=new char[strlen(newAM)];
	strcpy(am,newAM);
}

void Student::setName(string newName){
	name=newName;
}

void Student::setCurSemester(unsigned int newCurSem){
	cursem=newCurSem;
}

void Student::setLessPass(unsigned int newLessPass){
	lesspass=newLessPass;
}

void Student::setGradesArr(float* newGradesArr){
    if(gradesarr!=nullptr){
        delete[] gradesarr;
    }
    gradesarr=new float[lesspass];
    gradesarr=newGradesArr;
}

void Student::setDeclCrs(unsigned int newDeclCrs){
    declcrs=newDeclCrs;
}

void Student::setDeclCrsArr(Course** newDeclCrsArr){
    if(declcrsarr!=nullptr){
        delete[] declcrsarr;
	}
	declcrsarr = new Course*[declcrs];
	for(int i=0; i<declcrs; i++){
        declcrsarr[i]=newDeclCrsArr[i];
	}
}


//Class "Student" Getters Implementation
char* Student::getAM(){
	return am;
}

string Student::getName(){
	return name;
}

unsigned int Student::getCurSemester(){
	return cursem;
}

unsigned int Student::getLessPass(){
	return lesspass;
}

float* Student::getGradesArr(){
	return gradesarr;
}

unsigned int Student::getDeclCrs(){
    return declcrs;
}

Course** Student::getDeclCrsArr(){
    return declcrsarr;
}


int main(int argc, char **argv){

	char str[20];
	string n="Ioannis Ioannou";
	unsigned int csem=2, grds=5, crs=2;
	float lp[5]={5.7,6,8,9,10}, ngrd=5;
    strcpy(str,"12345678");


    //Course Initialization
	Course crs1("123","Course1",(unsigned)2);
	Course crs2("456","Course2",(unsigned)2);
	Course crs3("789","Course3",(unsigned)4);
	Course *carr[2]={&crs1, &crs2};

    //Student Full Info Initialization
	Student studA(str,n,csem,grds,lp,crs,carr);
	cout << studA;
	studA.ShowGrades();
	studA+=crs3;

	cout << "Addresses from main:\t\t" << &crs1 << "\t" << &crs2 << "\t" << &crs3 << endl << "Addresses from student's array: ";
	Course** ptr=studA.getDeclCrsArr();
	for(int i=0; i<studA.getDeclCrs(); i++){
        cout << ptr[i] << "\t";
	}
	return 0;
}
