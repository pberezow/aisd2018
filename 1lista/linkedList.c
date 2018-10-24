#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct node {
    struct node *next;
    int element;
} Node;

typedef struct linkedList {
    Node *head;
    Node *tail;
    int size;
} LinkedList;

LinkedList createLinkedList() {
    return (LinkedList) {NULL, NULL, 0};
}

int getSize(LinkedList *list) {
    return list->size;
}

void push(LinkedList *list, int val) {
    Node *newNode = malloc(sizeof(Node));
    newNode->next = NULL;
    newNode->element = val;
    if(list->tail != NULL) {
        list->tail->next = newNode;
        list->tail = newNode;
        list->size++;
    }
    else {
        list->tail = newNode;
        list->head = newNode;
        list->size++;
    }
}

int getValue(LinkedList *list, int idx) {
    if(idx < getSize(list) && idx >= 0) {
        Node *p = list->head;
        while(idx > 0) {
            p = p->next;
            idx--;
        }
        return p->element;
    }
    return 0;
}

void removeElement(LinkedList *list, int idx) {
    if(idx < getSize(list) && idx >= 0) {
        if(idx == 0) {
            Node *tmp = list->head;
            list->head = tmp->next;
            free(tmp);
            list->size--;
            if(getSize(list) == 0) {
                list->tail = NULL;
            }
        }
        else if(idx == getSize(list) - 1) {
            Node *p = list->head;
            while(idx > 1) {
                p = p->next;
                idx--;
            }
            Node *tmp = p->next;
            p->next = NULL;
            free(tmp);
            list->tail = p;
            list->size--;
        }
        else {
            Node *p = list->head;
            while(idx > 1) {
                p = p->next;
                idx--;
            }
            Node *tmp = p->next;
            p->next = tmp->next;
            free(tmp);
            list->size--;
        }
    }
}

void printList(LinkedList *list) {
    Node *p = list->head;
    printf("List:\n");
    if(p == NULL) {
        printf("List is empty!\n");
    }
    while(p != NULL) {
        printf("%d\n", p->element);
        p = p->next;
    }
}

void clearList(LinkedList *list) {
    Node *p, *tmp;
    p = list->head;
 
    if(p != NULL)
    {
        list->head = NULL;
        list->tail = NULL;
        list->size = 0;
        while(p != NULL)
        {
            tmp = p->next;
            p->next = NULL;
            free(p);
            p = tmp;
        }
    }
}

LinkedList merge(LinkedList *list1, LinkedList *list2) {
    LinkedList mergedList = createLinkedList();

    for(int i = 0; i < getSize(list1); i++) {
        push(&mergedList, getValue(list1, i));
    }

    for(int i = 0; i < getSize(list2); i++) {
        push(&mergedList, getValue(list2, i));
    }

    clearList(list1);
    clearList(list2);

    return mergedList;
}

int main(void) {
    LinkedList list = createLinkedList();
    srand(time(NULL));
    for(int i = 0; i<1000; i++) {
        push(&list, rand() % 1000);
    }

    clock_t start;
    start = clock();
    for(int i = 0; i<1000; i++) {
        getValue(&list, 600);
    }
    printf("%ld\n", clock() - start);

    start = clock();
    for(int i = 0; i<1000; i++) {
        getValue(&list, rand() % 1000);
    }
    printf("%ld\n", clock() - start);

    return 0;


    // LinkedList list = createLinkedList();

    // printList(&list);

    // push(&list, 1);
    // push(&list, 2);
    // push(&list, 3);
    // push(&list, 4);
    // push(&list, 5);
    // push(&list, 6);
    // push(&list, 7);

    // printList(&list);

    // removeElement(&list, 0);
    // printList(&list);

    // removeElement(&list, 3);
    // printList(&list);

    // removeElement(&list, 4);
    // printList(&list);
    
    // LinkedList list2 = createLinkedList();

    // push(&list2, 11);
    // push(&list2, 12);
    // push(&list2, 13);
    // push(&list2, 14);

    // printList(&list2);

    // list = merge(&list, &list2);

    // printList(&list);
}