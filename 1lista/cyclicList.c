#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct node {
    struct node *next;
    struct node *previous;
    int element;
} Node;

typedef struct cyclicList {
    Node *head;
    Node *tail;
    int size;
} CyclicList;

CyclicList createCyclicList() {
    return (CyclicList) {NULL, NULL, 0};
}

int getSize(CyclicList *list) {
    return list->size;
}

void push(CyclicList *list, int val) {
    Node *newNode = malloc(sizeof(Node));
    newNode->element = val;

    if(list->tail != NULL) {
        newNode->next = list->head;
        newNode->previous = list->tail;
        list->tail->next = newNode;
        list->head->previous = newNode;
        list->tail = newNode;
        list->size++;
    }
    else {
        newNode->next = newNode;
        newNode->previous = newNode;
        list->tail = newNode;
        list->head = newNode;
        list->size++;
    }
}

int getValue(CyclicList *list, int idx) {
    if(idx < getSize(list) && idx >= 0) {
        Node *p;

        if(idx < getSize(list) / 2) {
            p = list->head;
            while(idx > 0) {
                p = p->next;
                idx--;
            }
        }
        else {
            p = list->tail;
            int lastIdx = getSize(list) - 1;
            while(idx < lastIdx) {
                p = p->previous;
                idx++;
            }
        }

        return p->element;
    }

    return 0;
}

void removeElement(CyclicList *list, int idx) {
    if(idx < getSize(list) && idx >= 0) {
        if(idx < getSize(list) / 2) {
            if(idx == 0) { // removing head
                Node *tmp = list->head;
                list->head = tmp->next;
                list->head->previous = list->tail;
                list->tail->next = list->head;
                free(tmp);
                list->size--;
                return;
            }

            Node *p = list->head;
            while(idx > 1) {
                p = p->next;
                idx--;
            }
            Node *tmp = p->next;
            p->next = tmp->next;
            p->next->previous = p;
            free(tmp);
            list->size--;
        }
        else {
            int lastIdx = getSize(list) - 1;
            if(idx == lastIdx) { // removing tail
                Node *tmp = list->tail;
                list->tail = tmp->previous;
                list->head->previous = list->tail;
                list->tail->next = list->head;
                free(tmp);
                list->size--;
                return;
            }

            Node *p = list->tail;
            while(idx < lastIdx - 1) {
                p = p->previous;
                idx++;
            }
            Node *tmp = p->previous;
            p->previous = tmp->previous;
            p->previous->next = p;
            free(tmp);
            list->size--;
        }
    }
}

void printList(CyclicList *list, int x) {
    if(list->head == NULL && list->tail == NULL) {
        printf("empty list\n");
        return;
    }
    Node *p = list->head;
    printf("List (--->):\n");
    for(int i=0; i<x; i++) {
        do {
            printf("%d\n", p->element);
            p = p->next;
        } while(p != list->head);
    }

    p = list->tail;
    printf("List (<---):\n");
    for(int i=0; i<x; i++) {
        do {
            printf("%d\n", p->element);
            p = p->previous;
        } while(p != list->tail);
    }
}

void clearList(CyclicList *list) {
    Node *p, *tmp;
    p = list->head;
 
    if(p != NULL)
    {
        list->head = NULL;
        list->tail = NULL;
        list->size = 0;

        p->previous->next = NULL;
        while(p != NULL)
        {
            tmp = p->next;
            p->next = NULL;
            free(p);
            p = tmp;
        }
    }
}

CyclicList merge(CyclicList *list1, CyclicList *list2) {
    CyclicList mergedList = createCyclicList();

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
    CyclicList list = createCyclicList();
    srand(time(NULL));
    for(int i = 0; i < 1000; i++) {
        push(&list, rand() % 1000);
    }

    clock_t start;
    start = clock();
    for(int i = 0; i < 1000; i++) {
        getValue(&list, 600);
    }
    printf("%ld\n", clock() - start);

    start = clock();
    for(int i = 0; i < 1000; i++) {
        getValue(&list, rand() % 1000);
    }
    printf("%ld\n", clock() - start);

    return 0;


    // CyclicList list = createCyclicList();
    // push(&list, 1);
    // push(&list, 2);
    // push(&list, 3);
    // push(&list, 4);
    // push(&list, 5);
    // push(&list, 6);
    // push(&list, 7);
    // printList(&list, 2);

    // removeElement(&list, 0);
    // printList(&list, 2);

    // removeElement(&list, 5);
    // printList(&list, 2);

    // removeElement(&list, 2);
    // printList(&list, 2);

    // CyclicList list2 = createCyclicList();
    // push(&list2, 11);
    // push(&list2, 12);
    // push(&list2, 13);
    // push(&list2, 14);
    // list = merge(&list, &list2);
    // printList(&list, 2);

    // clearList(&list);
    // printList(&list, 1);
}