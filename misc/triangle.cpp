//SY 2017
//github.com/magicake
#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <math.h>
#include <cstdlib>
#include <ctime>
using namespace std;
int pic[700][700];
int coor[3][2];

void drawLine(int x1, int x2, int y1, int y2);
void outImage();
int main(void){
        for(int x = 1; x < 700; x++){
		for(int y = 0; y < 700; y++){
			pic[x][y] = 1;
			}
		}
		srand((unsigned)time(0));
	for(int m = 0; m < 3; m++){
		coor[m][0] = rand() % 700;
		coor[m][1] = rand() % 700;
		cout << coor[m][0];
		cout << " ";
		cout << coor[m][1];
		cout << " ";
	}
  drawLine(coor[0][0], coor[1][0], coor[0][1], coor[1][1]);
  drawLine(coor[1][0], coor[2][0], coor[1][1], coor[2][1]);
  drawLine(coor[2][0], coor[0][0], coor[2][1], coor[0][1]);
  //drawLine(0, 699, 0, 699);
  outImage();
}
void outImage(){
    std::ofstream file;
	file.open("pic.ppm");
	file << "P3 700 700 1\n";
	for(int a = 0; a < 700; a++){
		for(int b = 0; b < 700; b++){
			if(file){
				file << pic[a][b];
				file << " ";
                                file << pic[a][b];
				file << " ";
                                file << pic[a][b];
				file << " ";
				if(b == 699){
					file << "\n";
				}
			}
		}
	}
	file.close();
}
void drawLine(int x, int x2, int y, int y2){
  int w = x2 - x ;
    int h = y2 - y ;
    int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
    if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
    if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
    if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
    int longest = std::abs(w) ;
    int shortest = std::abs(h) ;
    if (!(longest>shortest)) {
        longest = std::abs(h) ;
        shortest = std::abs(w) ;
        if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
        dx2 = 0 ;
    }
    int numerator = longest >> 1 ;
    for (int i=0;i<=longest;i++) {
        pic[x][y] = 0;
        numerator += shortest ;
        if (!(numerator<longest)) {
            numerator -= longest ;
            x += dx1 ;
            y += dy1 ;
        } else {
            x += dx2 ;
            y += dy2 ;
        }
    }
}
