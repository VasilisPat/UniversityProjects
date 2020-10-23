#include <iostream>
#include "AppSystem.h"
#include "OfficeApp.h"
#include "GameApp.h"

using namespace std;

int main(int argc, char const *argv[]){
    AppDesigner ad("123","AD","123@ad.com");
    UserReview ur(1,"Me","Awesome");
    GameApp gam("11","GameA","1.0",&ad,false,"test",&ur,0);
    OfficeApp off("22","OffA","1.0",&ad,".*",&ur,0);
    AppSystem as;
    as.setNewApp(&gam);
    as.setNewApp(&off);
    as.ExportData();
    return 0;
}
