#include "GameApp.h"
#include <string>

using namespace std;

//Class "GameApp" Constructors Implementation
GameApp::GameApp(){
    setAppCode(nullptr);
    setAppName("");
    setAppMinimumVersion("");
    setAppDesigner(nullptr);
    setIsOnline(false);
    setAppCategory("");
    setAppReview(nullptr);
    setAppPrice(0);
}

GameApp::GameApp(char* inputCode, string inputName, string inputMinVers, AppDesigner* inputDes, bool inputOnl, string inputCategory, UserReview* inputUserRev, float inputPrice){
    setAppCode(inputCode);
    setAppName(inputName);
    setAppMinimumVersion(inputMinVers);
    setAppDesigner(inputDes);
    setIsOnline(inputOnl);
    setAppCategory(inputCategory);
    setAppReview(inputUserRev);
    setAppPrice(inputPrice);
}


//Class "GameApp" Methods Implementation
ostream& GameApp::printData(ostream& out){
    out << "GAME APP" << ";" << "CODE" << ";" << "NAME" << ";" << "MINIMUM VERSION" << ";" << "ONLINE" << ";" << "CATEGORY" << ";" << "PRICE" << endl;
    out << ";" << this->getAppCode() << ";" << this->getAppName() << ";" << this->getAppMinimumVersion() << ";" << ((this->getIsOnline())?"Yes":"No") << ";" << this->getAppCategory() << ";" << this->getAppPrice() << endl;

    out << "APP DESIGNERS" << ";" << "CODE" << ";" << "NAME" << ";" << "EMAIL" << endl;
    vector<AppDesigner*> tmpvecAD=this->getAppDesigners();
    for(unsigned int i=0; i<tmpvecAD.size(); i++){
        out << ";" << tmpvecAD[i]->getAppDesignerCode() << ";" << tmpvecAD[i]->getAppDesignerName() << ";" << tmpvecAD[i]->getAppDesignerEmail() << endl;
    }

    vector<UserReview*> tmpvecUR=this->getAppReviews();;
    out << "USER REVIEWS" << ";" << "STARS" << ";" << "NAME" << ";" << "COMMENTS" << endl;
    for(unsigned int i=0; i<tmpvecUR.size(); i++){
        out << ";" << tmpvecUR[i]->getStars() << ";" << tmpvecUR[i]->getReviewerName() << ";" << tmpvecUR[i]->getComments() << endl;
    }
    out << endl;
    return out;
}

void GameApp::clear(){
    setAppCode(nullptr);
    setAppName("");
    setAppMinimumVersion("");
    setAppDesigner(nullptr);
    setIsOnline(false);
    setAppCategory("");
    setAppReview(nullptr);
    setAppPrice(0);
}


//Class "GameApp" Setters Implementation
void GameApp::setIsOnline(bool inputOnl){
    isOnline = inputOnl;
}

void GameApp::setAppCategory(string inputCategory){
    category = inputCategory;
}


//Class "GameApp" Getters Implementation
bool GameApp::getIsOnline(){
    return isOnline;
}

string GameApp::getAppCategory(){
    return category;
}
