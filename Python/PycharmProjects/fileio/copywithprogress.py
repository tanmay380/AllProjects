import os
import shutil
import threading
import time

des = r'D:\Flutter'
src = r'D:\Users\FLUTTER'


def checker(source_path, destination_path):
    """
    Compare 2 files till they're the same and print the progress.

    :type source_path: str
    :param source_path: path to the source file
    :type destination_path: str
    :param destination_path: path to the destination file
    """

    # Making sure the destination path exists
    while not os.path.exists(destination_path):
        print ("not exists")
        time.sleep(.01)

    # Keep checking the file size till it's the same as source file
    while os.path.getsize(source_path) != os.path.getsize(destination_path):
        print ("percentage", int((float(os.path.getsize(destination_path))/float(os.path.getsize(source_path))) * 100))
        time.sleep(.01)

    print ("percentage", 100)


def copying_file(source_path, destination_path):
    """
    Copying a file

    :type source_path: str
    :param source_path: path to the file that needs to be copied
    :type destination_path: str
    :param destination_path: path to where the file is going to be copied
    :rtype: bool
    :return: True if the file copied successfully, False otherwise
    """
    print ("Copying....")
    shutil.copytree(source_path, destination_path)

    if os.path.exists(destination_path):
        print ("Done....")
        return True

    print ("Filed...")
    return False


t = threading.Thread(name='copying', target=copying_file, args=(src, des))
# Start the copying on a separate thread
t.start()
# Checking the status of destination file on a separate thread
b = threading.Thread(name='checking', target=checker, args=(src, des))
b.start()