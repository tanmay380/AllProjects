#include <arpa/inet.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>
#define MAX 80
#define IP_PROTOCOL 0
#define IP_ADDRESS "127.0.0.1" // localhost
#define PORT_NO 8080
#define NET_BUF_SIZE 32
#define cipherKey 'S'
#define sendrecvflag 0
#define SA struct sockaddr

// funtion to clear bufferer
void clearBuf(char *b)
{
    int i;
    for (i = 0; i < NET_BUF_SIZE; i++)
        b[i] = '\0';
}

// function for decryption
char Cipher(char ch)
{
    return ch ^ cipherKey;
}

void FileName(int sockfd, struct sockaddr_in addr_con)
{
    char net_buf[NET_BUF_SIZE],confirm[1];
    int addrlen = sizeof(addr_con), nBytes;
    int ext=1;
    do
    {

        printf("\nPlease enter file name to receive:\n");
        scanf("%s", net_buf);
        sendto(sockfd, net_buf, NET_BUF_SIZE,
               sendrecvflag, (struct sockaddr *)&addr_con,
               addrlen);
        // write(sockfd, net_buf, sizeof(net_buf));

        printf("\n---------Data Received---------\n");

        while (1)
        {
            // receive
            clearBuf(net_buf);
            nBytes = recvfrom(sockfd, net_buf, NET_BUF_SIZE,
                              sendrecvflag, (struct sockaddr *)&addr_con,
                              &addrlen);

            // process
            if (recvFile(net_buf, NET_BUF_SIZE))
            {
                break;
            }
        }
        printf("\n-------------------------------\n");

        printf("\n Do you want more Files(y/n):-> ");
        scanf(" %s", confirm);
        sendto(sockfd, confirm, NET_BUF_SIZE,
               sendrecvflag, (struct sockaddr *)&addr_con,
               addrlen);

        if (strncmp("n", confirm, 1) == 0)
        {
            ext=0;
            printf("\nFile Transfer Exit...\n");
            break;
        }
    } while (ext==1);
}

// function to receive file
int recvFile(char *buf, int s)

{
    int i;
    char ch;
    for (i = 0; i < s; i++)
    {
        ch = buf[i];
        ch = Cipher(ch);
        if (ch == EOF)
            return 1;
        else
            printf("%c", ch);
    }
    return 0;
}

void func(int sockfd, struct sockaddr_in addr_con)
{

    char buffer[MAX];
    int n;
    start:
    for (;;)
    {
        bzero(buffer, sizeof(buffer));
        printf("Enter the string : ");
        n = 0;
        while ((buffer[n++] = getchar()) != '\n')
            ;
        write(sockfd, buffer, sizeof(buffer));
        if (strncmp("SENDFILE", buffer, 8) == 0)
        {
            printf("Send file name.\n");
            FileName(sockfd, addr_con);
            goto start;
        }
        if (strncmp("exit", buffer, 4) == 0)
        {
            printf("Client Exit...\n");
            break;
        }
        bzero(buffer, sizeof(buffer));
        read(sockfd, buffer, sizeof(buffer));
        printf("From Server : %s", buffer);
        if ((strncmp(buffer, "exit", 4)) == 0)
        {
            printf("Client Exit...\n");
            break;
        }
    }
}
// driver code
int main()
{
    int sockfd, nBytes;
    struct sockaddr_in addr_con;
    int addrlen = sizeof(addr_con);

    char net_buf[NET_BUF_SIZE];
    FILE *fp;
    // socket()
    sockfd = socket(AF_INET, SOCK_STREAM,
                    IP_PROTOCOL);

    if (sockfd == -1)
    {
        printf("socket creation failed...\n");
        exit(0);
    }
    else
        printf("Socket successfully created..\n");
    bzero(&addr_con, sizeof(addr_con));

    addr_con.sin_family = AF_INET;
    addr_con.sin_port = htons(PORT_NO);
    addr_con.sin_addr.s_addr = inet_addr(IP_ADDRESS);

    if (connect(sockfd, (SA *)&addr_con, sizeof(addr_con)) != 0)
    {
        printf("connection with the server failed...\n");
        exit(0);
    }
    else
    {
        printf("connected to the server..\n");
        printf("\nWe have introduced new File Sharing system with our clients.\n\t TYPE \"SENDFILE\" to ask for a file\n\n");
    }

    // function for chat
    func(sockfd, addr_con);

    // close the socket
    close(sockfd);
}