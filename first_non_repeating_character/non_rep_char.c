#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

typedef struct node_s {
	unsigned char ch;
	struct node_s *prev;
	struct node_s *next;
} node_t;


struct d_linkedlist {
	node_t *head;
	node_t *tail;
};


void cleanup(struct d_linkedlist *d_ll)
{
	if (NULL == d_ll || NULL == d_ll->head) {
		return;
	}
	node_t *ptr;
	do {
		ptr = d_ll->head->next;		
		free(d_ll->head);
		d_ll->head = ptr;
	} while (NULL != ptr);
	d_ll->tail = NULL;
}

void *insert(unsigned char ch, struct d_linkedlist *d_ll)
{
	node_t *ptr = (node_t *)calloc(1, sizeof(node_t));
	if (NULL == ptr) {
		fprintf(stderr, "Error allocating memory\n");
		exit(1);
	}
	ptr->ch = ch;
	ptr->next = NULL;

	if (d_ll->head == NULL) {
		ptr->prev = NULL;
		d_ll->head = d_ll->tail = ptr;
		return (void *)ptr;
	}

	ptr->prev = d_ll->tail;
	d_ll->tail->next = ptr;
	d_ll->tail = ptr;
	return (void *)ptr;
}

void delete(node_t *ptr, struct d_linkedlist *d_ll)
{
	if (ptr == d_ll->head && ptr == d_ll->tail) {
		free(ptr);
		d_ll->head = d_ll->tail = NULL;
		return;
	}
	if (ptr == d_ll->head) {
		d_ll->head = d_ll->head->next;
		d_ll->head->prev = NULL;
		free(ptr);
		return;
	}
	if (ptr == d_ll->tail) {
		d_ll->tail = d_ll->tail->prev;
		d_ll->tail->next = NULL;
		free(ptr);
		return;
	}
	ptr->prev->next = ptr->next;
	ptr->next->prev = ptr->prev;
	free(ptr);
}


unsigned char non_rep_ch(unsigned char *input)
{
	int len = strlen((const char *)input);
	if (0 == len) {
		return '-';
	}

	struct d_linkedlist d_ll = {NULL, NULL};
	void *occ[256] = {0};
	
	for (int i = 0; i < len; i++) {
		if (NULL == occ[input[i]]) {
			occ[input[i]] = insert(input[i], &d_ll);
			continue;
		}
		if ((void *)-1 != occ[input[i]]) {
			delete(occ[input[i]], &d_ll);
			occ[input[i]] = (void *)-1;
		}
	}
	if (NULL == d_ll.head) {
		return '-';
	}
	unsigned char ch = d_ll.head->ch;
	cleanup(&d_ll);
	return ch;
}


int main(int argc, char **argv)
{

	unsigned char input[256];
	scanf("%s", input);
	printf("First non repeating character: %c\n", non_rep_ch(input));
	return 0;
}
	 
