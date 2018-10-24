#include <stdio.h>
#include <stdlib.h>

typedef struct node {
    int element;
    struct node *next;
} Node;

typedef struct queue {
    Node *head;
    Node *tail;
    int size;
} Queue;

Queue createQueue() {
    return (Queue){NULL, NULL, 0};
}

int getSize(Queue *queue) {
    return queue->size;
}

void push(Queue *queue, int val) {
    Node *newNode = malloc(sizeof(Node));
    newNode->next = NULL;
    newNode->element = val;
    if(queue->tail != NULL) {
        queue->tail->next = newNode;
        queue->tail = newNode;
        queue->size++;
    }
    else {
        queue->tail = newNode;
        queue->head = newNode;
        queue->size++;
    }
}

int pop(Queue *queue) {
    Node *tmp;
    int val = 0;
    if(queue->head != NULL && queue->size > 0) {
        tmp = queue->head->next;
        val = queue->head->element;
        free(queue->head);
        queue->head = tmp;
        queue->size--;
    }
    return val;
}

int main(void) {
    Queue q = createQueue();
    push(&q, 0);
    push(&q, 1);
    push(&q, 2);
    push(&q, 3);
    push(&q, 4);
    while(getSize(&q)) {
        printf("Value: %d\n", pop(&q));
    }
}