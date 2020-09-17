import time
import timetablejson
from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
import selenium.common.exceptions as ex
import os

global id, password,opened
opened=False


def get_download_path():
    """Returns the default downloads path for linux or windows"""
    if os.name == 'nt':
        import winreg
        sub_key = r'SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\Shell Folders'
        downloads_guid = '{374DE290-123F-4565-9164-39C4925E467B}'
        with winreg.OpenKey(winreg.HKEY_CURRENT_USER, sub_key) as key:
            location = winreg.QueryValueEx(key, downloads_guid)[0]
        return location
    else:
        return os.path.join(os.path.expanduser('~'), 'downloads')


def open_webdriver():
    global options, excutablepath, driver
    options = webdriver.ChromeOptions()
    options.add_argument(
        "user-data-dir=D:\\Temp\\User Data\\default1")
    if (os.path.isfile(get_download_path() + "\chromedriver_win32\chromedriver.exe")):
        excutablepath = get_download_path() + "\chromedriver_win32\chromedriver.exe"
    else:
        excutablepath = get_download_path() + "\chromedriver.exe"
    driver = webdriver.Chrome(executable_path=excutablepath,
                              options=options)
    opened=True
    driver.set_page_load_timeout(5)

def getidpass():
    fileexitst = os.path.isfile(get_download_path()+"\idpassword.txt")
    if (fileexitst == False):
        print("For first time only....")
        while True:
            id = input("Enter the ID-> ").lower()
            password = input("Enter the PASSWORD-> ")
            print(f"YOUR DETAILS WITH ID AND PASSWORD IS STORED IN-> {get_download_path()}\idpasswordh .txt")
            with open(get_download_path()+"\\idpassword.txt", 'w') as file:
                file.write(id + "\n" + password)
            change=input("Would you like to edit your username and password(y or n)").lower()
            if change=='n':
                break
        os.system(f"attrib +h {get_download_path()}\idpassword.txt")
        login()

    else:
        login()


def login():
    try:
        driver.get("https://learn.upes.ac.in/")
        login1()
    except ex.TimeoutException:
        login1()
    finally:
        driver.set_page_load_timeout(10)


def login1():
    with open(get_download_path() + "\\idpassword.txt") as detalis:
        id = detalis.readline().rstrip('\n')
        password = detalis.readline().rstrip('\n')
    try:
        driver.find_element_by_id("user_id").send_keys(id)
        driver.find_element_by_id("password").send_keys(password)
        WebDriverWait(driver, 30).until(EC.element_to_be_clickable((By.ID, "entry-login")))
        driver.find_element_by_id("entry-login").click()
    except ex.NoSuchElementException:
        driver.set_page_load_timeout(10)
        login()
    WebDriverWait(driver, 50).until(EC.presence_of_element_located((By.ID, "module:_3_1")))
    try:
        time.sleep(2)
        driver.find_element_by_id("agree_button").click()
    except:
        pass

def MicroProcessorTheory():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.LINK_TEXT,
                         "Micro Processor & Embedded Systems.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_314")))
    driver.find_element_by_link_text(
        "Micro Processor & Embedded Systems.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_314").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    # time.sleep(8)
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-b7262a1fb18a45f5a842a18d08552524")))
    driver.find_element_by_id("session-b7262a1fb18a45f5a842a18d08552524").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def MicroprocessorLab():

    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Micro Processor & Embedded Systems Lab.BT-CSE-SPZ-GG-V-B2.VR_B_320")))
    driver.find_element_by_link_text(
        "Micro Processor & Embedded Systems Lab.BT-CSE-SPZ-GG-V-B2.VR_B_320").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-ed76c081d7e5476497a7a795b3c3e3af")))
    driver.find_element_by_id("session-ed76c081d7e5476497a7a795b3c3e3af").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def ObjectOrientedAnalysisTheory():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Object Oriented Analysis and Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_310")))
    driver.find_element_by_link_text(
        "Object Oriented Analysis and Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_310").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-1e93f5d8335b417e8a2d0f89000335a7")))
    driver.find_element_by_id("session-1e93f5d8335b417e8a2d0f89000335a7").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def ObjectOrientedAnalysisLab():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Object Oriented Analysis and Design Lab.BT-CSE-SPZ-GG-V-B2.VR_B_319")))
    driver.find_element_by_link_text(
        "Object Oriented Analysis and Design Lab.BT-CSE-SPZ-GG-V-B2.VR_B_319").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-54a389806f9d41efbd53ec11dd2922b6")))
    driver.find_element_by_id("session-54a389806f9d41efbd53ec11dd2922b6").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def WebProgramLab():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Object Oriented Analysis and Design Lab.BT-CSE-SPZ-GG-V-B2.VR_B_319")))
    driver.find_element_by_link_text(
        "Web Program for GG (HTML 5 & Web GL) Lab.BT-CSE-SPZ-GG-V-B2.VR_B_322").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-2379e0ea2b2f44d6963a293a62b5061f")))
    driver.find_element_by_id("session-2379e0ea2b2f44d6963a293a62b5061f").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def WebProgramTheory():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Web Programming for GG (HTML 5 & Web GL).BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_313")))
    driver.find_element_by_link_text(
        "Web Programming for GG (HTML 5 & Web GL).BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_313").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-eac78448b573436788b37ef2d740a900")))

    driver.find_element_by_id("session-eac78448b573436788b37ef2d740a900").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def AdvancedGameProgramingTheory():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Advanced Game Programming Algorithms.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_312")))
    driver.find_element_by_link_text(
        "Advanced Game Programming Algorithms.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_312").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-dfe7c3ab16e54b11a832e4a2a8f10156")))
    driver.find_element_by_id("session-dfe7c3ab16e54b11a832e4a2a8f10156").click()
    time.sleep(2)
    while True:
        try:
            driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()
            break
        except ex.NoSuchElementException:
            time.sleep(10)
            driver.refresh()


def AdvancedGameProgramingLab():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Advanced Game Programming Algorithms Lab.BT-CSE-SPZ-GG-V-B2.VR_B_321")))
    driver.find_element_by_link_text(
        "Advanced Game Programming Algorithms Lab.BT-CSE-SPZ-GG-V-B2.VR_B_321").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-77454f853e474e009615dd3a8c04c0c1")))
    driver.find_element_by_id("session-77454f853e474e009615dd3a8c04c0c1").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def CompilerDesign():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Compiler Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_311")))
    driver.find_element_by_link_text(
        "Compiler Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_311").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))
    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-bce26bb5aa6a4fdbb0d239f2e78e7c1f")))
    driver.find_element_by_id("session-bce26bb5aa6a4fdbb0d239f2e78e7c1f").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def BIZ():
    driver.switch_to.window(driver.window_handles[0])
    driver.get("https://learn.upes.ac.in/")
    WebDriverWait(driver, 10).until(EC.presence_of_element_located(
        (By.LINK_TEXT, "Introduction to Business Analytics.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_309")))
    driver.find_element_by_link_text(
        "Introduction to Business Analytics.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_309").click()
    WebDriverWait(driver, 15).until(EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Collaborate")))
    driver.find_element_by_partial_link_text("Collaborate").click()
    WebDriverWait(driver, 30).until(EC.frame_to_be_available_and_switch_to_it((By.ID, "contentFrame")))

    WebDriverWait(driver, 30).until(EC.presence_of_element_located((By.ID,
                                                                    "session-c3eac564b6de4658b5a7c180ba4c1f37")))
    driver.find_element_by_id("session-c3eac564b6de4658b5a7c180ba4c1f37").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()



timestart = time.strftime("%H:%M", time.localtime())


def opensubjectlink():
    global timewait
    if subject == 'OOT':
        ObjectOrientedAnalysisTheory()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'AGPT':
        AdvancedGameProgramingTheory()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'WEBT':
        WebProgramTheory()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'AGPL':
        AdvancedGameProgramingLab()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'WEBL':
        WebProgramLab()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'OOADL':
        ObjectOrientedAnalysisLab()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'MPEDL':
        MicroprocessorLab()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'MPET':
        MicroProcessorTheory()
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'CompilerT':
        CompilerDesign()
        print("func call")
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    elif subject == 'BIZT':
        BIZ()

        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)
    else:
        print("You are free now")
        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait(subject, timewait)


while '08:55' < timestart <= '17:00':
    if timestart>'17:00':
        break
    checksubject=timetablejson.subject()
    if checksubject!='you are free':
        if  (opened):
            pass
        else:
            open_webdriver()
            opened=True
            getidpass()

    else :#(checksubject == 'you are free'):

        timewait = time.strftime("%M", time.localtime())
        timetablejson.wait('subject', timewait)
        if  (opened):
            pass
        else:
            open_webdriver()
            opened=True
            getidpass()
    try:
        subject = timetablejson.subject()
        opensubjectlink()
    except ex.TimeoutException:
        opensubjectlink()
    except ex.NoSuchElementException:
        opensubjectlink()

    except (ex.WebDriverException):
        open_webdriver()
        getidpass()

    timestart = time.strftime("%H:%M", time.localtime())

print("no class")