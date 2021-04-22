#include<windows.h>
#include<GL/glut.h>
#include<iostream>
#include<math.h>
using namespace std;

int rmax,rmin,  xmin, ymin;

void display(void)

{

glClear(GL_COLOR_BUFFER_BIT);

glColor3f(0.0,0.0,0.0);

glPointSize(2.0);

}

void init(void)
{
 glClearColor(1,1,1,1);
 glMatrixMode(GL_PROJECTION);
 glClear(GL_COLOR_BUFFER_BIT);
 gluOrtho2D(-200,200,-200,200);
 glFlush();
}

int i=1;
float x1,yy;
float xval, yval;
int xarr[2];
int yarr[2];

void setPixel(float x, float y){
  glPointSize(0.5);
  glBegin(GL_POINTS);
    glVertex2i(x,y);
  glEnd();
  // glFlush();
}

void circle(int x1, int y1, int x2, int y2){
  glClear(GL_COLOR_BUFFER_BIT);
  cout<<"x1="<<x1<<",x2="<<x2<<",y1="<<y1<<",y2="<<y2<<"\n";
  int r=sqrt(pow((x1-x2),2)+pow((y1-y2),2));
  cout<<"r actual="<<r<<"\n";
  int x=0, y=r, p=1-r;
  while(x<=y){
    setPixel(x+x1,y+y1);
    setPixel(y+x1,x+y1);
    setPixel(x+x1,-y+y1);
    setPixel(y+x1,-x+y1);

    setPixel(-x+x1,y+y1);
    setPixel(-y+x1,x+y1);
    setPixel(-y+x1,-x+y1);
    setPixel(-x+x1,-y+y1);
    if(p<0){
      p=p+(2*x)+3;
    }
    else{
      p=p+(2*(x-y))+5;
      y--;
    }
    x++;
  }
  // glFlush();
}

void cir(int x1, int y1, int r){
  glClear(GL_COLOR_BUFFER_BIT);
  cout<<"r="<<r<<"\n";
  if(r>=0){
    int x=0, y=r, p=1-r;
    while(x<=y){
      setPixel(x+x1,y+y1);
      setPixel(y+x1,x+y1);
      setPixel(x+x1,-y+y1);
      setPixel(y+x1,-x+y1);

      setPixel(-x+x1,y+y1);
      setPixel(-y+x1,x+y1);
      setPixel(-y+x1,-x+y1);
      setPixel(-x+x1,-y+y1);
      if(p<0){
        p=p+(2*x)+3;
      }
      else{
        p=p+(2*(x-y))+5;
        y--;
      }
      x++;
    }
  }
  glFlush();
}


int count=1;
void keyFunc(int key, GLint xMouse, GLint yMouse){
  cout<<"x1="<<xarr[i-2]<<",x2="<<xarr[i-1]<<",y1="<<yarr[i-2]<<",y2="<<yarr[i-1]<<"\n";
  int r=sqrt(pow((xarr[i-2]-xarr[i-1]),2)+pow((yarr[i-2]-yarr[i-1]),2));
  cout<<"r in ="<<r<<"\n";

  if(key==GLUT_KEY_UP){
    if((count+r+2)<rmax){
      cir(xarr[i-2],yarr[i-2],(count+r+2));
      count+=2;
    }
  }
  if(key==GLUT_KEY_DOWN){
    if((count+r-2)>=2){
      cir(xarr[i-2],yarr[i-2],(count+r-2));
      count-=2;
    }
  }
  cout<<"count in ="<<count<<"\n";
}

void mousePtPlot(GLint button, GLint action, GLint xMouse, GLint yMouse){

  if(button == GLUT_LEFT_BUTTON && action==GLUT_DOWN)
  {
      x1=xMouse;
      yy=400-yMouse;

      xval = ((x1*400)/400)-200;
      yval = ((yy*400)/400)-200;
      cout<<"x="<<xval<<" & y="<<yval<<"\n";

      glPointSize(5);
      glBegin(GL_POINTS);
      glColor3f(0,0,0);
      setPixel(xval, yval);
      // glVertex2i(xval,yval);
      glEnd();

      xarr[i]=xval;
      yarr[i]=yval;
      i++;

      if(i%2!=0){
        count=1;
        int xdif1=abs(xarr[i-2]-(-200));
        int xdif2=abs(xarr[i-2]-(200));
        if(xdif1<xdif2){
          xmin=xdif1;
        }
        else{
          xmin=xdif2;
        }

        int ydif1=abs(yarr[i-2]-(-200));
        int ydif2=abs(yarr[i-2]-(200));
        if(ydif1<ydif2){
          ymin=ydif1;
        }
        else{
          ymin=ydif2;
        }

        if(xmin<ymin){
          rmax=xmin;
        }
        else{
          rmax=ymin;
        }
        circle(xarr[i-2],yarr[i-2],xarr[i-1],yarr[i-1]);
      }
  }
  glFlush();
}

int main(int argc, char** argv){
  glutInit(&argc, argv);
  glutInitDisplayMode(GLUT_SINGLE|GLUT_RGB);
  glutInitWindowPosition(0,0);
  glutInitWindowSize(400,400);
  glutCreateWindow("Circle");

  init();
   glutDisplayFunc(display);
  glutMouseFunc(mousePtPlot);
  glutSpecialFunc(keyFunc);
  glutMainLoop();
  return 0;
}
