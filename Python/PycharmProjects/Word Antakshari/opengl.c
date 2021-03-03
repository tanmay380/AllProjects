#include <windows.h>
#include <GL/glut.h>
void hexagon (void)
{
    glBegin(GL_POLYGON);
    glColor3f(0.5f, 0.0f, 1.0f);
    glVertex3f(0.0, 0.4, 0);
    glVertex3f(-0.3, 0.2, 0);
    glVertex3f(-0.3,-0.2, 0);
    glVertex3f(0.3, 0.2, 0);
    glEnd();//end the shape we are currently working on
    glBegin(GL_POLYGON);
    glColor3f(0.5f, 0.0f, 1.0f);
    glVertex3f(-0.3, -0.2, 0);
    glVertex3f(0.0, -0.4, 0);
    glVertex3f(0.3,-0.2, 0);
    glVertex3f(0.3, 0.2, 0);
    glEnd();//end the shape we are currently working on

    glBegin(GL_LINES);
    glColor3f(1, 0, 0);
    glVertex2f(0.0,0.5);
    glVertex2f(0.0,-0.5);
    glEnd();

    glBegin(GL_LINES);
    glColor3f(1, 0, 0);
    glVertex2f(-0.4,0.3);
    glVertex2f(0.4,-0.3);
    glEnd();

    glBegin(GL_LINES);
    glColor3f(1, 0, 0);
    glVertex2f(-0.4,-0.3);
    glVertex2f(0.4,0.3);
    glEnd();
}


void display (void)
{

    glClear (GL_COLOR_BUFFER_BIT);
    glLoadIdentity();
    hexagon();
    glFlush();
}



int main (int argc, char **argv)
{
    glutInit (&argc, argv);
    glutInitDisplayMode (GLUT_SINGLE);
    glutInitWindowSize (500, 500);
    glutInitWindowPosition (100, 100);
    glutCreateWindow ("OpenGL Window");
    glutDisplayFunc (display);
    glutMainLoop ();
    return 0;
}