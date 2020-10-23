#include <iostream>
#include <iterator>
#include <cstring>
#include <fstream>
#include <tuple>
#include "AppSystem.h"
#include "OfficeApp.h"
#include "GameApp.h"

using namespace std;

//Class "AppSystem" Constructor and Deconstructor Implementation
AppSystem::AppSystem(){
    setNewApp(nullptr);
}

AppSystem::~AppSystem(){
    if(appList.empty()!=true){
        appList.clear();
    }
}


//Class "AppSystem" Methods Implementation
void AppSystem::AddNewAppDesigner(char* inputAppCode, AppDesigner* inputAppDes){
    for(unsigned int i=0; i<appList.size(); i++){
        if(strcmp(appList[i]->getAppCode(),inputAppCode)){
            appList[i]->setAppDesigner(inputAppDes);
        }
    }
}

void AppSystem::AddNewUserReview(char* inputAppCode, UserReview* inputUserRev){
    for(unsigned int i=0; i<appList.size(); i++){
        if(strcmp(appList[i]->getAppCode(),inputAppCode)){
            appList[i]->setAppReview(inputUserRev);
        }
    }
}

void AppSystem::DeleteDesignersApp(AppDesigner* inputAppDes){
    vector<GenericApp*>::iterator i;
    for(i=appList.begin(); i<appList.end(); i++){
        vector<AppDesigner*>::iterator j;
        for(j=(*i)->getAppDesigners().begin(); j<(*i)->getAppDesigners().end(); j++){
            if((*j)->getAppDesignerCode()==inputAppDes->getAppDesignerCode()){
                i = appList.erase(i);
                break;
            }
        }
    }
}

tuple<vector<OfficeApp*>, vector<GameApp*>> AppSystem::AverageAppList(){
    int sum;
    float rev, avg;
    vector<OfficeApp*> tmpOff;
    vector<GameApp*> tmpGame;
    for(auto i=appList.begin(); i<appList.end(); i++){
        if((*i)->getAppPrice()==0){
            sum=0; rev=0; avg=0;
            for(auto j=(*i)->getAppReviews().begin(); j<(*i)->getAppReviews().end(); j++){
                    sum++;
                    rev+=(*j)->getStars();
            }
            avg=rev/sum;
            if(avg>4){
                if(dynamic_cast<OfficeApp*>(*i)){
                    tmpOff.push_back(dynamic_cast<OfficeApp*>(*i));
                }
                else{
                    tmpGame.push_back(dynamic_cast<GameApp*>(*i));
                }
            }
        }
    }
    return make_tuple(tmpOff, tmpGame);
}

void AppSystem::ExportData(){
    ofstream expFile;
    expFile.open("expfile.csv");
    try{
        for(auto i=appList.begin(); i<appList.end(); i++){
            (*i)->printData(expFile);
        }
        expFile.close();
    }catch(ofstream::failure &writeErr){
        cout << "Can't create export file." << endl;
    }
}


//Class "AppSystem" Setter Implementation
void AppSystem::setNewApp(GenericApp* inputApp){
    if(inputApp!=nullptr){
        appList.push_back(inputApp);
    }
}


//Class "AppSystem" Getter Implementation
vector<GenericApp*> AppSystem::getAppsList(){
    return appList;
}

