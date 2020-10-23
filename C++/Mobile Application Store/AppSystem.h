#pragma once
#include <iostream>
#include <vector>
#include <tuple>
#include "GenericApp.h"
#include "OfficeApp.h"
#include "GameApp.h"

using namespace std;

class AppSystem{

    private:
        vector<GenericApp*> appList;
    public:
        //Constructor and Deconstructor Decloration
        AppSystem();
        ~AppSystem();
        //Class Methods
        void AddNewAppDesigner(char*, AppDesigner*);
        void AddNewUserReview(char*, UserReview*);
        void DeleteDesignersApp(AppDesigner*);
        tuple<vector<OfficeApp*>, vector<GameApp*>> AverageAppList();
        void ExportData();
        //Setter Declaration
        void setNewApp(GenericApp*);
        //Getter Declaration
        vector<GenericApp*> getAppsList();

};
