cmake_minimum_required(VERSION 3.17)
project(codewithharry C)

set(CMAKE_C_STANDARD 99)

add_executable(LinkedList 14_LinkedList.c )
add_executable(Opengl fractals.c )

#target_link_libraries(Opengl libopengl32.a libglu32.a libfreeglut.a)
target_link_libraries(Opengl -lOpenGL32 -lglut32win)
#target_link_libraries(Opengl ${OPENGL_LIBRARIES} ${GLUT_LIBRARY})
add_executable(Doublylinkedlist 21_doubly_linked_list.c)