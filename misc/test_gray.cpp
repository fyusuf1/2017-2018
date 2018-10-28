#include <cv.h>
#include <highgui.h>
#include <opencv2/opencv.hpp>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include <stdlib.h>
#include <vector>

using namespace cv;

Mat makeGray(Mat ori);
Mat resize(Mat ori, double x, double y);
Mat blur(Mat ori, double factor);
Mat edges_sobel(Mat ori);
Mat edges_canny_test(Mat ori);
Mat edges_canny_method(Mat ori, int lowThreshold);
void createPPM(Mat ori, std::string filename);

//double accum = [][][];

int main( int argc, char** argv )
{
 char* imageName = argv[1];

 Mat image;
 Mat n_image;

 image = imread( imageName, 1 );
 image = resize(image, 0.1, 0.1);

 n_image = makeGray(image);
 n_image = blur(n_image, 2.5);
 //namedWindow( imageName, CV_WINDOW_AUTOSIZE );
 //namedWindow( "Gray image", CV_WINDOW_AUTOSIZE );
 Mat sobel_image = edges_sobel(n_image);
 Mat canny_image = edges_canny(n_image, 30);
 imshow( "Original", image );
 imshow( "Initial", n_image );
 imshow("Sobel", sobel_image);
 imshow("Canny", canny_image);
 createPPM(canny_image, "easy.ppm");
 waitKey(0);

 return 0;
}
Mat resize(Mat ori, double x, double y){
  Mat n_size;
  resize(ori, n_size, Size(), x, y, CV_INTER_AREA);
  return n_size;
}
Mat makeGray(Mat ori){
  Mat gray_image;
  cvtColor( ori, gray_image, CV_BGR2GRAY );
  imwrite( "../../images/Gray_Image.jpg", gray_image );
  return gray_image;
}
Mat blur(Mat ori, double factor){
  Mat blurred_image;
  GaussianBlur(ori, blurred_image, Size(), factor, 0, BORDER_DEFAULT);
  return blurred_image;
}
Mat edges_sobel(Mat ori){
  Mat gradx, grady;
  Mat absgradx, absgrady;
  Mat grad;

  Sobel( ori, gradx, CV_16S, 1, 0, 3, 1, 0, BORDER_DEFAULT );
  convertScaleAbs( gradx, absgradx );

  Sobel( ori, grady, CV_16S, 1, 0, 3, 1, 0, BORDER_DEFAULT );
  convertScaleAbs( grady, absgrady );

  addWeighted(absgradx, 0.5, absgrady, 0.5, 0, grad);

  return grad;

}
Mat edges_canny_test(Mat ori, int lowThreshold){
  Mat edge_image;
  Canny(ori, edge_image, lowThreshold, lowThreshold*3, 3);
  return edge_image;
}
Mat edges_canny_method(Mat ori){

}
void circle_detect(Mat ori){
  //
}
void createPPM(Mat ori, std::string filename){
  std::vector<int> whatisthis;
  whatisthis.push_back(CV_IMWRITE_PXM_BINARY);
  whatisthis.push_back(9);
  imwrite(filename, ori, whatisthis);
}
