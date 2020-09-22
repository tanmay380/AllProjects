from tkinter import *

# from WEB.timetablejson import subject

root = Tk()
root.title("CLASS LOADER")
p1=PhotoImage(file='download.png')
root.iconphoto(False,p1)

# gui logic
root.geometry("644x434")
root.minsize(644, 434)
# root.maxsize(644, 434)

heading = Label(root, bg="black", fg="white", text='WELCOME TO UPES\n AUTO CLASS LOADER', font="comicsans 30 bold")
heading.grid(row=0, column=0, padx=100)

# email=Frame(root).grid(row=1,column=4)
# Label(email,text="EMAIL").grid()
email = Frame(root,bg ="gold", borderwidth=1).grid(row=1, column=0, pady=20)
Label(email, text='EMAIL', font="comicsans 15 bold",bg="gold", borderwidth=1, relief=SUNKEN).grid(row=1,column=0,pady=20,padx=20)
emailentry=Entry(email, font="comicsans 15 bold", bg="gold", borderwidth=1, relief=SUNKEN).grid(row=1, column=0,padx=30)

root.mainloop()
