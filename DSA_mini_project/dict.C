#include<stdio.h>
#include<malloc.h>
#include<stdlib.h>
#include<string.h>

//print line function;
void printline(){

	int i=0;
	printf("\n");
	for(i=0;i<80;i++){
		printf("#");
	}


}


//defining a word using structure;
struct word{

	char wd[50];

	//pointer to next word;
	struct word *nextword;


};

struct word* start=NULL;

//add the word to memory;

struct word* add_to_memory(struct word* start,char new_word[50]){

	struct word* word_to_add = NULL;
	//temporary node that will point to start;
	struct word* temp = NULL;
	int i;

	//assigning memory to word_to_add;
	word_to_add = (struct word*)malloc(sizeof(struct word));


	//copying the word in the node;
	strcpy(word_to_add->wd,new_word);

	//linking with other nodes;

	if(start == NULL){

		word_to_add->nextword = NULL;
		start = word_to_add;
		return start;
	}

	else{

		temp = start;
		//get to last node;
		while(temp->nextword!=NULL){

			temp = temp->nextword;

		}

		word_to_add->nextword = NULL;
		temp->nextword = word_to_add;

	return start;
	}


}
//open the file in read mode and pass the values to add_to_memory();

void read_file(){
	char new_word[50];
	FILE *fp;

	fp = fopen("temp.txt","r");

	while(fgets(new_word,50,fp)!=NULL){

		start = add_to_memory(start,new_word);

	}

	fclose(fp);

}



//making update to file by opening in append mode ;
void make_update_to_file(struct word* start){

	struct word* temp = NULL;
	FILE *fp;
	fp = fopen("temp.txt","a");

	temp = start;
	while(temp->nextword!=NULL){

	temp = temp->nextword;

	}

	//fputs("\n",fp);
	fputs(temp->wd,fp);
	fclose(fp);

}


//insert new word in the linked list at the last;
struct word* add_new_word(struct word* start,char newword[50]){
	int i = 0;
	struct word* new_word = NULL;
	struct word* temp = NULL;

	//assigning location to new word;
	new_word = (struct word*)malloc(sizeof(struct word));

	//for(i=0 ; new_word->wd[i]!='\0' ; i++){
	//}
	//new_word->wd[i] = '\0';
	//inserting this word at the end of linked list;

	strcpy(new_word->wd,newword);
	
	if(start == NULL){

		new_word->nextword = NULL;
		start = new_word;
		make_update_to_file(start);
		return start;

	}
	else{
		temp = start;
		while(temp->nextword!=NULL){

			temp = temp->nextword;

		}
		new_word->nextword = NULL;
		temp->nextword = new_word;
		make_update_to_file(start);
		return start;


	}



}





//display the linked list in the memory;
void display(struct word* start){

	struct word* temp = NULL;
	temp = start;
	while(temp!=NULL){
	
		printf("%s",temp->wd);
		temp = temp->nextword;
	
	
	
	}

}

void search(struct word* start){

	char word_to_find[20];
	int len,i;
	int isThere = 0;
	struct word* temp = NULL;
	printline();
	printf("\nEnter word to find:");
	scanf("%s",word_to_find);
	len = strlen(word_to_find);
	temp = start;
	while(temp!=NULL){
		i = strncmp(temp->wd,word_to_find,len);
		if(i==0){
			printf("\n%s",temp->wd);
			isThere = 1;
			break;
		}
	
	
		temp = temp->nextword;
	}
	if(isThere == 0 ){
	
		printf("\nWord not found");
	
	}
	
}



//making update to file after deleting;
//opening the file in write mode;

void make_update_to_file_after_del(struct word* start){

	FILE *fp;
	int i;
	struct word* temp = NULL;
	temp = start;

	//opening the file;
	fp = fopen("temp.txt","w");

	while(temp!=NULL){

		i = strlen(temp->wd);
		//word is empty;
		if(i==0){

			temp = temp->nextword;
		
		}
		
		else{
		
			fputs(temp->wd,fp);
			temp = temp->nextword;
		
		}
	
		
	
	
	}

  fclose(fp);
}


//delete the word here;
void delete_word(struct word* start){

	char word_to_delete[20];
	struct word* temp = NULL;
	int isThere = 0,i,len;
	printline();
	printf("\nEnter word to delete:");
	scanf("%s",word_to_delete);
	
	len = strlen(word_to_delete);
	
	temp = start;
	//finding the word;
	while(temp!=NULL){
		i = strncmp(temp->wd,word_to_delete,len);
		
		if(i==0){
		
			isThere = 1;
			strcpy(temp->wd,"");
			break;

		}


		temp = temp->nextword;
	}
	
	//debugging;
	//display(start);
	
	if(isThere == 0){

		printf("\nWord not found....unable to delete");

	}
	else{
	
		make_update_to_file_after_del(start);
	
	}




}


//driver program for dictionary;
int main(){
	char choice;
	char newword[50];
	clrscr();
	read_file();
	//display(start);
	while(1){
	printf("\n 1.Insert 2.search 3.delete 4.exit-------->");
	scanf("%c",&choice);
	getchar();
	switch(choice){
	
		case 'i':
		
				
				printline();
	
				printf("\nEnter new word along with meaning:");
				scanf("%[^.]s",newword);
				start = add_new_word(start,newword);
				break;
		case 's':
				search(start);
				break;
		
		case 'd':
				delete_word(start);
				break;
		
		
		case 'e':
				exit(0);
	
	}
	}
	

getch();
flushall();
return 0;
	
}




















