#include <time.h>
#include "Paint_dot.h"
#include "Paint_network.h"
#include <iostream>
#include <cmath>
#include <string>
#include <vector>
#include <list>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string.h>
#include <netdb.h>
#include <stdio.h>
#include <pthread.h>

#include <stdio.h>

// #define ADDR "127.0.0.1"
// #define PORT_NUM 50002

#ifdef __APPLE__
#include <OpenGL/gl.h>
#include <OpenGL/glu.h>
#include <GLUT/glut.h>
#else
#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/freeglut.h>
#endif

//using namespace std;

int cfd; //cliend socket descriptor

float red = 1.0, green = 0.0, blue = 0.0;
int tmpx, tmpy; // store the first point when shape is line, rectangle or circle
int brushSize = 4;
int eraserSize = 1;
bool isSecond = false;
bool isRandom = false;
bool isEraser = false;
float window_w = 500;
float window_h = 500;

int shape = 1; // 1:point, 2:line, 3:rectangle, 4:circle, 5:brush

std::vector<Dot> dots;      // store all the points until clear

void display(void)
{
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
    glPointSize(2);
    glBegin(GL_POINTS);
    for (unsigned int i = 0; i < dots.size(); i++)
    {
        glColor3f(dots[i].getR(), dots[i].getG(), dots[i].getB());
        glVertex2i(dots[i].getX(), dots[i].getY());
    }
    glEnd();
    glutSwapBuffers();
}

void clear()
{
    dots.clear();
    glClear(GL_COLOR_BUFFER_BIT);
    glutSwapBuffers();
    time_t rawtime;
    struct tm *timeinfo;
    time(&rawtime);
    timeinfo = localtime(&rawtime);
    std::cout << asctime(timeinfo)
              << "[Info] The window and the history are cleared successfully.\n";
}

void quit()
{
    std::cout << "Thank you for using this Paint tool! Goodbye!" << std::endl;

    PNET::close_socket(cfd);

    glutDestroyWindow(0);
}
void drawDot(int mousex, int mousey)
{
    Dot newDot(mousex, window_h - mousey, isEraser ? 1.0 : red, isEraser ? 1.0 : green, isEraser ? 1.0 : blue);
    dots.push_back(newDot);
}


void erase(int x, int y)
{
    for (int i = -eraserSize; i <= eraserSize; i++)
    {
        for (int j = -eraserSize; j <= eraserSize; j++)
        {
            drawDot(x + i, y + j);


        }
    }
}

void keyboard(unsigned char key, int xIn, int yIn)
{
    isSecond = false;
    switch (key)
    {
    case 'q':
    case 27: // 27 is the esc key
        quit();
        break;
    case 'c':
        clear();
        break;
    case '+':
        if (shape == 5 && !isEraser)
        {
            if (brushSize < 16)
                brushSize += 4;
            else
            {
                time_t rawtime;
                struct tm *timeinfo;
                time(&rawtime);
                timeinfo = localtime(&rawtime);
                std::cout << asctime(timeinfo)
                          << "[Warning] Airbrush's size cannot be larger. It is already the largest.\n";
            }
        }
        else if (isEraser)
        {
            if (eraserSize < 10)
                eraserSize += 4;
            else
            {
                time_t rawtime;
                struct tm *timeinfo;
                time(&rawtime);
                timeinfo = localtime(&rawtime);
                std::cout << asctime(timeinfo)
                          << "[Warning] Eraser's size cannot be larger. It is already the largest.\n";
            }
        }
        break;
    case '-':
        if (shape == 5 && !isEraser)
        {
            if (brushSize > 4)
                brushSize -= 4;
            else
            {
                time_t rawtime;
                struct tm *timeinfo;
                time(&rawtime);
                timeinfo = localtime(&rawtime);
                std::cout << asctime(timeinfo)
                          << "[Warning] Airbrush's size cannot be smaller. It is already the smallest.\n";
            }
        }
        else if (isEraser)
        {
            if (eraserSize > 2)
                eraserSize -= 4;
            else
            {
                time_t rawtime;
                struct tm *timeinfo;
                time(&rawtime);
                timeinfo = localtime(&rawtime);
                std::cout << asctime(timeinfo)
                          << "[Warning] Eraser's size cannot be smaller. It is already the smallest.\n";
            }
        }
        break;
    }
}



void motion(int x, int y)
{
    if (isEraser)
        erase(x, y);
    else
    {
        if (shape == 1)

        {

            int array[5];
            array[0] = x;
            array[1] = y;
            array[2] = red;
            array[3] = green;
            array[4] = blue;

            drawDot(x, y);

            PNET::send_packet(cfd, array, sizeof(array));
        }
        
    }
}

void reshape(int w, int h)
{
    window_w = w;
    window_h = h;
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0, w, 0, h);

    glMatrixMode(GL_MODELVIEW);
    glViewport(0, 0, w, h);
}

void processMainMenu(int value)
{
    switch (value)
    {
    case 0:
        quit();
        break;
    case 1:
        clear();
        break;
    }
}

void processColourMenu(int value)
{
    isSecond = false;
    isEraser = false;
    isRandom = false;

    switch (value)
    {
    case 1: // red
        red = 1.0;
        green = 0.0;
        blue = 0.0;
        break;
    case 2: // green
        red = 0.0;
        green = 1.0;
        blue = 0.0;
        break;
    case 3: // blue
        red = 0.0;
        green = 0.0;
        blue = 1.0;
        break;
    case 4: // purple
        red = 0.5;
        green = 0.0;
        blue = 0.5;
        break;
    case 5: // yellow
        red = 1.0;
        green = 1.0;
        blue = 0.0;
        break;
    case 6: // random
        isRandom = true;
        break;
    }
}

void processShapeMenu(int value)
{
    shape = value;
    isEraser = false;
    isSecond = false;

    switch (shape)
    {
    case 1:
        glutSetCursor(GLUT_CURSOR_RIGHT_ARROW);
        break;
    case 2:
    case 3:
    case 4:
        glutSetCursor(GLUT_CURSOR_CROSSHAIR);
        break;
    }
}

void processEraserSizeMenu(int value)
{
    glutSetCursor(GLUT_CURSOR_RIGHT_ARROW);
    eraserSize = value;
    isEraser = true;
}

void createOurMenu()
{
    int colourMenu = glutCreateMenu(processColourMenu);
    glutAddMenuEntry("Red", 1);
    glutAddMenuEntry("Green", 2);
    glutAddMenuEntry("Blue", 3);
    glutAddMenuEntry("Purple", 4);
    glutAddMenuEntry("Yellow", 5);
    glutAddMenuEntry("Random", 6);

    int shapeMenu = glutCreateMenu(processShapeMenu);
    glutAddMenuEntry("Point", 1);

    int eraserSizeMenu = glutCreateMenu(processEraserSizeMenu);
    glutAddMenuEntry("Small", 2);
    glutAddMenuEntry("Medium", 6);
    glutAddMenuEntry("Large", 10);

    int main_id = glutCreateMenu(processMainMenu);
    glutAddSubMenu("Colour", colourMenu);
    glutAddSubMenu("Shapes", shapeMenu);
    glutAddSubMenu("Eraser", eraserSizeMenu);
    glutAddMenuEntry("Clear", 1);
    glutAddMenuEntry("Quit", 0);
    glutAttachMenu(GLUT_RIGHT_BUTTON);
}

void init(void)
{
    /* background color */
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glColor3f(red, green, blue);

    glMatrixMode(GL_PROJECTION);
    gluOrtho2D(0.0, window_w, 0.0, window_h);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

void FPS(int val)
{
    glutPostRedisplay();
    glutTimerFunc(0, FPS, 0);
}

void callbackInit()
{
    glutDisplayFunc(display);
    glutReshapeFunc(reshape);
    glutKeyboardFunc(keyboard);
    glutMotionFunc(motion);
    glutTimerFunc(17, FPS, 0);
}



void *ThreadFunc(void *arg)
{
    Dot *myDot = new Dot();
    int arr[5];
    while (1)
    {
        PNET::recieve_packet(cfd, arr, sizeof(arr));
        myDot->setPosition(arr[0], window_h - arr[1]);
        myDot->setColour(arr[2], arr[3], arr[4]);
        dots.push_back(*myDot);
    }
}

int main(int argc, char **argv)
{
    pthread_t tid;

    PNET::address_structure *addressOfSocket = new PNET::address_structure;

    addressOfSocket->addr.sin_family = AF_INET;
    addressOfSocket->addr.sin_port = htons(PORT_NUM);
    inet_aton(ADDR, &addressOfSocket->addr.sin_addr);

    cfd = PNET::cl_socket_create();
    PNET::send_request(cfd, addressOfSocket);

    char buffer[20];
    char svBuffer[20];
    char spec[7] = "PAINT\n";
    char response = 0;

    while (1)
    {

        printf("Waiting for message...\n");
        if (read(cfd, svBuffer, sizeof(svBuffer)) == -1)
        {
            printf("Error occurred when recieving data from socket!");
            exit(EXIT_FAILURE);
        }

        if (svBuffer[0] == 0)
        {
            break;
        }

        printf("\t Message from server:- %s\n", svBuffer);
        printf("Enter your message:- ");

        fgets(buffer, sizeof(buffer), stdin);
        if (strcmp(buffer, spec) == 0)
        {
            write(cfd, &response, sizeof(char));
            break;
        }

        if (write(cfd, buffer, sizeof(buffer)) == -1)
        {
            printf("Error occurred when sending data to socket!");
            exit(EXIT_FAILURE);
        }
    }

    pthread_create(&tid, NULL, ThreadFunc, addressOfSocket);

    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);
    glutInitWindowSize(window_w, window_h);
    glutInitWindowPosition(100, 100);
    glutCreateWindow("PaintClient");
    callbackInit();
    init();
    createOurMenu();
    glutMainLoop();
    return (0);
}