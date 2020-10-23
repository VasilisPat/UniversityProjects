#include "OfficeApp.h"
#include <string>

using namespace std;

//Class "OfficeApp" Constructors and Deconstructor Implementation
OfficeApp::OfficeApp(){
    setAppCode(nullptr);
    setAppName("");
    setAppMinimumVersion("");
    setAppDesigner(nullptr);
    setAppFileTypes("");
    setAppReview(nullptr);
    setAppPrice(0);
}

OfficeApp::OfficeApp(char* inputCode, string inputName, string inputMinVers, AppDesigner* inputDesg, string inputFileTypes, UserReview* inputRev, float inputPrice){
    setAppCode(inputCode);
    setAppName(inputName);
    setAppMinimumVersion(inputMinVers);
    setAppDesigner(inputDesg);
    setAppFileTypes(inputFileTypes);
    setAppReview(inputRev);
    setAppPrice(inputPrice);
}

OfficeApp::~OfficeApp(){
    if(fileTypes.empty()!=true){
        fileTypes.clear();
    }
}


//Class "GameApp" Methods Implementation
ostream& OfficeApp::printData(ostream& out){
    out << "OFFICE APP" << ";" << "CODE" << ";" << "NAME" << ";" << "MINIMUM VERSION" << ";" << "PRICE" << endl;
    out << ";" << this->getAppCode() << ";" << this->getAppName() << ";" << this->getAppMinimumVersion() << ";" << this->getAppPrice() << endl;

    out << "SUPPORTED FILE TYPES" << ";" << "EXTENSIONS" << endl;
    vector<string> tmpvecFT=this->getAppFileTypes();
    for(unsigned int i=0; i<tmpvecFT.size(); i++){
        out << ";" << tmpvecFT[i];
    }
    out << endl;

    out << "APP DESIGNERS" << ";" << "CODE" << ";" << "NAME" << ";" << "EMAIL" << endl;
    vector<AppDesigner*> tmpvecAD=this->getAppDesigners();
    for(unsigned int i=0; i<tmpvecAD.size(); i++){
        out << ";" << tmpvecAD[i]->getAppDesignerCode() << ";" << tmpvecAD[i]->getAppDesignerName() << ";" << tmpvecAD[i]->getAppDesignerEmail() << endl;
    }

    vector<UserReview*> tmpvecUR=this->getAppReviews();
    out << "USER REVIEWS" << ";" << "STARS" << ";" << "NAME" << ";" << "COMMENTS" << endl;
    for(unsigned int i=0; i<tmpvecUR.size(); i++){
        out << ";" << tmpvecUR[i]->getStars() << ";" << tmpvecUR[i]->getReviewerName() << ";" << tmpvecUR[i]->getComments() << endl;
    }
    out << endl;
    return out;
}

void OfficeApp::clear(){
    setAppCode(nullptr);
    setAppName("");
    setAppMinimumVersion("");
    setAppDesigner(nullptr);
    setAppFileTypes("");
    setAppReview(nullptr);
    setAppPrice(0);
}


//Class "OfficeApp" Setters Implementation
void OfficeApp::setAppFileTypes(string inputFileTypes){
    if(inputFileTypes!=""){
        fileTypes.push_back(inputFileTypes);
    }
}


//Class "OfficeApp" Getters Implementation
vector<string> OfficeApp::getAppFileTypes(){
    return fileTypes;
}
