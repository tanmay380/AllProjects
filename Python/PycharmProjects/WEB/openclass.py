import time
from WEB import timetablejson
from selenium import webdriver

options = webdriver.ChromeOptions()
options.add_argument("user-data-dir=D:\Temp\\User Data\default1")#change this path with your copied chrome folder
driver = webdriver.Chrome(executable_path="D:\Tanmay Singhal\Downloads\chromedriver.exe", options=options)#change this path with your chromedriver.exe location


subject=timetablejson.subject()
print(subject)

def login():
    driver.get("https://learn.upes.ac.in/")
    driver.find_element_by_id("user_id").send_keys("500069450@stu.upes.ac.in")
    driver.find_element_by_id("password").send_keys("tanmay427462")
    driver.find_element_by_id("entry-login").click()
login()

def MicroProcessorTheory():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Micro Processor & Embedded Systems.BT-find_element_by_idCSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_314").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-b7262a1fb18a45f5a842a18d08552524").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()

def MicroprocessorLab():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Micro Processor & Embedded Systems Lab.BT-CSE-SPZ-GG-V-B2.VR_B_320").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-ed76c081d7e5476497a7a795b3c3e3af").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()

def ObjectOrientedAnalysisTheory():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Object Oriented Analysis and Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_310").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(9)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-1e93f5d8335b417e8a2d0f89000335a7").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def ObjectOrientedAnalysisLab():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Object Oriented Analysis and Design Lab.BT-CSE-SPZ-GG-V-B2.VR_B_319").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(9)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-54a389806f9d41efbd53ec11dd2922b6").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def WebProgramLab():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Web Program for GG (HTML 5 & Web GL) Lab.BT-CSE-SPZ-GG-V-B2.VR_B_322").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(9)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-2379e0ea2b2f44d6963a293a62b5061f").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def WebProgramTheory():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Web Programming for GG (HTML 5 & Web GL).BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_313").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(9)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-eac78448b573436788b37ef2d740a900").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def AdvancedGameProgramingTheory():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Advanced Game Programming Algorithms.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_312").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-dfe7c3ab16e54b11a832e4a2a8f10156").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


def AdvancedGameProgramingLab():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Advanced Game Programming Algorithms Lab.BT-CSE-SPZ-GG-V-B2.VR_B_321").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-77454f853e474e009615dd3a8c04c0c1").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()

def CompilerDesign():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Compiler Design.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_311").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-bce26bb5aa6a4fdbb0d239f2e78e7c1f").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()

def BIZ():
    # login()
    driver.get("https://learn.upes.ac.in/")
    time.sleep(2)
    driver.find_element_by_link_text(
        "Introduction to Business Analytics.BT-CSE-SPZ-GG-V-B1.BT-CSE-SPZ-GG-V-B2.VR_B_309").click()
    driver.find_element_by_partial_link_text("Collaborate").click()
    time.sleep(8)
    driver.switch_to.frame("contentFrame")
    driver.find_element_by_id("session-c3eac564b6de4658b5a7c180ba4c1f37").click()
    time.sleep(2)
    driver.find_element_by_xpath('/html/body/div[1]/div/div[2]/div[2]/div/div/div/div/div[2]/div').click()


if  subject=='OOT':
    ObjectOrientedAnalysisTheory()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='AGPT':
    AdvancedGameProgramingTheory()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='WEBT':
    WebProgramTheory()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='AGPL':
    AdvancedGameProgramingLab()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='WEBT':
    WebProgramLab()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='OOADL':
    ObjectOrientedAnalysisLab()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='MPEDL':
    MicroprocessorLab()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='MPET':
    try:
        MicroProcessorTheory()
    except:
        MicroProcessorTheory()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='CompilerT':
    CompilerDesign()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
elif subject=='BIZT':
    BIZ()
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)
else:
    print("You are free now")
    timewait= time.strftime("%M", time.localtime())
    timetablejson.wait(timewait)










