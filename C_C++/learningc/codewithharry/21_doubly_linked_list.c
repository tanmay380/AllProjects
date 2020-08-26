#include <stdlib.h>
#include "stdio.h"

struct Node{
    int data;
    struct Node* prev;
    struct Node* next;
};

void traverselist(struct Node* head){
    struct Node* tail=head;
    printf("forward\n");

    while (head!=NULL) {
        printf("%d \n", head->data);
        head = head->next;
    }
    printf("backword\n");

    while (tail->next!=NULL){
        tail=tail->next;
    }
    while (tail!=NULL){
        printf("%d \n",tail->data);

        tail=tail->prev;
    }
}

int main(){
    struct Node* head;
    struct Node* second;
    struct Node* third;
    struct Node* fourth;

    head=(struct Node*)malloc(sizeof(struct Node));
    second=(struct Node*)malloc(sizeof(struct Node));
    third=(struct Node*)malloc(sizeof(struct Node));
    fourth=(struct Node*)malloc(sizeof(struct Node));

    head->prev=NULL;
    head->data=2;
    head->next=second;

    second->prev=head;
    second->data=4;
    second->next=third;

    third->prev=second;
    third->data=6;
    third->next=fourth;

    fourth->prev=third;
    fourth->data=8;
    fourth->next=NULL;

    traverselist(head);


}