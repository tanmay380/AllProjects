def show_on_password():
    print('u cracked it')


def create_file():
    email=input("enter se")
    # password=input("enter as")
    with open('imp.txt', 'a') as f:
        for i in f.():
            if email in i:
                print("Already regiterd")
        else:
            f.write(email)
            f.write(':')
            f.write(password)
            f.write('\n')



logged_in = False
create_file()
ent_u=input("enter vheck user")
ent_p=input("enter vheck pass")

with open('imp.txt', 'r') as file:
    for line in file:
        username, password = line.split(':')
        if username == ent_u:
            if password == ent_p+'\n':
                logged_in = True
            else:
                print('wrong password')
            break
        else:
            print('email not registerd')
if logged_in:
    show_on_password()
else:
    print('no one')

# import csv
#
# def main():
#     with open("imp.txt","r") as file:
#         file_reader = csv.reader(file)
#         user_find(file_reader)
#         file.close()
#
# def user_find(file):
#     user = input("Enter your username: ")
#     for row in file:
#         if row[0] == user:
#             print("username found", user)
#             user_found = [row[0],row[1]]
#             pass_check(user_found)
#             break
#         else:
#             print("not found")
#
# def create_file(email, password):
#     email=input('ener your mail')
#     password=input('ener your pass')
#     with open('imp.txt', 'a') as f:
#         file_writer=csv.writer(f)
#         file_writer.writerow(email,password)
#
# def pass_check(user_found):
#     user = input("enter your password: ")
#     if user_found[1] == user:
#         print("password match")
#     else:
#         print("password not match")
#
# main()