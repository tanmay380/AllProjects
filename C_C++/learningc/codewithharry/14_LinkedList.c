#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;
    struct Node *next;
};

void linkedlisttraversal(struct Node *ptr) {
    while (ptr != NULL) {
        printf("Element is: %d\n", ptr->data);
        ptr = ptr->next;
    }
}

struct Node *insertatbegin(struct Node *head, int data) {
    struct Node *ptr = (struct Node *) malloc(sizeof(struct Node));
    ptr->data = data;
    ptr->next = head;
    return ptr;

}

struct Node *insertatindex(struct Node *head, int data, int index) {
    struct Node *ptr = (struct Node *) malloc(sizeof(struct Node));
    struct Node *p = head;
    int i = 0;
    while (i != index - 1) {
        p = p->next;
        i++;
    }
    ptr->data = data;
    ptr->next = p->next;
    p->next = ptr;
    return head;
}

struct Node *insertatEnd(struct Node *head, int data) {
    struct Node *ptr = (struct Node *) malloc(sizeof(struct Node));
    ptr->data = data;
    struct Node *p = head;
    while (p->next != NULL) {
        p = p->next;
    }
    p->next = ptr;
    ptr->next = NULL;
    return head;
}

struct Node *insertafterNode(struct Node *head, struct Node* prevnode, int data) {
    struct Node *ptr = (struct Node *) malloc(sizeof(struct Node));
    ptr->data=data;

    ptr->next=prevnode->next;
    prevnode->next=ptr;
    return head;

}
struct Node* deleteatfirst(struct Node* head){
    struct Node* ptr=head;
    head=head->next;

    free(ptr);
    return head;
}

struct Node* deleteatIndex(struct Node* head,int index){
    struct Node *p = head;
    int i=0;
    while (i!=index-1){
        p=p->next;
        i++;
    }
    struct Node* q=p->next;

    p->next=q->next;
    free(q);
    return head;
}
struct Node* deleteatend(struct Node* head){
    struct Node* p=head;
    struct Node* q=head->next;
    while (q->next!=NULL){
        p=p->next;
        q=q->next;
    }
    p->next=NULL;
    free(q);
    return head;

}

struct Node* deletevalue(struct Node* head, int value){
    struct Node* p=head;
    struct Node* q=head->next;

    while (q->data!=value && q->next!=NULL)
    {
        p=p->next;
        q=q->next;
    }
    if (q->data==value)
    {
        p->next=q->next;
    }
    free(q);

    return p;

}

int main() {
    struct Node *head;
    struct Node *second;
    struct Node *third;
    struct Node *fourth;
    head = (struct Node *) malloc(sizeof(struct Node));
    second = (struct Node *) malloc(sizeof(struct Node));
    third = (struct Node *) malloc(sizeof(struct Node));
    fourth = (struct Node *) malloc(sizeof(struct Node));

    head->data = 7;
    head->next = second;

    second->data = 11;
    second->next = third;

    third->data = 13;
    third->next = fourth;


    fourth->data = 100;
    fourth->next = NULL;
    printf("before");
    linkedlisttraversal(head);
//    head=insertatbegin(head,22);
//    head=insertatindex(head,22,1);
//    head = insertatEnd(head, 22);
//    head = insertafterNode(head,second,22);
//    head = deleteatfirst(head);
//    head = deleteatIndex(head,1);
//     head = deleteatend(head);
    head= deletevalue(head, 11);
    printf("after\n");
    linkedlisttraversal(head);


}









