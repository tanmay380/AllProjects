/*#include<Windows.h>
#include<GL\glut.h>

#include<math.h>
#define pi 3.142857  // GLUT, include glu.h and gl.h


using namespace std;

float xr= 0;
float yh =0;
 // try



/*void init()
{

    glClearColor(0.4, 0.250, 0.777,0);
    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0, 800, 0.0, 600);
}
void home()
{
    //Roof
    glClear(GL_COLOR_BUFFER_BIT);     // Clear display window
    // Set line segment color as glColor3f(R,G,B)
    //grass
    glColor3f(0.133, 0.694, 0.298);
    glBegin(GL_POLYGON);
    glVertex2i(0, 0);
    glVertex2i(0, 200);
    glVertex2i(800, 200);
    glVertex2i(800, 0);
    glEnd();

    //grass line
    glLineWidth(3);
    glColor3f(0, 0, 0);
    glBegin(GL_LINES);


    glVertex2i(0, 200);
    glVertex2i(800, 200);
    glEnd();


    //Boat
    glColor3f(0.454, 0.788, 0.997);
    glBegin(GL_POLYGON);
    glVertex2i(450 + xr, 200 + yh);
    glVertex2i(420+ xr, 270+ yh);
    glVertex2i(600+ xr, 270+ yh);
    glVertex2i(570+ xr, 200+ yh);
    glEnd();

     //Boat lines
    glColor3f(0, 0, 0);
    glBegin(GL_LINES);
    glVertex2i(450+ xr, 200+ yh);
 glVertex2i(420+ xr, 270+ yh);
    glVertex2i(420+ xr, 270+ yh);
	glVertex2i(600+ xr, 270+ yh);
	glVertex2i(600+ xr, 270+ yh);
    glVertex2i(570+ xr, 200+ yh);
    glVertex2i(570+ xr, 200+ yh);
     glVertex2i(450+ xr, 200+ yh);
    glEnd();

    //Boat triangle

    glColor3f(0.545, 0.794, 0.2);
    glBegin(GL_POLYGON);
    glVertex2i(480+ xr, 270+ yh);
    glVertex2i(510+ xr, 340+ yh);
    glVertex2i(540+ xr, 270+ yh);
    glEnd();

    //Boat triangle lines

    glColor3f(0, 0, 0);
    glBegin(GL_LINES);
    glVertex2i(480+ xr, 270+ yh);
    glVertex2i(510+ xr, 340+ yh);
    glVertex2i(510+ xr, 340+ yh);
    glVertex2i(540+ xr, 270+ yh);
    glVertex2i(540+ xr, 270+ yh);
    glVertex2i(480+ xr, 270+ yh);
    glEnd();

  // Sun
    glColor3f(1, 0.783, 0.152);
    glBegin(GL_POLYGON);
    float x, y, i;

    // iterate y up to 2*pi, i.e., 360 degree
    // with small increment in angle as
    // glVertex2i just draws a point on specified co-ordinate
    for ( i = 0; i < (2 * pi); i += 0.001)
    {
        // let 200 is radius of circle and as,
        // circle is defined as x=r*cos(i) and y=r*sin(i)
        x = 50 * cos(i);
        y = 50 * sin(i);

        glVertex2i(x+600, y+500);
    }
    glEnd();

    // SUN lines
    glPointSize(2);
    glColor3f(0, 0, 0);
    glBegin(GL_POINTS);

    // iterate y up to 2*pi, i.e., 360 degree
    // with small increment in angle as
    // glVertex2i just draws a point on specified co-ordinate
    for ( i = 0; i < (2 * pi); i += 0.001)
    {
        // let 200 is radius of circle and as,
        // circle is defined as x=r*cos(i) and y=r*sin(i)
        x = 50 * cos(i);
        y = 50 * sin(i);

        glVertex2i(x+600, y+500);
    }
    glEnd();

     //SUN shine lines
     glColor3f(0,0,0);
     glBegin(GL_LINES);
    glVertex2i(600, 590);
    glVertex2i(600, 555);
    glVertex2i(600, 445);
    glVertex2i(600, 410);
    glVertex2i(655, 500);
    glVertex2i(700, 500);
    glVertex2i(500, 500);
    glVertex2i(545, 500);

    //sides

     glVertex2i(635, 540);
    glVertex2i(670, 575);
    glVertex2i(520, 435);
    glVertex2i(555, 470);
    glVertex2i(520, 570);
    glVertex2i(555, 535);
    glVertex2i(640, 465);
    glVertex2i(675, 430);
    glEnd();


    //hut
     glColor3f(0.730, 0.558, 0.545);
    glBegin(GL_POLYGON);
    glVertex2i(200, 130);
    glVertex2i(200, 350);
    glVertex2i(350, 350);
    glVertex2i(350, 130);
    glEnd();

    //HUT Gate

     glColor3f(0, 0, 0);
    glBegin(GL_LINES);
    glVertex2i(235, 130);
    glVertex2i(235, 300);
    glVertex2i(233, 300);
    glVertex2i(315, 300);
    glVertex2i(314, 300);
    glVertex2i(314, 130);
    glVertex2i(275, 300);
    glVertex2i(275, 130);
    glEnd();
    //hut lines

    glColor3f(0, 0, 0);
    glBegin(GL_LINES);
    glVertex2i(200, 130);
    glVertex2i(200, 350);
    glVertex2i(200, 350);
    glVertex2i(350, 350);
    glVertex2i(350, 350);
    glVertex2i(350, 130);
    glVertex2i(350, 130);
    glVertex2i(200, 130);
    glEnd();

    //Hut triangle
     glColor3f(0.794, 0.398, 0.125);
    glBegin(GL_POLYGON);
    glVertex2i(150, 350);
    glVertex2i(275, 500);
    glVertex2i(400, 350);
    glEnd();

    //Hut triangle line
     glColor3f(0, 0, 0);
    glBegin(GL_LINES);
    glVertex2i(150, 350);
    glVertex2i(275, 500);
    glVertex2i(275, 500);
    glVertex2i(400, 350);
    glVertex2i(400, 350);
    glVertex2i(150, 350);
    glEnd();

    // chimney
     glColor3f(0.233, 0, 0.462);
    glBegin(GL_POLYGON);
    glVertex2i(220, 390);
    glVertex2i(220, 570);
    glVertex2i(240, 570);
    glVertex2i(240, 390);
    glEnd();

     // chimney Lines
     glColor3f(0, 0, 0);
    glBegin(GL_LINES);
    glVertex2i(220, 390);
    glVertex2i(220, 570);
    glVertex2i(220, 570);
    glVertex2i(240, 570);
    glVertex2i(240, 570);
    glVertex2i(240, 390);
    glVertex2i(240, 390);
    glVertex2i(220, 390);
    glEnd();
    glFlush();
}

void key(int key, int x, int y) {
	switch (key)
	{
	case GLUT_KEY_LEFT:
		xr = xr - 3;
		glutPostRedisplay();
		break;
	case GLUT_KEY_RIGHT:
		xr = xr + 3;
		glutPostRedisplay();
		break;


	}
}

void Mouse(int button, int state, int x, int y)
{
	if (button == GLUT_LEFT_BUTTON && state == GLUT_DOWN)
	{

	switch (button)
	{
			case GLUT_LEFT_BUTTON:
	yh = yh + 20;
	glutPostRedisplay();
		break;

	}

	}
	else if((button == GLUT_LEFT_BUTTON && state == GLUT_UP))
	{
			switch (button)
	{
			case GLUT_LEFT_BUTTON:
	yh = yh - 20;
	glutPostRedisplay();
		break;

	}
		}

}
int main(int argc, char ** argv)
{
    // Initialize GLUT
    glutInit(&argc, argv);
    // Set display mode
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    // Set top - left display window position.
    glutInitWindowPosition(100, 100);
    // Set display window width and height
    glutInitWindowSize(800, 600);
    // Create display window with the given title
    glutCreateWindow("2D House in OpenGL ");
    glutSpecialFunc(key);
    glutMouseFunc(Mouse);
    // Execute initialization procedure
//    init();
    // Send graphics to display window
    glutDisplayFunc(home);
    // Display everything and wait.
    glutMainLoop();
}
*/
